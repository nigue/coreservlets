-------------------------------------------------------------------------------------------
-- PHYSICAL_ACCOUNT_TRANSACTION
-------------------------------------------------------------------------------------------
CREATE TABLE PHYSICAL_ACCOUNT_TRANSACTION
(
  PHYSICAL_ACCOUNT_TRX_ID	BIGINT		    NOT NULL,
  BANK_NAME					VARCHAR(4000)	NOT NULL,
  TELLER					VARCHAR(4000)
);


-------------
--PRIMARY KEY
-------------

ALTER TABLE PHYSICAL_ACCOUNT_TRANSACTION ADD CONSTRAINT P_TX_PK PRIMARY KEY
    (PHYSICAL_ACCOUNT_TRX_ID);

--------------
--FOREIGN KEYS
--------------
ALTER TABLE PHYSICAL_ACCOUNT_TRANSACTION ADD CONSTRAINT TX_PHYACNTTRX_ACNTTRX_FK FOREIGN KEY
    (PHYSICAL_ACCOUNT_TRX_ID)
    REFERENCES ACCOUNT_TRANSACTION (ACCOUNT_TRANSACTION_ID);
