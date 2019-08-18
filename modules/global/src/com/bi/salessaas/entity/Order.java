package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NamePattern("%s %s %s|customer,season,orderType")
@Table(name = "SALESSAAS_ORDER")
@Entity(name = "salessaas_Order")
public class Order extends StandardTenantEntity {
    private static final long serialVersionUID = -5561335352908326511L;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @NotNull
    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_ID")
    protected JobType jobType;

    @NotNull
    @Column(name = "ORDER_TYPE", nullable = false)
    protected Integer orderType;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEASON_ID")
    protected Year season;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALES_PERSON_ID")
    protected SalesPerson salesPerson;

    @NumberFormat(pattern = "#%")
    @Column(name = "SHIPPING_RATE")
    protected BigDecimal shippingRate = new BigDecimal(.14);

    @MetaProperty(datatype = "currency")
    @Column(name = "SHIPPING_AMOUNT")
    protected BigDecimal shippingAmount = BigDecimal.ZERO;

    @Column(name = "IS_DISCOUNTED")
    protected Boolean isDiscounted = false;

    @Column(name = "DISCOUNT")
    @MetaProperty(datatype = "currency")
    protected BigDecimal discount = BigDecimal.ZERO;

    @NumberFormat(pattern = "#.#%")
    @Column(name = "DISCOUNT_PERCENT")
    protected BigDecimal discountPercent = BigDecimal.ZERO;

    @Column(name = "IS_INSTALL_TAXABLE")
    protected Boolean isInstallTaxable = true;

    @Column(name = "IS_REMOVAL_TAXABLE")
    protected Boolean isRemovalTaxable = true;

    @Column(name = "IS_STORAGE_TAXABLE")
    protected Boolean isStorageTaxable = true;

    @Column(name = "NOTES", length = 1000)
    protected String notes;

    @Column(name = "STATUS")
    protected Integer status;

    @Column(name = "DATE_ENTERED")
    protected LocalDateTime dateEntered = LocalDateTime.now(); //defaults to right now

    @Column(name = "DATE_SOLD")
    protected LocalDateTime dateSold;

    @Column(name = "INSTALL_INSTRUCTIONS", length = 1024)
    protected String installInstructions;

    @Column(name = "REMOVAL_INSTRUCTIONS", length = 1024)
    protected String removalInstructions;

    @Column(name = "DRIVING_DIRECTIONS", length = 1024)
    protected String drivingDirections;

    @Column(name = "INSTALL_DATE")
    protected LocalDateTime installDate;

    @Column(name = "REMOVAL_DATE")
    protected LocalDateTime removalDate;

