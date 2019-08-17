package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamePattern("%s - %s|productCode,description")
@Table(name = "SALESSAAS_PRODUCT")
@Entity(name = "salessaas_Product")
public class Product extends StandardTenantEntity {
    private static final long serialVersionUID = 6815723710546340671L;

    @NotNull
    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @NotNull
    @Column(name = "PRODUCT_CODE", nullable = false, unique = true)
    protected String productCode;

    @Column(name = "DESCRIPTION")
    protected String description;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    protected Integer type;

    @NotNull
    @MetaProperty(datatype = "currency")
    @Column(name = "BASE_RETAIL", nullable = false)
    protected BigDecimal baseRetail;

    @MetaProperty(datatype = "currency")
    @NotNull
    @Column(name = "COST", nullable = false)
    protected BigDecimal cost;

    @MetaProperty(datatype = "currency")
    @Column(name = "STORAGE_PRICE")
    protected BigDecimal storagePrice;

    @Column(name = "INSTALL_MH")
    protected BigDecimal installMH;

    @Column(name = "REMOVAL_MH")
    protected BigDecimal removalMH;

    @Column(name = "INSTALL_CHARGE")
    protected BigDecimal installCharge;

    @Column(name = "REMOVAL_CHARGE")
    protected BigDecimal removalCharge;

    public BigDecimal getBaseRetail() {
        return baseRetail;
    }

    public void setBaseRetail(BigDecimal baseRetail) {
        this.baseRetail = baseRetail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductType getType() {
        return type == null ? null : ProductType.fromId(type);
    }

    public void setType(ProductType type) {
        this.type = type == null ? null : type.getId();
    }

    public BigDecimal getRemovalCharge() {
        return removalCharge;
    }

    public void setRemovalCharge(BigDecimal removalCharge) {
        this.removalCharge = removalCharge;
    }

    public BigDecimal getInstallCharge() {
        return installCharge;
    }

    public void setInstallCharge(BigDecimal installCharge) {
        this.installCharge = installCharge;
    }

    public BigDecimal getRemovalMH() {
        return removalMH;
    }

    public void setRemovalMH(BigDecimal removalMH) {
        this.removalMH = removalMH;
    }

    public BigDecimal getInstallMH() {
        return installMH;
    }

    public void setInstallMH(BigDecimal installMH) {
        this.installMH = installMH;
    }

    public BigDecimal getStoragePrice() {
        return storagePrice;
    }

    public void setStoragePrice(BigDecimal storagePrice) {
        this.storagePrice = storagePrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}