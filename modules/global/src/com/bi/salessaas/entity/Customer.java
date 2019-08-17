package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.List;

@Table(name = "SALESSAAS_CUSTOMER")
@Entity(name = "salessaas_Customer")
@NamePattern("#getCaption|firstName,lastName,company")
public class Customer extends StandardTenantEntity {
    private static final long serialVersionUID = -5078154188110491054L;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Column(name = "COMPANY")
    protected String company;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    protected List<Order> orders;

    @Column(name = "BILL_ADDRESS")
    protected String billAddress;

    @Column(name = "BILL_ADDRESS2")
    protected String billAddress2;

    @Column(name = "BILL_CITY")
    protected String billCity;

    @Column(name = "BILL_STATE")
    protected String billState;

    @Column(name = "BILL_ZIP")
    protected String billZip;

    @Column(name = "JOB_ADDRESS1")
    protected String jobAddress1;

    @Column(name = "JOB_ADDRESS2")
    protected String jobAddress2;

    @Column(name = "JOB_ADDRESS3")
    protected String jobAddress3;

    @Column(name = "JOB_CITY")
    protected String jobCity;

    @Column(name = "JOB_STATE")
    protected String jobState;

    @Column(name = "JOB_ZIP")
    protected String jobZip;

    @Column(name = "HOME_PHONE")
    protected String homePhone;

    @Column(name = "WORK_PHONE")
    protected String workPhone;

    @Column(name = "CELL_PHONE")
    protected String cellPhone;

    @Column(name = "FAX")
    protected String fax;

    @Column(name = "CONTACT")
    protected String contact;

    @Column(name = "CONTACT_PHONE")
    protected String contactPhone;

    @Email(message = "{msg://InvalidEmail}", regexp = "^[a-zA-Z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "REFERRED_BY")
    protected String referredBy;

    @Column(name = "TAX_RATE")
    protected Double taxRate;

    @Column(name = "TERMS")
    protected Integer terms;

    @Column(name = "NOTES", length = 1024)
    protected String notes;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getJobZip() {
        return jobZip;
    }

    public void setJobZip(String jobZip) {
        this.jobZip = jobZip;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public String getJobCity() {
        return jobCity;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public String getJobAddress3() {
        return jobAddress3;
    }

    public void setJobAddress3(String jobAddress3) {
        this.jobAddress3 = jobAddress3;
    }

    public String getJobAddress2() {
        return jobAddress2;
    }

    public void setJobAddress2(String jobAddress2) {
        this.jobAddress2 = jobAddress2;
    }

    public String getJobAddress1() {
        return jobAddress1;
    }

    public void setJobAddress1(String jobAddress1) {
        this.jobAddress1 = jobAddress1;
    }

    public CustomerTerms getTerms() {
        return terms == null ? null : CustomerTerms.fromId(terms);
    }

    public void setTerms(CustomerTerms terms) {
        this.terms = terms == null ? null : terms.getId();
    }

    public String getCaption(){
        if (company == null || company.isEmpty()){
            return firstName + " " + lastName;
        }
        return company;
    }

    public String getBillZip() {
        return billZip;
    }

    public void setBillZip(String billZip) {
        this.billZip = billZip;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillAddress2() {
        return billAddress2;
    }

    public void setBillAddress2(String billAddress2) {
        this.billAddress2 = billAddress2;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}