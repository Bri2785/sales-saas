package com.bi.salessaas.entity;

import com.haulmont.addon.sdbmt.entity.StandardTenantEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@NamePattern("%s|typeName")
@Table(name = "SALESSAAS_JOB_TYPE")
@Entity(name = "salessaas_JobType")
public class JobType extends StandardTenantEntity {
    private static final long serialVersionUID = -3690802550425022147L;

    @Column(name = "TYPE_NAME", unique = true)
    protected String typeName;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "HOURLY_WAGE")
    protected BigDecimal hourlyWage;

    @NumberFormat(pattern = "$#,##0.00")
    @Column(name = "LABOR_WAGE")
    protected BigDecimal laborWage;

    @NumberFormat(pattern = "#%")
    @Column(name = "MARKUP")
    protected Double markup;

    @NumberFormat(pattern = "#%")
    @Column(name = "REMOVAL_PERCENT")
    protected Double removalPercent;

    public Double getRemovalPercent() {
        return removalPercent;
    }

    public void setRemovalPercent(Double removalPercent) {
        this.removalPercent = removalPercent;
    }

    public Double getMarkup() {
        return markup;
    }

    public void setMarkup(Double markup) {
        this.markup = markup;
    }

    public BigDecimal getLaborWage() {
        return laborWage;
    }

    public void setLaborWage(BigDecimal laborWage) {
        this.laborWage = laborWage;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}