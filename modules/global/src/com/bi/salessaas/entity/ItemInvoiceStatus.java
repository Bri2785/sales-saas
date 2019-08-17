package com.bi.salessaas.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ItemInvoiceStatus implements EnumClass<Integer> {

    NOT_INVOICED(10),
    INVOICED(20);

    private Integer id;

    ItemInvoiceStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ItemInvoiceStatus fromId(Integer id) {
        for (ItemInvoiceStatus at : ItemInvoiceStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}