package com.bi.salessaas.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "SALESSAAS_YEAR")
@Entity(name = "salessaas_Year")
public class Year extends StandardEntity {
    private static final long serialVersionUID = 1976887623984141054L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 6)
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}