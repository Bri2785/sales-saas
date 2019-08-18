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

@NamePattern("%s|product")
@Table(name = "SALESSAAS_ORDER_ITEM")
@Entity(name = "salessaas_OrderItem")
public class OrderItem extends StandardTenantEntity {
    private static final long serialVersionUID = -5039925972692498225L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_ID")
    protected Order order;

    @Column(name = "SORT_ORDER")
    protected Integer sortOrder;

    @NotNull
    @Lookup(type = LookupType.SCREEN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    protected Integer quantity = 1;

    @MetaProperty(datatype = "currency")
    @Column(name = "COST")
    protected BigDecimal cost = BigDecimal.ZERO;

    @NotNull
    @MetaProperty(datatype = "currency", mandatory = true)
    @Column(name = "RETAIL", nullable = false)
    protected BigDecimal retail = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_PRICE")
    protected BigDecimal totalPrice = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_INSTALL_CHARGE")
    protected BigDecimal totalInstallCharge = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_REMOVAL_CHARGE")
    protected BigDecimal totalRemovalCharge = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "INSTALL_CHARGE")
    protected BigDecimal installCharge = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "REMOVAL_CHARGE")
    protected BigDecimal removalCharge = BigDecimal.ZERO;


    @Column(name = "INSTALL_MH")
    protected BigDecimal installMH = BigDecimal.ZERO;

    @NumberFormat(pattern = "#.00")
    @Column(name = "REMOVAL_MH")
    protected BigDecimal removalMH = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "STORAGE")
    protected BigDecimal storage = BigDecimal.ZERO;

    @Column(name = "NOTES", length = 1024)
    protected String notes;

    @Column(name = "USE_MH_FOR_CALC")
    protected Boolean useMHForCalc = true;

    @Column(name = "IS_ON_WORK_ORDER")
    protected Boolean isOnWorkOrder = true;

    @Column(name = "IS_ON_INVOICE")
    protected Boolean isOnInvoice = true;

    @Column(name = "STATUS")
    protected Integer status;

    @Column(name = "TAXABLE")
    protected Boolean taxable = true;

    @Column(name = "INVOICED_STATUS")
    protected Integer invoicedStatus;

    public BigDecimal getTotalRemovalCharge() {
        return totalRemovalCharge;
    }

    public void setTotalRemovalCharge(BigDecimal totalRemovalCharge) {
        this.totalRemovalCharge = totalRemovalCharge;
    }

    public BigDecimal getTotalInstallCharge() {
        return totalInstallCharge;
    }

    public void setTotalInstallCharge(BigDecimal totalInstallCharge) {
        this.totalInstallCharge = totalInstallCharge;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setInstallMH(BigDecimal installMH) {
        this.installMH = installMH;
    }

    public BigDecimal getInstallMH() {
        return installMH;
    }

    public void setRemovalMH(BigDecimal removalMH) {
        this.removalMH = removalMH;
    }

    public BigDecimal getRemovalMH() {
        return removalMH;
    }

    public ItemInvoiceStatus getInvoicedStatus() {
        return invoicedStatus == null ? null : ItemInvoiceStatus.fromId(invoicedStatus);
    }

    public void setInvoicedStatus(ItemInvoiceStatus invoicedStatus) {
        this.invoicedStatus = invoicedStatus == null ? null : invoicedStatus.getId();
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getRetail() {
        return retail;
    }

    public void setRetail(BigDecimal retail) {
        this.retail = retail;
    }

    public Boolean getTaxable() {
        return taxable;
    }

    public void setTaxable(Boolean taxable) {
        this.taxable = taxable;
    }

    public ItemStatus getStatus() {
        return status == null ? null : ItemStatus.fromId(status);
    }

    public void setStatus(ItemStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Boolean getIsOnInvoice() {
        return isOnInvoice;
    }

    public void setIsOnInvoice(Boolean isOnInvoice) {
        this.isOnInvoice = isOnInvoice;
    }

    public Boolean getIsOnWorkOrder() {
        return isOnWorkOrder;
    }

    public void setIsOnWorkOrder(Boolean isOnWorkOrder) {
        this.isOnWorkOrder = isOnWorkOrder;
    }

    public Boolean getUseMHForCalc() {
        return useMHForCalc;
    }

    public void setUseMHForCalc(Boolean useMHForCalc) {
        this.useMHForCalc = useMHForCalc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getStorage() {
        return storage;
    }

    public void setStorage(BigDecimal storage) {
        this.storage = storage;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}