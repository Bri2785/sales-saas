package com.bi.salessaas.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrderType implements EnumClass<Integer> {

    INTERIOR(10),
    EXTERIOR(20),
    OTHER(30);

    private Integer id;

    OrderType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OrderType fromId(Integer id) {
        for (OrderType at : OrderType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}