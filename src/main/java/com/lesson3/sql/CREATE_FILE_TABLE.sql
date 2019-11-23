CREATE TABLE FILE (
  ID NUMBER,
  CONSTRAINT FILE_ONE_PK PRIMARY KEY (ID),
  NAME NVARCHAR2(10) NOT NULL,
  FORMAT_FILE NVARCHAR2(100) NOT NULL,
  FILE_SIZE NUMBER NOT NULL,
  STORAGE_ONE NUMBER,
  CONSTRAINT STORAGE_ONE_FK FOREIGN KEY (STORAGE_ONE) REFERENCES STORAGE_ONE (ID)
  );