-- update SALESSAAS_PRODUCT set CATEGORY_ID = <default_value> where CATEGORY_ID is null ;
alter table SALESSAAS_PRODUCT alter column CATEGORY_ID set not null ;
update SALESSAAS_PRODUCT set COST = 0 where COST is null ;
alter table SALESSAAS_PRODUCT alter column COST set not null ;
