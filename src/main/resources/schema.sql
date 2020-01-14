DROP TABLE IF EXISTS EVENT;

CREATE TABLE EVENT (
                       ID NUMBER,
                       CONSTRAINT EVENT_PK PRIMARY KEY(ID),
                       HEADER NVARCHAR2(200),
                       START_TIME TIMESTAMP,
                       END_TIME TIMESTAMP,
                       DESCRIPTION NVARCHAR2(500)
);

DROP SEQUENCE IF EXISTS EVENT_SEQ;

CREATE SEQUENCE EVENT_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;