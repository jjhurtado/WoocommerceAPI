/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.sales;

import com.jobits.woo.domain.Client;
import com.jobits.woo.domain.Orden;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public interface WooSalesApi {

    public List<Orden> getOrdenesActivas();

    public boolean completeOrden(Orden o);

    public boolean completeOrden(List<Orden> ordenes);

    public boolean completeOrden(Integer[] ordenesNo);

    public void printOrden(Orden o);

    //public List<Client> getClients();

}
