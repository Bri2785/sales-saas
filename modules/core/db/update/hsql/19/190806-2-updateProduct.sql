update SALESSAAS_PRODUCT set BASE_RETAIL = 0 where BASE_RETAIL is null ;
alter table SALESSAAS_PRODUCT alter column BASE_RETAIL set not null ;
