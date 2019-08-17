package com.bi.salessaas.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum CustomerTerms implements EnumClass<Integer> {

    DUE_ON_RECEIPT(10),
    NET_30(20);

    private Integer id;

    CustomerTerms(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CustomerTerms fromId(Integer id) {
        for (CustomerTerms at : CustomerTerms.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}