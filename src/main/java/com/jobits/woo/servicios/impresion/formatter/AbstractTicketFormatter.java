/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.servicios.impresion.formatter;

import com.jobits.woo.servicios.impresion.Impresion;
import com.jobits.woo.servicios.impresion.Ticket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.SimpleDoc;

/**
 *
 * @author Javier
 */
public abstract class AbstractTicketFormatter implements PrintFormatter {

    protected static final Logger LOGGER = Logger.getLogger(Impresion.class.getSimpleName());

    protected final String TAB = "****> ";

    /**
     * Strings referentes a la impresion de resumenes de ventas
     */
    protected final String RESUMEN_VENTAS_CAMAREROS = "Resumen de ventas personal ",
            RESUMEN_VENTAS_COCINA = "Resumen de ventas por area ",
            TOTAL_VENTAS = "Total Vendido: ",
            RESUMEN_CONSUMO_CASA = "Resumen del consumo de la casa ",
            RESUMEN_LISTA_ORDENES = "Resumen de ordenes por venta";

    protected final String IPV_TABLE_HEADER = "Ini. |Ent. |Disp.|Cons.|Final.",
            IPV_HEADER = "Resumen de gasto de insumos",
            IPV_PUNTO_ELAB = "Punto de elaboracion";

    protected final String IPV_VENTA_TABLE_HEADER = "Ini. |Ent. |Disp.|Imp. |Final.",
            IPV_VENTA_HEADER = "Resumen de productos de venta",
            IPV_VENTA_PUNTO_ELAB = "Punto de elaboracion";

    protected final String GASTO_HEADER = "Resumen de gastos";
    protected final String ASISTENCIA_PERSONAL = "Asistencia del personal";

    protected final String PAGO_POR_VENTA_HEADER = "Pago por ventas";

    protected final String RESUMEN_AREA = "Resumen de area de venta";

    protected final String RESUMEN_MESA = "Resumen de Venta por Mesa";

    protected final String RESUMEN_COMISION_PORCENTUAL = "Resumen de Comision Porcentual por Mesa";
    /**
     * String referentes al almacen
     */
    protected final String STOCK_BALANCE = "Balance de stock en almacen",
            STOCK_FORMAT = "En Almacen | Diferencia ",
            COMPROBANTE_TRANSACCION = "Comprobante de Transaccion";

    /**
     * String referentes a los pagos de trabajadores
     */
    protected final String PAGO_TRABAJADOR = "Comprobante de pago a trabajador";

    public AbstractTicketFormatter() {
    }


    protected void addFinal(Ticket t) {
        t.feed((byte) 3);
        t.finit();
    }

    protected void addFocusedMessage(Ticket t, String sms) {
        t.addLineSeperator();
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        t.alignCenter();
        t.setText(sms);
        t.newLine();
        t.addLineSeperator();
        t.newLine();
    }

    protected void addTimeStamp(Ticket t) {
        t.addLineSeperator();
        t.newLine();
        t.alignLeft();
        t.setText("Impreso: "
                + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a")));
        t.newLine();
        t.addLineSeperator();
        t.newLine();
    }

    protected Doc createDoc(byte[] bytes) {
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(bytes, flavor, null);
        return doc;

    }

    protected void adjusttextToLine(Ticket t, String text, String inicio, boolean addTab) {
        String[] part = text.split(" ");
        String[] line = new String[5];
        line[0] = inicio;
        int numLine = 0;
        for (int i = 0; i < part.length; i++) {
            if (line[numLine].length() + part[i].length() < 32) {
                line[numLine] += " " + part[i];
            } else {
                numLine++;
                if (addTab) {
                    line[numLine] = "      " + part[i];
                } else {
                    line[numLine] = part[i];
                }
            }
        }
        for (String string : line) {
            if (string != null) {
                t.setText(string);
                t.newLine();
            } else {
                break;
            }
        }
    }

}
