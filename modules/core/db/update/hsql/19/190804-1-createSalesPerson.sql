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
);