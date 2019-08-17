package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.HasTenant;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.Extends;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("EXT")
@NamePattern("%s %s|name,tenantId")
@Extends(FileDescriptor.class)
@Entity(name = "salessaas_TenantFileDescriptor")
public class TenantFileDescriptor extends FileDescriptor implements HasTenant {
    private static final long serialVersionUID = 390829873421722226L;

    @Column(name = "TENANT_ID")
    protected String tenantId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}