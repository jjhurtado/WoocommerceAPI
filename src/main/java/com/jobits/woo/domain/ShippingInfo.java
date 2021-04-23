/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.domain;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ShippingInfo {

    private String first_name;
    private String last_name;
    private String address_1;
    private String address_2;
    private String city;
    private String state;

    public ShippingInfo() {
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

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ShippingInfo{" + "first_name=" + first_name + ", last_name=" + last_name + ", address_1=" + address_1 + ", address_2=" + address_2 + ", city=" + city + ", state=" + state + '}';
    }

    
    
}