    @Column(name = "USE_REMOVAL_PERCENT")
    protected Boolean useRemovalPercent = true;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_PRODUCT")
    protected BigDecimal totalProduct = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_STORAGE")
    protected BigDecimal totalStorage = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_INSTALL")
    protected BigDecimal totalInstall = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_REMOVAL")
    protected BigDecimal totalRemoval = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "TOTAL_TAX")
    protected BigDecimal totalTax = BigDecimal.ZERO;

    @MetaProperty(datatype = "currency")
    @Column(name = "GRAND_TOTAL")
    protected BigDecimal grandTotal = BigDecimal.ZERO;

    @Column(name = "IS_INSTALLED")
    protected Boolean isInstalled = false;

    @Column(name = "IS_REMOVED")
    protected Boolean isRemoved = false;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ID")
    protected Source source;

    @Column(name = "PO_NUMBER")
    protected String poNumber;

    @Column(name = "OVERRIDE_STORAGE")
    protected Boolean overrideStorage = false;

    @Column(name = "OVERRIDE_INSTALL")
    protected Boolean overrideInstall = false;

    @Column(name = "OVERRIDE_REMOVAL")
    protected Boolean overrideRemoval = false;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "order")
    protected List<OrderItem> items;

    public void setShippingRate(BigDecimal shippingRate) {
        this.shippingRate = shippingRate;
    }

    public BigDecimal getShippingRate() {
        return shippingRate;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(BigDecimal totalProduct) {
        this.totalProduct = totalProduct;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Boolean getIsStorageTaxable() {
        return isStorageTaxable;
    }

    public void setIsStorageTaxable(Boolean isStorageTaxable) {
        this.isStorageTaxable = isStorageTaxable;
    }

    public Boolean getIsRemovalTaxable() {
        return isRemovalTaxable;
    }

    public void setIsRemovalTaxable(Boolean isRemovalTaxable) {
        this.isRemovalTaxable = isRemovalTaxable;
    }

    public Boolean getIsInstallTaxable() {
        return isInstallTaxable;
    }

    public void setIsInstallTaxable(Boolean isInstallTaxable) {
        this.isInstallTaxable = isInstallTaxable;
    }

    public Boolean getIsDiscounted() {
        return isDiscounted;
    }

    public void setIsDiscounted(Boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }

    public Boolean getOverrideRemoval() {
        return overrideRemoval;
    }

    public void setOverrideRemoval(Boolean overrideRemoval) {
        this.overrideRemoval = overrideRemoval;
    }

    public Boolean getOverrideInstall() {
        return overrideInstall;
    }

    public void setOverrideInstall(Boolean overrideInstall) {
        this.overrideInstall = overrideInstall;
    }

    public Boolean getOverrideStorage() {
        return overrideStorage;
    }

    public void setOverrideStorage(Boolean overrideStorage) {
        this.overrideStorage = overrideStorage;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public void setDateEntered(LocalDateTime dateEntered) {
        this.dateEntered = dateEntered;
    }

    public LocalDateTime getDateEntered() {
        return dateEntered;
    }

    public Boolean getIsInstalled() {
        return isInstalled;
    }

    public void setIsInstalled(Boolean isInstalled) {
        this.isInstalled = isInstalled;
    }

    public BigDecimal getTotalRemoval() {
        return totalRemoval;
    }

    public void setTotalRemoval(BigDecimal totalRemoval) {
        this.totalRemoval = totalRemoval;
    }

    public BigDecimal getTotalInstall() {
        return totalInstall;
    }

    public void setTotalInstall(BigDecimal totalInstall) {
        this.totalInstall = totalInstall;
    }

    public BigDecimal getTotalStorage() {
        return totalStorage;
    }

    public void setTotalStorage(BigDecimal totalStorage) {
        this.totalStorage = totalStorage;
    }

    public Boolean getUseRemovalPercent() {
        return useRemovalPercent;
    }

    public void setUseRemovalPercent(Boolean useRemovalPercent) {
        this.useRemovalPercent = useRemovalPercent;
    }

    public LocalDateTime getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(LocalDateTime removalDate) {
        this.removalDate = removalDate;
    }

    public LocalDateTime getInstallDate() {
        return installDate;
    }

    public void setInstallDate(LocalDateTime installDate) {
        this.installDate = installDate;
    }

    public String getDrivingDirections() {
        return drivingDirections;
    }

    public void setDrivingDirections(String drivingDirections) {
        this.drivingDirections = drivingDirections;
    }

    public String getRemovalInstructions() {
        return removalInstructions;
    }

    public void setRemovalInstructions(String removalInstructions) {
        this.removalInstructions = removalInstructions;
    }

    public String getInstallInstructions() {
        return installInstructions;
    }

    public void setInstallInstructions(String installInstructions) {
        this.installInstructions = installInstructions;
    }

    public Year getSeason() {
        return season;
    }

    public void setSeason(Year season) {
        this.season = season;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDateTime getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDateTime dateSold) {
        this.dateSold = dateSold;
    }

    public OrderStatus getStatus() {
        return status == null ? null : OrderStatus.fromId(status);
    }

    public void setStatus(OrderStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }

    public OrderType getOrderType() {
        return orderType == null ? null : OrderType.fromId(orderType);
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType == null ? null : orderType.getId();
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }




}