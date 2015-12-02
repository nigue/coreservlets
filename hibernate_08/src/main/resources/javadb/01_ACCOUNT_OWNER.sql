-------------------------------------------------------------------------------------------
-- ACCOUNT_OWNER
-------------------------------------------------------------------------------------------
CREATE TABLE ACCOUNT_OWNER
(
  ACCOUNT_OWNER_ID          BIGINT		    NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  LAST_NAME                 VARCHAR(50)	    NOT NULL,
  FIRST_NAME                VARCHAR(50)	    NOT NULL,
  SOCIAL_SECURITY_NUMBER    VARCHAR(50)	    NOT NULL,
  STREET_ADDRESS            VARCHAR(50)	    NOT NULL,
  CITY                   	VARCHAR(50)	    NOT NULL,
  STATE                  	VARCHAR(50)	    NOT NULL,
  ZIP_CODE                	VARCHAR(10)	    NOT NULL,
  ZIP_PLUS_FOUR				VARCHAR(4)		NOT NULL,
  HOME_PHONE            	VARCHAR(20),
  CELL_PHONE           		VARCHAR(20),
  EMAIL						VARCHAR(50)    	NOT NULL,
  VERSION					BIGINT			NOT NULL
);


-------------
--PRIMARY KEY
-------------

ALTER TABLE ACCOUNT_OWNER ADD CONSTRAINT ACCTOWNER_PK PRIMARY KEY
    (ACCOUNT_OWNER_ID);

-------------
--UNIQUE KEYS
-------------

ALTER TABLE ACCOUNT_OWNER ADD CONSTRAINT ACCTOWNER_SOCSECNUM_UK UNIQUE
    (SOCIAL_SECURITY_NUMBER);

ALTER TABLE ACCOUNT_OWNER ADD CONSTRAINT ACCTOWNER_EMAIL_UK UNIQUE
    (EMAIL);
