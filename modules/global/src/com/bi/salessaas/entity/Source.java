package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|source")
@Table(name = "SALESSAAS_SOURCE")
@Entity(name = "salessaas_Source")
public class Source extends StandardTenantEntity {
    private static final long serialVersionUID = 665993129461656187L;

    @Column(name = "SOURCE")
    protected String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}