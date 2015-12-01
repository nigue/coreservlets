INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE, CHECK_STYLE, INTEREST_RATE, MATURITY_DATE, VERSION)
	VALUES(1, 'CHECKING', CURRENT_TIMESTAMP, 1000, 'COMPUTERS', NULL, NULL, 0);

INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE, CHECK_STYLE, INTEREST_RATE, MATURITY_DATE, VERSION)
	VALUES(2, 'CHECKING', CURRENT_TIMESTAMP, 2000, 'COMPUTERS', NULL, NULL, 0);

INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE, CHECK_STYLE, INTEREST_RATE, MATURITY_DATE, VERSION)
	VALUES(3, 'CHECKING', CURRENT_TIMESTAMP, 3000, 'COMPUTERS', NULL, NULL, 0);

INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE, CHECK_STYLE, INTEREST_RATE, MATURITY_DATE, VERSION)
	VALUES(4, 'CHECKING', CURRENT_TIMESTAMP, 4000, 'COMPUTERS', NULL, NULL, 0);

INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE, CHECK_STYLE, INTEREST_RATE, MATURITY_DATE, VERSION)
	VALUES(5, 'SAVINGS', CURRENT_TIMESTAMP, 5000, 'COMPUTERS', 4, CURRENT_TIMESTAMP, 0);

INSERT INTO EBILLER(EBILLER_ID, NAME, BILLING_ADDRESS, PHONE)
	VALUES(1, 'VISA', 'TEST', 'TEST');

INSERT INTO EBILLER(EBILLER_ID, NAME, BILLING_ADDRESS, PHONE)
	VALUES(2, 'MASTERCARD', 'TEST', 'TEST');

INSERT INTO EBILLER(EBILLER_ID, NAME, BILLING_ADDRESS, PHONE)
	VALUES(3, 'DISCOVER', 'TEST', 'TEST');

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 100, 0, NULL, NULL);

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(2, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 200, 0, NULL, NULL);

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(3, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 300, 0, NULL, NULL);

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(4, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 400, 0, NULL, NULL);

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(5, 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 500, 0, NULL, NULL);

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(6, 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 600, 0, NULL, NULL);

INSERT INTO EBILL(EBILL_ID, EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE, AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID)
	VALUES(7, 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 700, 0, NULL, NULL);

INSERT INTO ACCOUNT_OWNER(ACCOUNT_OWNER_ID, LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER, STREET_ADDRESS, CITY, STATE, ZIP_CODE, ZIP_PLUS_FOUR, HOME_PHONE, CELL_PHONE, EMAIL, VERSION)
	VALUES(1, 'HALL', 'MARTY', '111-11-1111', 'TEST', 'TEST', 'MD', '12345', '6789', NULL, NULL, 'martyhall@coreservlets.com', 0);

INSERT INTO ACCOUNT_OWNER(ACCOUNT_OWNER_ID, LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER, STREET_ADDRESS, CITY, STATE, ZIP_CODE, ZIP_PLUS_FOUR, HOME_PHONE, CELL_PHONE, EMAIL, VERSION)
	VALUES(2, 'CHERRY', 'MATT', '222-22-2222', 'TEST', 'TEST', 'MD', '12345', '6789', NULL, NULL, 'mattcherry@coreservlets.com', 0);

INSERT INTO ACCOUNT_OWNER(ACCOUNT_OWNER_ID, LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER, STREET_ADDRESS, CITY, STATE, ZIP_CODE, ZIP_PLUS_FOUR, HOME_PHONE, CELL_PHONE, EMAIL, VERSION)
	VALUES(3, 'MOUSE', 'MICKEY', '333-33-3333', 'TEST', 'TEST', 'MD', '12345', '6789', NULL, NULL, 'mickeymouse@disney.com', 0);

INSERT INTO ACCOUNT_OWNER(ACCOUNT_OWNER_ID, LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER, STREET_ADDRESS, CITY, STATE, ZIP_CODE, ZIP_PLUS_FOUR, HOME_PHONE, CELL_PHONE, EMAIL, VERSION)
	VALUES(4, 'MOUSE', 'MINNIE', '444-44-4444', 'TEST', 'TEST', 'MD', '67890', '1234', NULL, NULL, 'minniemouse@disney.com', 0);

INSERT INTO ACCOUNT_OWNER(ACCOUNT_OWNER_ID, LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER, STREET_ADDRESS, CITY, STATE, ZIP_CODE, ZIP_PLUS_FOUR, HOME_PHONE, CELL_PHONE, EMAIL, VERSION)
	VALUES(5, 'DUCK', 'DONALD', '555-55-5555', 'TEST', 'TEST', 'MD', '67890', '1234', NULL, NULL, 'donaldduck@disney.com', 0);

