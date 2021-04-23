/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.jobits.woo.sales.WooSalesApi;
import com.jobits.woo.sales.impl.WooSalesApiImpl;
import com.jobits.woo.servicios.impresion.ImpresoraService;
import com.jobits.woo.servicios.impresion.ImpresoraUseCase;


/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
class WooInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(WooSalesApi.class).to(WooSalesApiImpl.class).in(Scopes.SINGLETON);
        bind(ImpresoraService.class).to(ImpresoraUseCase.class).in(Scopes.SINGLETON);

    }

}
