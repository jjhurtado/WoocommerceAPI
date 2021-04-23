/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.woo.domain;

/**
 * 
 * JoBits
 * @author Jorge
 * 
 */
 public class Producto {

    private int id;
    private int product_id;
    private int variation_id;
    private String name;
    private int quantity;
    private String short_description;

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVariation_id() {
        return variation_id;
    }

    public void setVariation_id(int variation_id) {
        this.variation_id = variation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", product_id=" + product_id 
                + ", variation_id=" + variation_id + ", name=" + name 
                + ", quantity=" + quantity + ", short_description=" + short_description + '}';
    }
    
    
    
    
    
}
