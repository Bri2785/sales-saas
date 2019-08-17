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
    --
    primary key (ID)
);