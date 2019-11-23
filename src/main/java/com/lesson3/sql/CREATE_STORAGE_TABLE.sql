CREATE TABLE STORAGE (
  ID NUMBER,
  CONSTRAINT STORAGE_ONE_PK PRIMARY KEY (ID),
  FORMAT_SUPPORTED NVARCHAR2(100) NOT NULL,
  STORAGE_COUNTRY NVARCHAR2(100) NOT NULL,
  STORAGE_SIZE NUMBER NOT NULL
  );