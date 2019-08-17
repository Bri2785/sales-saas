alter table SALESSAAS_ORDER_ITEM alter column REMOVAL_MH rename to REMOVAL_MH__U32332 ^
alter table SALESSAAS_ORDER_ITEM alter column INSTALL_MH rename to INSTALL_MH__U51011 ^
alter table SALESSAAS_ORDER_ITEM alter column QUANTITY rename to QUANTITY__U85204 ^
alter table SALESSAAS_ORDER_ITEM alter column QUANTITY__U85204 set null ;
alter table SALESSAAS_ORDER_ITEM add column QUANTITY integer ^
update SALESSAAS_ORDER_ITEM set QUANTITY = 0 where QUANTITY is null ;
alter table SALESSAAS_ORDER_ITEM alter column QUANTITY set not null ;
alter table SALESSAAS_ORDER_ITEM add column INSTALL_MH decimal(19, 2) ;
alter table SALESSAAS_ORDER_ITEM add column REMOVAL_MH decimal(19, 2) ;
