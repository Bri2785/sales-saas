alter table SALESSAAS_CUSTOMER alter column LOGO_FILE_ID rename to LOGO_FILE_ID__U68493 ^
alter table SALESSAAS_CUSTOMER drop constraint FK_SALESSAAS_CUSTOMER_ON_LOGO_FILE ;
drop index IDX_SALESSAAS_CUSTOMER_ON_LOGO_FILE ;
