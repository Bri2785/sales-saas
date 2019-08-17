package com.bi.salessaas.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ProductType implements EnumClass<Integer> {

    PRODUCT(10),
    SERVICE(20);

    private Integer id;

    ProductType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ProductType fromId(Integer id) {
        for (ProductType at : ProductType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}