--------------------------------------------------------
--  File created - Wednesday-December-18-2019   
--------------------------------------------------------
REM INSERTING into ROLE
SET DEFINE OFF;
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (1,'ADMIN','Administrador',1);
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (2,'AWARD','Adjudicación',1);
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (3,'REQUI','Requisición',1);
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (4,'AWREQ','Adjudicación/Requisición',1);

REM INSERTING into ADMINISTRATIVEUNIT
SET DEFINE OFF;
Insert into ADMINISTRATIVEUNIT (ADMINISTRATIVEUNITID,CODE,DESCRIPTION,ACTIVE) values (1,'UNIDAD1','UNIDAD ADMINISTRATIVA 1',1);

REM INSERTING into USERDATA
SET DEFINE OFF;
Insert into USERDATA (USERDATAID,NICKNAME,NAME,PASSWORD,ACTIVE) values (1,'admin','Administrator','$2a$10$jzL596SqJa3Gp7kcl6bsFe2aidpDEFdUqDHzHa/tA70EyDHHpK59q',1);

REM INSERTING into USERDATA_ADMINISTRATIVEUNIT
SET DEFINE OFF;
Insert into USERDATA_ADMINISTRATIVEUNIT (USERDATAID,ADMINISTRATIVEUNITID) values (1,1);

REM INSERTING into USERDATA_ROLE
SET DEFINE OFF;
Insert into USERDATA_ROLE (USERDATAID,ROLEID) values (1,1);


