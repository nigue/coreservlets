-------------------------------------------------------------------------------------------
-- ACCOUNT_TRANSACTION
-------------------------------------------------------------------------------------------
CREATE TABLE ACCOUNT_TRANSACTION
(
  ACCOUNT_TRANSACTION_ID	BIGINT		    NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  ACCOUNT_ID			    BIGINT          NOT NULL,
  TRANSACTION_TYPE		    VARCHAR(200)	NOT NULL,
  TXDATE			        TIMESTAMP	    NOT NULL,
  AMOUNT			        NUMERIC(10,2)	NOT NULL
);


-------------
--PRIMARY KEY
-------------

ALTER TABLE ACCOUNT_TRANSACTION ADD CONSTRAINT TX_PK PRIMARY KEY
    (ACCOUNT_TRANSACTION_ID);


--------------
--FOREIGN KEYS
--------------
ALTER TABLE ACCOUNT_TRANSACTION ADD CONSTRAINT TX_ACCTID_ACCT_FK FOREIGN KEY
    (ACCOUNT_ID)
    REFERENCES ACCOUNT (ACCOUNT_ID);
    