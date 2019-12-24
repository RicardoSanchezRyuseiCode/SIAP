--------------------------------------------------------
--  File created - Wednesday-December-18-2019   
--------------------------------------------------------
REM INSERTING into ROLE
SET DEFINE OFF;
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (1,'ADMIN','Administrador',1);
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (2,'AWARD','Adjudicaci�n',1);
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (3,'REQUI','Requisici�n',1);
Insert into ROLE (ROLEID,NAME,DESCRIPTION,ACTIVE) values (4,'AWREQ','Adjudicaci�n/Requisici�n',1);

REM INSERTING into ADMINISTRATIVEUNIT
SET DEFINE OFF;
Insert into ADMINISTRATIVEUNIT (ADMINISTRATIVEUNITID,CODE,DESCRIPTION,EMAIL,AUTHORIZER,ACTIVE) values (1,'UNIDAD1','UNIDAD ADMINISTRATIVA 1','sileon@hotmail.com',1,1);

REM INSERTING into USERDATA
SET DEFINE OFF;
Insert into USERDATA (USERDATAID,NICKNAME,NAME,PASSWORD,ACTIVE) values (1,'admin','Administrator','$2a$10$jzL596SqJa3Gp7kcl6bsFe2aidpDEFdUqDHzHa/tA70EyDHHpK59q',1);

REM INSERTING into USERDATA_ADMINISTRATIVEUNIT
SET DEFINE OFF;
Insert into USERDATA_ADMINISTRATIVEUNIT (USERDATAID,ADMINISTRATIVEUNITID) values (1,1);

REM INSERTING into USERDATA_ROLE
SET DEFINE OFF;
Insert into USERDATA_ROLE (USERDATAID,ROLEID) values (1,1);

