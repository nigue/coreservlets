-------------------------------------------------------------------------------------------
-- ACCOUNT
-------------------------------------------------------------------------------------------
CREATE TABLE ACCOUNT
(
  ACCOUNT_ID		        BIGINT		    NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  ACCOUNT_TYPE		        VARCHAR(200)    NOT NULL,
  CREATION_DATE		        TIMESTAMP	    NOT NULL,
  BALANCE		            BIGINT		    NOT NULL,
  CHECK_STYLE				VARCHAR(200),
  INTEREST_RATE				BIGINT,
  MATURITY_DATE				TIMESTAMP,
  VERSION					BIGINT			NOT NULL
);


-------------
--PRIMARY KEY
-------------

ALTER TABLE ACCOUNT ADD CONSTRAINT ACCT_PK PRIMARY KEY
    (ACCOUNT_ID);
