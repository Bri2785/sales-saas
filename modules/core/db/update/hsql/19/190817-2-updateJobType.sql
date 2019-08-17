-- update SALESSAAS_JOB_TYPE set TYPE_NAME = <default_value> where TYPE_NAME is null ;
alter table SALESSAAS_JOB_TYPE alter column TYPE_NAME set not null ;