REM INSERTING into DOCUMENTVARIABLE
SET DEFINE OFF;
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (18,'$ADJ_ORIGEN','com.ryuseicode.siap.entity.award.Adjudication','getSourceOrigin',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (19,'$ADJ_MONTO','com.ryuseicode.siap.entity.award.Adjudication','getAmount',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (20,'$ADJ_TIPO_CONTRATO','com.ryuseicode.siap.entity.award.Adjudication','getContractType',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (21,'$ADJ_MODALIDAD','com.ryuseicode.siap.entity.award.Adjudication','getModality',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (22,'$ADJ_DESCRIPCION','com.ryuseicode.siap.entity.award.Opening','getDenomination',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (23,'$ADJ_FECHA_FIN_EVENTO','com.ryuseicode.siap.entity.award.Opening','getEventDateText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (24,'$ADJ_CUADRO_FECHA_ELABORACION','com.ryuseicode.siap.entity.award.Quotation','getElaborationDateText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (25,'$CONT_RFC_PROVEEDOR','com.ryuseicode.siap.entity.award.Contract','getTaxId',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (26,'$CONT_MAIL_PROVEEDOR','com.ryuseicode.siap.entity.award.Contract','getEmail',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (27,'$CONT_MONTO_MAXIMO','com.ryuseicode.siap.entity.award.Contract','getMaxAmount',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (28,'$CONT_MONTO_MINIMO','com.ryuseicode.siap.entity.award.Contract','getMinAmount',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (29,'$CONT_MONTO_CERRADO','com.ryuseicode.siap.entity.award.Contract','getMaxAmount',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (30,'$CONT_MONTO_LETRA_MAXIMO','com.ryuseicode.siap.entity.award.Contract','getMaxAmountText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (31,'$CONT_MONTO_LETRA_MINIMO','com.ryuseicode.siap.entity.award.Contract','getMinAmountText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (32,'$CONT_MONTO_LETRA_CERRADO','com.ryuseicode.siap.entity.award.Contract','getMaxAmountText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (33,'$CONT_IVA_MONTO_MAXIMO','com.ryuseicode.siap.entity.award.Contract','getMaxAmountIva',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (34,'$CONT_IVA_MONTO_MINIMO','com.ryuseicode.siap.entity.award.Contract','getMinAmountIva',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (35,'$CONT_IVA_MONTO_CERRADO','com.ryuseicode.siap.entity.award.Contract','getMaxAmountIva',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (36,'$CONT_IVA_MONTO_LETRA_MAXIMO','com.ryuseicode.siap.entity.award.Contract','getMaxAmountIvaText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (37,'$CONT_IVA_MONTO_LETRA_MINIMO','com.ryuseicode.siap.entity.award.Contract','getMinAmountIvaText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (38,'$CONT_IVA_MONTO_LETRA_CERRADO','com.ryuseicode.siap.entity.award.Contract','getMaxAmountIvaText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (39,'$CONT_%_ANTICIPO','com.ryuseicode.siap.entity.award.Contract','getDepositPercent',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (40,'$CONT_FIANZA','com.ryuseicode.siap.entity.award.Contract','getDeposit',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (41,'$CONT_ANTICIPO_FIANZA','com.ryuseicode.siap.entity.award.Contract','getDepositInAdvance',1,'double');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (42,'$CONT_LETRA_FIANZA','com.ryuseicode.siap.entity.award.Contract','getDepositText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (43,'$CONT_LETRA_ANTICIPO_FIANZA','com.ryuseicode.siap.entity.award.Contract','getDepositInAdvanceText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (44,'$CONT_INSTITUCION_NUM_ACREDITACION','com.ryuseicode.siap.entity.award.Institution','getAccreditationNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (45,'$CONT_NUMERO_PARTIDAS','com.ryuseicode.siap.entity.award.Contract','getNumberMatch',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (46,'$CONT_DATOS_ID_PERSONA_FISICA','com.ryuseicode.siap.entity.award.Contract','getPhysicalPersonData',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (47,'$CONT_DATOS_ID_ESCRITURA','com.ryuseicode.siap.entity.award.Contract','getLegalDeedNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (48,'$CONT_DATOS_ID_NOTARIO','com.ryuseicode.siap.entity.award.Contract','getLegalNotaryNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (49,'$CONT_DATOS_ID_ESTADO','com.ryuseicode.siap.entity.award.Contract','getLegalState',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (50,'$CONT_DATOS_ID_MUNICIPIO','com.ryuseicode.siap.entity.award.Contract','getLegalCity',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (51,'$CONT_DATOS_ID_FECHA_ESCRITURA','com.ryuseicode.siap.entity.award.Contract','getLegalDateText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (52,'$CONT_DATOS_ID_R_ESCRITURA','com.ryuseicode.siap.entity.award.Contract','getLegalAgentDeedNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (53,'$CONT_DATOS_ID_R_NOTARIO','com.ryuseicode.siap.entity.award.Contract','getLegalAgentNotaryNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (54,'$CONT_DATOS_ID_R_ESTADO','com.ryuseicode.siap.entity.award.Contract','getLegalAgentState',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (55,'$CONT_DATOS_ID_R_MUNICIPIO','com.ryuseicode.siap.entity.award.Contract','getLegalAgentCity',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (56,'$CONT_DATOS_ID_R_FECHA_ESCRITURA','com.ryuseicode.siap.entity.award.Contract','getLegalAgentDateText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (57,'$CONT_FECHA_FIN','com.ryuseicode.siap.entity.award.Contract','getDueDateText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (58,'$CONT_PERSONALIDAD_GANADOR','com.ryuseicode.siap.entity.award.Contract','getWinnerName',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (59,'$CONT_NOM_INSTITUCION','com.ryuseicode.siap.entity.award.Institution','getName',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (60,'$CONT_DIR_INSTITUCION','com.ryuseicode.siap.entity.award.Institution','getAddress',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (61,'$CONT_INSTITUCION_FECHA_CREACION','com.ryuseicode.siap.entity.award.Institution','getCreationDate',1,'LocalDateTime');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (63,'$CONT_REP_CARGO_INSTITUCION','com.ryuseicode.siap.entity.award.Institution','getDesignation',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (64,'$CONT_REP_INSTITUCION','com.ryuseicode.siap.entity.award.Institution','getRepresentative',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (1,'$ADJ_LUGAR_EMISION','com.ryuseicode.siap.entity.award.Emission','getPlace',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (2,'$ADJ_FECHA_EMISION','com.ryuseicode.siap.entity.award.Emission','getEmissionDateText',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (3,'$CONT_NUM_OFICIO','com.ryuseicode.siap.entity.award.Competitor','getTradeNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (4,'$ADJ_TIPO_ADJUDICACION','com.ryuseicode.siap.entity.award.Adjudication','getAdjudicationType',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (5,'$CONT_NOMBRE_PROVEEDOR','com.ryuseicode.siap.entity.award.Supplier','getName',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (6,'$CONT_DIR_PROVEEDOR','com.ryuseicode.siap.entity.award.Supplier','getAddress',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (7,'$CONT_DIR_CIUDAD','com.ryuseicode.siap.entity.award.Supplier','getCity',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (8,'$CONT_DIR_ESTADO','com.ryuseicode.siap.entity.award.Supplier','getState',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (9,'$CONT_DIR_CODIGO','com.ryuseicode.siap.entity.award.Supplier','getZipCode',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (10,'$ADJ_NUM_PROCEDIMIENTO','com.ryuseicode.siap.entity.award.Adjudication','getProcedureNumber',1,'String');
Insert into DOCUMENTVARIABLE (DOCUMENTVARIABLEID,VARIABLE,CLASSNAME,METHODNAME,ACTIVE,RETURNTYPE) values (11,'$ADJ_DIAS_PAGO','com.ryuseicode.siap.entity.award.Emission','getDaysForPayment',1,'int');

REM INSERTING into BUDGET
SET DEFINE OFF;
Insert into BUDGET (BUDGETID,ADMINISTRATIVEUNITID,CODE,DESCRIPTION,SEASON,AMOUNT,ACTIVE) values (1,1,'001','Presupuesto 1','2019',100000,1);
Insert into BUDGET (BUDGETID,ADMINISTRATIVEUNITID,CODE,DESCRIPTION,SEASON,AMOUNT,ACTIVE) values (2,1,'002','Presupuesto 2','2019',100000,1);

REM INSERTING into CHAPTER
SET DEFINE OFF;
Insert into CHAPTER (CHAPTERID,BUDGETID,CODE,CONCEPT,AMOUNT,ACTIVE) values (1,1,'001','Capitulo 1',50000,1);
Insert into CHAPTER (CHAPTERID,BUDGETID,CODE,CONCEPT,AMOUNT,ACTIVE) values (2,1,'002','Capitulo 2',50000,1);
Insert into CHAPTER (CHAPTERID,BUDGETID,CODE,CONCEPT,AMOUNT,ACTIVE) values (3,2,'001','Capitulo 3',50000,1);
Insert into CHAPTER (CHAPTERID,BUDGETID,CODE,CONCEPT,AMOUNT,ACTIVE) values (4,2,'002','Capitulo 4',50000,1);

REM INSERTING into ENTRY
SET DEFINE OFF;
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (1,1,'001','Partida 1',25000,25000,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (2,1,'002','Partida 2',25000,0,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (3,2,'001','Partida 1',25000,0,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (4,2,'002','Partida 2',25000,0,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (5,3,'001','Partida 1',25000,0,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (6,3,'002','Partida 2',25000,0,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (7,4,'001','Partida 1',25000,0,1);
Insert into ENTRY (ENTRYID,CHAPTERID,CODE,DESCRIPTION,AMOUNTALLOCATED,AMOUNTUSED,ACTIVE) values (8,4,'002','Partida 2',25000,0,1);



