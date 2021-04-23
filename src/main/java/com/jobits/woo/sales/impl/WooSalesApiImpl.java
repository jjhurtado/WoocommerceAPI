/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.sales.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.icoderman.woocommerce.ApiVersionType;
import com.icoderman.woocommerce.EndpointBaseType;
import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import com.jobits.woo.domain.Client;
import com.jobits.woo.domain.Orden;
import com.jobits.woo.domain.OrderStatus;
import com.jobits.woo.module.WooCoreModule;
import com.jobits.woo.sales.WooSalesApi;
import com.jobits.woo.servicios.impresion.Impresion;
import com.jobits.woo.servicios.impresion.ImpresoraService;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WooSalesApiImpl implements WooSalesApi {

    private OAuthConfig config
            = new OAuthConfig(
                    ResourceHandler.getString("site_url"),
                    ResourceHandler.getString("consumer_key"),
                    ResourceHandler.getString("consumer_secret"));

    private WooCommerce wooCommerce = new WooCommerceAPI(config, ApiVersionType.V3);

    private ObjectMapper om = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public boolean completeOrden(Orden o) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", OrderStatus.COMPLETED.getStatus());
        wooCommerce.update(EndpointBaseType.ORDERS.getValue(), o.getId(), params);
        return true;
    }

    @Override
    public boolean completeOrden(List<Orden> ordenes) {
        Map<String, Object> params = new HashMap<>();
        List<Map> orders = new ArrayList<>();
        params.put("update", orders);
        for (Orden o : ordenes) {
            Map<String, Object> batchOrder = new HashMap<>();
            batchOrder.put("status", OrderStatus.COMPLETED.getStatus());
            batchOrder.put("id", o.getId());
            orders.add(batchOrder);
        }
        wooCommerce.batch(EndpointBaseType.ORDERS.getValue(), params);

        return true;
    }

    @Override
    public boolean completeOrden(Integer[] ordenesNo) {
        List<Orden> ret = new ArrayList<>();
        for (int o : ordenesNo) {
            Orden add = new Orden();
            add.setId(o);
            ret.add(add);
        }
        completeOrden(ret);
        return true;
    }

//
//    @Override
//    public List<Client> getClients() {
//        var map = getOptionsMap();
//        List<Map> clients = wooCommerce.getAll(EndpointBaseType.CUSTOMERS.getValue(), map);
//        for (int i = 2; i < 30; i++) {
//            map.put("page", "" + i);
//            clients.addAll(wooCommerce.getAll(EndpointBaseType.CUSTOMERS.getValue(), map));
//        }
//
//        return om.convertValue(clients, om.getTypeFactory().constructCollectionType(List.class, Client.class));
//    }
    @Override
    public List<Orden> getOrdenesActivas() {
        var map = getOptionsMap();
        map.put("page", "1");
        map.put("status", "processing");
        List<Map> orders = wooCommerce.getAll(EndpointBaseType.ORDERS.getValue(), map);
        return om.convertValue(orders, om.getTypeFactory().constructCollectionType(List.class, Orden.class));
    }

    @Override
    public void printOrden(Orden o) {
        ImpresoraService service = WooCoreModule.getInstance().getImplementation(ImpresoraService.class);
        service.
    }

    private Map<String, String> getOptionsMap() {
        Map<String, String> params = new HashMap<>();
        params.put("per_page", "100");
        return params;
    }
}