INSERT INTO ACCOUNT_OWNER(ACCOUNT_OWNER_ID, LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER, STREET_ADDRESS, CITY, STATE, ZIP_CODE, ZIP_PLUS_FOUR, HOME_PHONE, CELL_PHONE, EMAIL, VERSION)
	VALUES(6, 'HAMILTON', 'MARK', '666-66-6666', 'TEST', 'TEST', 'MD', '67890', '1234', NULL, NULL, 'markhamilton@test.com', 0);

INSERT INTO ACCOUNT_ACCOUNT_OWNER(ACCOUNT_ACCOUNT_OWNER_ID, ACCOUNT_ID, ACCOUNT_OWNER_ID)
	VALUES(1, 1, 1);

INSERT INTO ACCOUNT_ACCOUNT_OWNER(ACCOUNT_ACCOUNT_OWNER_ID, ACCOUNT_ID, ACCOUNT_OWNER_ID)
	VALUES(2, 2, 2);

INSERT INTO ACCOUNT_ACCOUNT_OWNER(ACCOUNT_ACCOUNT_OWNER_ID, ACCOUNT_ID, ACCOUNT_OWNER_ID)
	VALUES(3, 3, 3);

INSERT INTO ACCOUNT_ACCOUNT_OWNER(ACCOUNT_ACCOUNT_OWNER_ID, ACCOUNT_ID, ACCOUNT_OWNER_ID)
	VALUES(4, 4, 4);

INSERT INTO ACCOUNT_ACCOUNT_OWNER(ACCOUNT_ACCOUNT_OWNER_ID, ACCOUNT_ID, ACCOUNT_OWNER_ID)
	VALUES(5, 5, 5);

INSERT INTO ACCOUNT_ACCOUNT_OWNER(ACCOUNT_ACCOUNT_OWNER_ID, ACCOUNT_ID, ACCOUNT_OWNER_ID)
	VALUES(6, 5, 6);

INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TRANSACTION_ID, ACCOUNT_ID, TRANSACTION_TYPE, TXDATE, AMOUNT)
	VALUES(1, 1, 'CREDIT', CURRENT_TIMESTAMP, 100);

INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TRANSACTION_ID, ACCOUNT_ID, TRANSACTION_TYPE, TXDATE, AMOUNT)
	VALUES(2, 1, 'CREDIT', CURRENT_TIMESTAMP, 200);

INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TRANSACTION_ID, ACCOUNT_ID, TRANSACTION_TYPE, TXDATE, AMOUNT)
	VALUES(3, 2, 'CREDIT', CURRENT_TIMESTAMP, 100);

INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TRANSACTION_ID, ACCOUNT_ID, TRANSACTION_TYPE, TXDATE, AMOUNT)
	VALUES(4, 2, 'CREDIT', CURRENT_TIMESTAMP, 500);

INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TRANSACTION_ID, ACCOUNT_ID, TRANSACTION_TYPE, TXDATE, AMOUNT)
	VALUES(5, 3, 'CREDIT', CURRENT_TIMESTAMP, 500);

INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TRANSACTION_ID, ACCOUNT_ID, TRANSACTION_TYPE, TXDATE, AMOUNT)
	VALUES(6, 4, 'CREDIT', CURRENT_TIMESTAMP, 100);

INSERT INTO PHYSICAL_ACCOUNT_TRANSACTION(PHYSICAL_ACCOUNT_TRX_ID, BANK_NAME, TELLER)
	VALUES(1, 'TEST', NULL);

INSERT INTO PHYSICAL_ACCOUNT_TRANSACTION(PHYSICAL_ACCOUNT_TRX_ID, BANK_NAME, TELLER)
	VALUES(2, 'TEST', NULL);

INSERT INTO PHYSICAL_ACCOUNT_TRANSACTION(PHYSICAL_ACCOUNT_TRX_ID, BANK_NAME, TELLER)
	VALUES(3, 'TEST', NULL);

INSERT INTO PHYSICAL_ACCOUNT_TRANSACTION(PHYSICAL_ACCOUNT_TRX_ID, BANK_NAME, TELLER)
	VALUES(4, 'TEST', NULL);

INSERT INTO PHYSICAL_ACCOUNT_TRANSACTION(PHYSICAL_ACCOUNT_TRX_ID, BANK_NAME, TELLER)
	VALUES(5, 'TEST', NULL);

INSERT INTO PHYSICAL_ACCOUNT_TRANSACTION(PHYSICAL_ACCOUNT_TRX_ID, BANK_NAME, TELLER)
	VALUES(6, 'TEST', NULL);

COMMIT;