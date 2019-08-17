package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NamePattern("%s|salesPersonName")
@Table(name = "SALESSAAS_SALES_PERSON")
@Entity(name = "salessaas_SalesPerson")
public class SalesPerson extends StandardTenantEntity {
    private static final long serialVersionUID = 7551955656006110163L;

    @NotNull
    @Column(name = "SALES_PERSON_NAME", nullable = false, unique = true)
    protected String salesPersonName;

    @Column(name = "TITLE")
    protected String title;

    @Pattern(message = "{msg://SalesPersonInvalidPhone}", regexp = "(?:\\d{3}-?){2}\\d{4}")
    @Column(name = "CONTACT_NUMBER")
    protected String contactNumber;

    @Email(message = "{msg://InvalidEmail}", regexp = "^[a-zA-Z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @Column(name = "EMAIL_ADDRESS")
    protected String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }
}