alter table SALESSAAS_ORDER alter column DISCOUNT_PERCENT rename to DISCOUNT_PERCENT__U49042 ^
alter table SALESSAAS_ORDER alter column SHIPPING_RATE rename to SHIPPING_RATE__U27396 ^
alter table SALESSAAS_ORDER add column SHIPPING_RATE decimal(19, 2) ;
alter table SALESSAAS_ORDER add column DISCOUNT_PERCENT decimal(19, 2) ;
