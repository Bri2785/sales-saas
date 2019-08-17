package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
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
    protected Double quantity;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "COST")
    protected BigDecimal cost;

    @NotNull
    @Column(name = "RETAIL", nullable = false)
    protected BigDecimal retail;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "TOTAL_PRICE")
    protected BigDecimal totalPrice;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "INSTALL_CHARGE")
    protected BigDecimal installCharge;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "REMOVAL_CHARGE")
    protected BigDecimal removalCharge;

    @NumberFormat(pattern = "#.00")
    @Column(name = "INSTALL_MH")
    protected Double installMH;

    @NumberFormat(pattern = "#.00")
    @Column(name = "REMOVAL_MH")
    protected Double removalMH;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "STORAGE")
    protected BigDecimal storage;

    @Column(name = "NOTES", length = 1024)
    protected String notes;

    @Column(name = "USE_MH_FOR_CALC")
    protected Boolean useMHForCalc;

    @Column(name = "IS_ON_WORK_ORDER")
    protected Boolean isOnWorkOrder;

    @Column(name = "IS_ON_INVOICE")
    protected Boolean isOnInvoice;

    @Column(name = "STATUS")
    protected Integer status;

    @Column(name = "TAXABLE")
    protected Boolean taxable;

    @Column(name = "INVOICED_STATUS")
    protected Integer invoicedStatus;

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

    public Double getRemovalMH() {
        return removalMH;
    }

    public void setRemovalMH(Double removalMH) {
        this.removalMH = removalMH;
    }

    public Double getInstallMH() {
        return installMH;
    }

    public void setInstallMH(Double installMH) {
        this.installMH = installMH;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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