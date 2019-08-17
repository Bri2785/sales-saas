create table SALESSAAS_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CUSTOMER_ID varchar(36),
    TYPE_ID varchar(36),
    ORDER_TYPE integer,
    SALES_PERSON_ID varchar(36),
    SHIPPING_RATE double precision,
    DISCOUNT decimal(19, 2),
    NOTES varchar(1000),
    STATUS integer,
    DATE_SOLD timestamp,
    --
    primary key (ID)
);