package com.jobits.woo.servicios.impresion;

import java.util.ArrayList;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

import javax.swing.JOptionPane;
import com.jobits.woo.servicios.impresion.formatter.AbstractTicketFormatter;
import com.jobits.woo.servicios.impresion.formatter.PrintFormatter;

/**
 *
 * @author Jorge
 */
public class Impresion {

    private static EstadoImpresion estadoImpresion = EstadoImpresion.UKNOWN;
    ArrayList<CopiaTicket> RAM = new ArrayList<>();
    public static int cantidadCopias = 1;

    public static Impresion getDefaultInstance() {
        return new Impresion();
    }

    public void print(PrintFormatter formatter, String puntoDeImpresion) {
        for (int i = 0; i <= cantidadCopias; i++) {
            feedPrinter(formatter.formatPrint(PrintFormatter.TICKET_PRINTER), puntoDeImpresion);
        }
    }

    //
    //Getters And Setters
    //

    /**
     *
     * @return
     */
    public static EstadoImpresion getEstadoImpresion() {
        return estadoImpresion;
    }


    //
    // Private printing format methods
    //
    public void forceDrawerKick() {
        Ticket t = new Ticket();
        t.resetAll();
        t.drawerKick();

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(t.finalCommandSet().getBytes(), flavor, null);

        feedPrinter(doc, null);

    }

    public void forceBell() {
        Ticket t = new Ticket();
        t.resetAll();
        t.soundBuzzer();

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(t.finalCommandSet().getBytes(), flavor, null);

        feedPrinter(doc, null);
    }

    //
    //Private Methods
    //
    public void feedPrinter(Doc documentToPrint, String printTo) {

        ImpresoraUseCase impresoraUC = new ImpresoraUseCase(new ImpresoraRepoImpl());
        try {
            impresoraUC.imprimirEnGrupo(printTo, documentToPrint);
        } catch (PrintException e) {
          throw new IllegalStateException("No se pudo imprimir en la impresora",e);
          
        }
    }

     private class JobListener implements PrintJobListener {

        private JOptionPane progressDialog;

        public JobListener() {
        }

        public EstadoImpresion getStatus() {
            return estadoImpresion;
        }

        @Override
        public void printDataTransferCompleted(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.SEND;
            System.out.println(getStatus());
        }

        @Override
        public void printJobCompleted(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.COMPLETED;
            System.out.println(getStatus());
        }

        @Override
        public void printJobFailed(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.FAILED;
            System.out.println(getStatus());
        }

        @Override
        public void printJobCanceled(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.CANCELED;
            System.out.println(getStatus());
        }

        @Override
        public void printJobNoMoreEvents(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.NO_MORE_EVENTS;
            System.out.println(getStatus());
        }

        @Override
        public void printJobRequiresAttention(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.REQUIERE_ATTENTION;
            System.out.println(getStatus());
        }
    }

    private enum EstadoImpresion {

        START("ENVIANDO"),
        SEND("ENVIADO"),
        COMPLETED("COMPLETADO"),
        FAILED("FALLIDO"),
        CANCELED("CANCELADO"),
        NO_MORE_EVENTS("NO MAS EVENTOS"),
        REQUIERE_ATTENTION("REQUIERE ATENCION"),
        UKNOWN("Desconocido");

        private final String Messagge;

        private EstadoImpresion(String Msg) {
            Messagge = Msg;
        }

        public String getMessagge() {
            return Messagge;
        }

        @Override
        public String toString() {
            return "Estado: " + Messagge + "...";
        }

    }

    private class CopiaTicket {

        private final String nombreImpresora;
        private final byte[] impresionData;

        public CopiaTicket(String nombreImpresora, byte[] impresionData) {
            this.nombreImpresora = nombreImpresora;
            this.impresionData = impresionData;
        }

        public String getNombreImpresora() {
            return nombreImpresora;
        }

        public byte[] getImpresionData() {
            return impresionData;
        }

    }
}