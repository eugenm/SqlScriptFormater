create table ELAN_ROLLEN(
  C_ROLLE        VARCHAR2(36) not null,
  C_DESCRIPTION VARCHAR2(254),
  C_PK          VARCHAR2(36) not null,
  constraint PK_ROLEMASTER primary key (C_PK),
  c_table_lut VARCHAR(25)
);