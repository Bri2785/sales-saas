package com.bi.salessaas.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s|categoryName")
@Table(name = "SALESSAAS_CATEGORY")
@Entity(name = "salessaas_Category")
public class Category extends StandardEntity {
    private static final long serialVersionUID = -5601114263053631093L;

    @NotNull
    @Column(name = "CATEGORY_NAME", nullable = false, unique = true)
    protected String categoryName;

    @Column(name = "DESCRIPTION")
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}