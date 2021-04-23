/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.servicios.impresion;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author ERIK QUESADA
 */
public class ImpresoraUseCase implements ImpresoraService {

    private List<Impresora> impresoras;
    private ObjectMapper om = new ObjectMapper();

    private ImpresoraRepo repository;

    public ImpresoraUseCase(ImpresoraRepo repo) {
        this.repository = repo;
        impresoras = repo.cargarImpresoras();
    }

    private List<Impresora> getImpresorasAlmacenadas() {
        return repository.cargarImpresoras();
    }

    private void guardarImpresorasAlmacenadas() /*throws IOException*/ {
        repository.guardarImpresoras(impresoras);
    }

    public List<Impresora> impresoraMathCocina(String mathWithCocina) {
        List<Impresora> listaImpresoras = new ArrayList<>();

        /*  for(int i =0;i<impresoras.size();i++){
                for (int j=0;j<impresoras.get(i).getCocinasEnlazadas().size();j++){
                    if(impresoras.get(i).getCocinasEnlazadas().get(j).getNombreCocina().equals(mathWithCocina));
                        listaImpresoras.add(impresoras.get(i));
                }
            }
         */
        return listaImpresoras;
    }

    public List<Impresora> getImpresorasDefault() {
        List<Impresora> listaImpresoras = new ArrayList<>();

        for (int i = 0; i < impresoras.size(); i++) {
            if (impresoras.get(i).isPorDefecto()) {
                listaImpresoras.add(impresoras.get(i));
            }
        }

        return listaImpresoras;
    }

    @Override
    public Impresora agregarImpresora(Impresora impresora) {
        impresoras = getImpresorasAlmacenadas();
        if (impresoras.isEmpty()) {
            impresora.setIdImpresora(0);
        } else {
            impresora.setIdImpresora(impresoras.get(impresoras.size() - 1).getIdImpresora() + 1);
        }
        impresoras.add(impresora);
        guardarImpresorasAlmacenadas();
        return impresora;
    }

    @Override
    public void updateImpresora(Impresora impresora) {
        impresoras = getImpresorasAlmacenadas();
        for (int i = 0; i < impresoras.size(); i++) {
            if (impresoras.get(i).getIdImpresora() == impresora.getIdImpresora()) {
                impresoras.set(i, impresora);
                guardarImpresorasAlmacenadas();
                break;
            }
        }
    }

    @Override
    public Impresora deleteImpresora(Impresora impresora) {

        if (repository.eliminarImpresora(impresora)) {
            return impresora;
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Impresora findBy(String nombreVirtualImpresora) {
        impresoras = getImpresorasAlmacenadas();
        Impresora impresoraEncontrada = null;

        for (Impresora listaImpresoras : impresoras) {
            if (listaImpresoras.getNombreImpresoraVirtual().equals(nombreVirtualImpresora)) {
                return impresoraEncontrada;
            }
        }
        return null;
    }

    @Override
    public List<Impresora> findAll() {
        List<Impresora> retSorted = getImpresorasAlmacenadas();
        Collections.sort(retSorted);
        return retSorted;
    }

    @Override
    public void imprimirEnGrupo(String nombreGrupo, Doc docToPrint) throws PrintException {
        List<Impresora> listaImpresoras = repository.cargarImpresoras();

        if (nombreGrupo == null) {
            imprimirPorDefault(docToPrint);
        } else {
            for (Impresora listaImpresora : listaImpresoras) {
                if (listaImpresora.getGrupo().equals(nombreGrupo)) {
                    listaImpresora.imprimir(docToPrint);
                }
            }
        }
    }

    private void imprimirPorDefault(Doc docToPrint) throws PrintException {
        for (Impresora impresora : getImpresorasDefault()) {
            impresora.imprimir(docToPrint);
        }
    }

    @Override
    public List<String> getNombreImpresorasSistema() {
        List<String> nombreImpresorasSistema = new ArrayList<>();
        List<PrintService> impresorasSistema = Arrays.asList(PrintServiceLookup.lookupPrintServices(null, null));

        for (PrintService printService : impresorasSistema) {
            nombreImpresorasSistema.add(printService.getName());

        }
        return nombreImpresorasSistema;

    }

    @Override
    public List<String> getNombreGrupos() {
        return null;
    }

}
