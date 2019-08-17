-- begin SALESSAAS_CUSTOMER
create table SALESSAAS_CUSTOMER (
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
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    COMPANY varchar(255),
    BILL_ADDRESS varchar(255),
    BILL_ADDRESS2 varchar(255),
    BILL_CITY varchar(255),
    BILL_STATE varchar(255),
    BILL_ZIP varchar(255),
    JOB_ADDRESS1 varchar(255),
    JOB_ADDRESS2 varchar(255),
    JOB_ADDRESS3 varchar(255),
    JOB_CITY varchar(255),
    JOB_STATE varchar(255),
    JOB_ZIP varchar(255),
    HOME_PHONE varchar(255),
    WORK_PHONE varchar(255),
    CELL_PHONE varchar(255),
    FAX varchar(255),
    CONTACT varchar(255),
    CONTACT_PHONE varchar(255),
    EMAIL varchar(255),
    REFERRED_BY varchar(255),
    TAX_RATE double precision,
    TERMS integer,
    NOTES varchar(1024),
    --
    primary key (ID)
)^
-- end SALESSAAS_CUSTOMER
-- begin SALESSAAS_CATEGORY
create table SALESSAAS_CATEGORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CATEGORY_NAME varchar(255) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end SALESSAAS_CATEGORY
-- begin SALESSAAS_ORDER
create table SALESSAAS_ORDER (
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
    CUSTOMER_ID varchar(36),
    TYPE_ID varchar(36) not null,
    ORDER_TYPE integer not null,
    SEASON_ID varchar(36) not null,
    SALES_PERSON_ID varchar(36),
    SHIPPING_RATE decimal(19, 2),
    SHIPPING_AMOUNT decimal,
    IS_DISCOUNTED boolean,
    DISCOUNT decimal,
    DISCOUNT_PERCENT decimal(19, 2),
    IS_INSTALL_TAXABLE boolean,
    IS_REMOVAL_TAXABLE boolean,
    IS_STORAGE_TAXABLE boolean,
    NOTES varchar(1000),
    STATUS integer,
    DATE_ENTERED timestamp,
    DATE_SOLD timestamp,
    INSTALL_INSTRUCTIONS varchar(1024),
    REMOVAL_INSTRUCTIONS varchar(1024),
    DRIVING_DIRECTIONS varchar(1024),
    INSTALL_DATE timestamp,
    REMOVAL_DATE timestamp,
    USE_REMOVAL_PERCENT boolean,
    TOTAL_PRODUCT decimal,
    TOTAL_STORAGE decimal,
    TOTAL_INSTALL decimal,
    TOTAL_REMOVAL decimal,
    TOTAL_TAX decimal,
    GRAND_TOTAL decimal,
    IS_INSTALLED boolean,
    IS_REMOVED boolean,
    SOURCE_ID varchar(36),
    PO_NUMBER varchar(255),
    OVERRIDE_STORAGE boolean,
    OVERRIDE_INSTALL boolean,
    OVERRIDE_REMOVAL boolean,
    --
    primary key (ID)
)^
-- end SALESSAAS_ORDER
-- begin SALESSAAS_JOB_TYPE
create table SALESSAAS_JOB_TYPE (
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
    TYPE_NAME varchar(255) not null,
    HOURLY_WAGE decimal,
    LABOR_WAGE decimal,
    MARKUP double precision,
    REMOVAL_PERCENT double precision,
    --
    primary key (ID)
)^
-- end SALESSAAS_JOB_TYPE
-- begin SALESSAAS_SALES_PERSON
create table SALESSAAS_SALES_PERSON (
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
    SALES_PERSON_NAME varchar(255) not null,
    TITLE varchar(255),
    CONTACT_NUMBER varchar(255),
    EMAIL_ADDRESS varchar(255),
    --
    primary key (ID)
)^
-- end SALESSAAS_SALES_PERSON
-- begin SALESSAAS_ORDER_ITEM
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
    ORDER_ID varchar(36) not null,
    SORT_ORDER integer,
    PRODUCT_ID varchar(36) not null,
    QUANTITY integer not null,
    COST decimal,
    RETAIL decimal not null,
    TOTAL_PRICE decimal,
    INSTALL_CHARGE decimal,
    REMOVAL_CHARGE decimal,
    INSTALL_MH decimal(19, 2),
    REMOVAL_MH decimal(19, 2),
    STORAGE decimal,
    NOTES varchar(1024),
    USE_MH_FOR_CALC boolean,
    IS_ON_WORK_ORDER boolean,
    IS_ON_INVOICE boolean,
    STATUS integer,
    TAXABLE boolean,
    INVOICED_STATUS integer,
    --
    primary key (ID)
)^
-- end SALESSAAS_ORDER_ITEM
-- begin SALESSAAS_PRODUCT
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
    CATEGORY_ID varchar(36) not null,
    PRODUCT_CODE varchar(255) not null,
    DESCRIPTION varchar(255),
    TYPE integer not null,
    BASE_RETAIL decimal(19, 2) not null,
    COST decimal(19, 2) not null,
    STORAGE_PRICE decimal(19, 2),
    INSTALL_MH decimal(19, 2),
    REMOVAL_MH decimal(19, 2),
    INSTALL_CHARGE decimal(19, 2),
    REMOVAL_CHARGE decimal(19, 2),
    --
    primary key (ID)
)^
-- end SALESSAAS_PRODUCT
-- begin SALESSAAS_YEAR
create table SALESSAAS_YEAR (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(6) not null,
    --
    primary key (ID)
)^
-- end SALESSAAS_YEAR
-- begin SALESSAAS_COMPANY
create table SALESSAAS_COMPANY (
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
    NAME varchar(255) not null,
    PROPOSAL_ACCEPTANCE_TERMS varchar(2000),
    ADDRESS1 varchar(255),
    ADDRESS2 varchar(255),
    ADDRESS3 varchar(255),
    CITY varchar(255),
    STATE varchar(255),
    ZIP varchar(255),
    PHONE varchar(255),
    FAX varchar(255),
    WEBSITE varchar(255),
    TAXRATE double precision,
    IS_TAX_OVERRIDEN_BY_CUSTOMER boolean,
    LOGO_FILE_ID varchar(36),
    --
    primary key (ID)
)^
-- end SALESSAAS_COMPANY
-- begin SALESSAAS_SOURCE
create table SALESSAAS_SOURCE (
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
    SOURCE varchar(255),
    --
    primary key (ID)
)^
-- end SALESSAAS_SOURCE
-- begin SYS_FILE
alter table SYS_FILE add column TENANT_ID varchar(255) ^
alter table SYS_FILE add column DTYPE varchar(100) ^
update SYS_FILE set DTYPE = 'salessaas_TenantFileDescriptor' where DTYPE is null ^
-- end SYS_FILE
