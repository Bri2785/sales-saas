package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "SALESSAAS_COMPANY")
@Entity(name = "salessaas_Company")
public class Company extends StandardTenantEntity {
    private static final long serialVersionUID = -2791476034014638098L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "PROPOSAL_ACCEPTANCE_TERMS", length = 2000)
    protected String proposalAcceptanceTerms;

    @Column(name = "ADDRESS1")
    protected String address1;

    @Column(name = "ADDRESS2")
    protected String address2;

    @Column(name = "ADDRESS3")
    protected String address3;

    @Column(name = "CITY")
    protected String city;

    @Column(name = "STATE")
    protected String state;

    @Column(name = "ZIP")
    protected String zip;

    @Column(name = "PHONE")
    protected String phone;

    @Column(name = "FAX")
    protected String fax;

    @Column(name = "WEBSITE")
    protected String website;

    @Column(name = "TAXRATE")
    protected Double taxrate;

    @Column(name = "IS_TAX_OVERRIDEN_BY_CUSTOMER")
    protected Boolean isTaxOverridenByCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "LOGO_FILE_ID")
    protected TenantFileDescriptor logoFile;

    public void setLogoFile(TenantFileDescriptor logoFile) {
        this.logoFile = logoFile;
    }

    public TenantFileDescriptor getLogoFile() {
        return logoFile;
    }

    public String getProposalAcceptanceTerms() {
        return proposalAcceptanceTerms;
    }

    public void setProposalAcceptanceTerms(String proposalAcceptanceTerms) {
        this.proposalAcceptanceTerms = proposalAcceptanceTerms;
    }

    public Boolean getIsTaxOverridenByCustomer() {
        return isTaxOverridenByCustomer;
    }

    public void setIsTaxOverridenByCustomer(Boolean isTaxOverridenByCustomer) {
        this.isTaxOverridenByCustomer = isTaxOverridenByCustomer;
    }

    public Double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Double taxrate) {
        this.taxrate = taxrate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}