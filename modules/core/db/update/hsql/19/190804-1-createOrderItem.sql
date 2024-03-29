create table SALESSAAS_ORDER_ITEM (
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
    ORDER_ID varchar(36),
    PRODUCT_ID varchar(36),
    QUANTITY double precision,
    COST decimal(19, 2),
    RETAIL decimal(19, 2),
    INSTALL_CHARGE decimal(19, 2),
    REMOVAL_CHARGE decimal(19, 2),
    INSTALL_MH double precision,
    REMOVAL_MH double precision,
    STORAGE decimal(19, 2),
    NOTES varchar(1024),
    USE_MH_FOR_CALC boolean,
    IS_ON_WORK_ORDER boolean,
    IS_ON_INVOICE boolean,
    STATUS integer,
    TAXABLE boolean,
    --
    primary key (ID)
);