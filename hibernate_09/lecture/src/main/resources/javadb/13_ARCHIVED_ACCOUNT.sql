-------------------------------------------------------------------------------------------
-- ARCHIVED ACCOUNT
-------------------------------------------------------------------------------------------
CREATE TABLE ARCHIVED_ACCOUNT
(
  ACCOUNT_ID		        BIGINT		    NOT NULL,
  CREATION_DATE		        TIMESTAMP	    NOT NULL,
  BALANCE		            BIGINT		    NOT NULL
);


-------------
--PRIMARY KEY
-------------

ALTER TABLE ARCHIVED_ACCOUNT ADD CONSTRAINT ARCH_ACCT_PK PRIMARY KEY
    (ACCOUNT_ID);
