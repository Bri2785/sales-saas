package com.bi.salessaas.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ItemStatus implements EnumClass<Integer> {

    NEW(10),
    EXISTING(20),
    NON_CHARGABLE(30);

    private Integer id;

    ItemStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ItemStatus fromId(Integer id) {
        for (ItemStatus at : ItemStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}