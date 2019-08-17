-- update SALESSAAS_ORDER set TYPE_ID = <default_value> where TYPE_ID is null ;
alter table SALESSAAS_ORDER alter column TYPE_ID set not null ;
update SALESSAAS_ORDER set ORDER_TYPE = 10 where ORDER_TYPE is null ;
alter table SALESSAAS_ORDER alter column ORDER_TYPE set not null ;
