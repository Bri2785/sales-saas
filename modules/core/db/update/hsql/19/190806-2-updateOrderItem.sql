-- update SALESSAAS_ORDER_ITEM set ORDER_ID = <default_value> where ORDER_ID is null ;
alter table SALESSAAS_ORDER_ITEM alter column ORDER_ID set not null ;
-- update SALESSAAS_ORDER_ITEM set PRODUCT_ID = <default_value> where PRODUCT_ID is null ;
alter table SALESSAAS_ORDER_ITEM alter column PRODUCT_ID set not null ;
update SALESSAAS_ORDER_ITEM set QUANTITY = 0 where QUANTITY is null ;
alter table SALESSAAS_ORDER_ITEM alter column QUANTITY set not null ;
update SALESSAAS_ORDER_ITEM set RETAIL = 0 where RETAIL is null ;
alter table SALESSAAS_ORDER_ITEM alter column RETAIL set not null ;
