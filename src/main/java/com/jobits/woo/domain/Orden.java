/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jobits.woo.domain.utils.DateDeserializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class Orden {

    private int id;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime date_created;
    private OrderStatus status;
    private String currency;
    private ShippingInfo shipping;
    private BillingInfo billing;
    private List<Producto> line_items;
    private String customer_note;

    public Orden() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCustomer_note() {
        return customer_note;
    }

    public void setCustomer_note(String customer_note) {
        this.customer_note = customer_note;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ShippingInfo getShipping() {
        return shipping;
    }

    public void setShipping(ShippingInfo shipping) {
        this.shipping = shipping;
    }

    public BillingInfo getBilling() {
        return billing;
    }

    public void setBilling(BillingInfo billing) {
        this.billing = billing;
    }

    
    public List<Producto> getLine_items() {
        return line_items;
    }

    public void setLine_items(List<Producto> line_items) {
        this.line_items = line_items;
    }

    @Override
    public String toString() {
        String items = "";
        for (Producto li : line_items) {
            items += li.getName() + ",";
        }
        return id +" {"+ date_created.format(DateTimeFormatter.ISO_LOCAL_DATE)+"} "+ " {" + items + "}";
    }
    
   
    

}
