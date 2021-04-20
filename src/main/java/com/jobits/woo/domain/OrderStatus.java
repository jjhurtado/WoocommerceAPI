/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.woo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public enum OrderStatus {

    PROCCESING("processing"), COMPLETED("completed");

    private static Map<String, OrderStatus> namesMap = new HashMap<String, OrderStatus>(2);

    static {
        namesMap.put("processing", PROCCESING);
        namesMap.put("completed", COMPLETED);
    }

    @JsonCreator
    public static OrderStatus forValue(String value) {
        return namesMap.get(value);
    }

    @JsonValue
    public String toValue() {
        return getStatus();
    }

    private final String status;

    private OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
