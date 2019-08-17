alter table SALESSAAS_YEAR alter column NAME rename to NAME__U68896 ^
alter table SALESSAAS_YEAR alter column NAME__U68896 set null ;
alter table SALESSAAS_YEAR add column NAME varchar(6) ^
update SALESSAAS_YEAR set NAME = '' where NAME is null ;
alter table SALESSAAS_YEAR alter column NAME set not null ;
