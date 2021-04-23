/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.servicios.impresion.formatter;

import com.jobits.woo.domain.Orden;
import com.jobits.woo.domain.Producto;
import com.jobits.woo.servicios.impresion.Ticket;
import java.util.List;
import javax.print.Doc;

/**
 *
 * @author Javier
 */
public class OrdenFormatter extends AbstractTicketFormatter {

    Orden orden;
    boolean preview;

    public OrdenFormatter(Orden orden) {
        this.orden = orden;
        this.preview = preview;
    }

    @Override
    public Doc formatPrint(String printingOutput) {
        float total;

        Ticket t = new Ticket();

        addHeader(t);

        addShippingInfo(t);

        addPvOrden(t, orden.getLine_items());

        addConcentLines(t);

        addFinal(t);

        return createDoc(t.finalCommandSet().getBytes());
    }

    private void addConcentLines(Ticket t) {

    }

    private void addHeader(Ticket t) {
        t.addLineSeperator();
        t.alignRight();
        t.setText("Número de pedido: " + orden.getId());
        t.newLine();
        t.setText("Fecha: " + orden.getCurrency());//TODO: missing
        t.newLine();
        t.addLineSeperator();
        t.newLine();

    }

    private void addPvOrden(Ticket t, List<Producto> line_items) {
        t.alignLeft();
        for (Producto x : line_items) {
            t.setText(x.getQuantity() + " " + x.getName());
            t.newLine();
            t.setText(TAB + x.getShort_description());
            t.newLine();
        }
    }

    private void addShippingInfo(Ticket t) {
        t.setText("N: " + orden.getShipping().getFirst_name() + orden.getShipping().getLast_name());
        t.newLine();
        t.setText("Dir: " + orden.getShipping().getAddress_1());
        t.newLine();
        if (!orden.getShipping().getAddress_2().isEmpty()) {
            t.setText("Apt: " + orden.getShipping().getAddress_2());
            t.newLine();
        }
        t.setText(orden.getShipping().getState() + " - " + orden.getShipping().getCity());
        t.newLine();
        t.addLineSeperator();
    }

}
