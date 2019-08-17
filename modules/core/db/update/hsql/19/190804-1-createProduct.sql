create table SALESSAAS_PRODUCT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    TENANT_ID varchar(255),
    --
    CATEGORY_ID varchar(36),
    PRODUCT_CODE varchar(255) not null,
    DESCRIPTION varchar(255),
    TYPE integer not null,
    COST decimal(19, 2),
    STORAGE_PRICE decimal(19, 2),
    INSTALL_MH decimal(19, 2),
    REMOVAL_MH decimal(19, 2),
    INSTALL_CHARGE decimal(19, 2),
    REMOVAL_CHARGE decimal(19, 2),
    --
    primary key (ID)
);