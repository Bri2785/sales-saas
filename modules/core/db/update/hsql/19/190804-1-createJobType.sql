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
    TYPE_NAME varchar(255),
    HOURLY_WAGE decimal(19, 2),
    LABOR_WAGE decimal(19, 2),
    MARKUP double precision,
    REMOVAL_PERCENT double precision,
    --
    primary key (ID)
);