/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jobits.woo.domain.utils.DateDeserializer;
import java.time.LocalDateTime;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class Client {

    private int id;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime date_created;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime date_modified;
    private BillingInfo billing;
    private boolean is_paying_customer;
    private String first_name;
    private String last_name;
    private String email;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDateTime date_created) {
        this.date_created = date_created;
    }

    public LocalDateTime getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(LocalDateTime date_modified) {
        this.date_modified = date_modified;
    }

    public BillingInfo getBilling() {
        return billing;
    }

    public void setBilling(BillingInfo billing) {
        this.billing = billing;
    }

    public boolean isIs_paying_customer() {
        return is_paying_customer;
    }

    public void setIs_paying_customer(boolean is_paying_customer) {
        this.is_paying_customer = is_paying_customer;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", billing=" + billing + ", is_paying_customer=" + is_paying_customer + '}';
    }

}
