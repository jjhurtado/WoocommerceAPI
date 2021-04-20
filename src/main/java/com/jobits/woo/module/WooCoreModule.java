/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.root101.clean.core.app.modules.AbstractModule;
import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.core.exceptions.AlreadyInitModule;
import com.root101.clean.core.exceptions.NotInitModule;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class WooCoreModule extends DefaultAbstractModule {

    public static final String MODULE_NAME = "Reservas Core Module";

    private final Injector inj = Guice.createInjector(new WooInjectionConfig());

    private static WooCoreModule INSTANCE;

    public static WooCoreModule getInstance() {
        if (INSTANCE == null) {
            throw new NotInitModule(ResourceHandler.getString("com.jobits.pos.reserva.name"));
        }
        return INSTANCE;
    }

    /**
     * Usar init() sin repo por parametro para usar el repo por defecto
     *
     * @param repoModule
     * @return
     * @Deprecated
     */
    public static WooCoreModule init(AbstractModule... repoModule) {
        if (INSTANCE != null) {
            throw new AlreadyInitModule(ResourceHandler.getString("com.jobits.pos.reserva.name"));
        }
        INSTANCE = new WooCoreModule();
        for (AbstractModule m : repoModule) {
            INSTANCE.registerModule(m);

        }
        return getInstance();
    }

    private WooCoreModule() {
        try {
            ResourceHandler.registerExternal("woo-module-props");
        } catch (MalformedURLException ex) {
            Logger.getLogger(WooCoreModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

}
