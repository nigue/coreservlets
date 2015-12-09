CREATE TRIGGER EBILL_BU AFTER UPDATE 
OF EBILLER_ID, ACCOUNT_ID, DUE_DATE, RECEIVED_DATE, MINIMUM_AMOUNT_DUE, BALANCE,
AMOUNT_PAID, DATE_PAID, ACCOUNT_TRANSACTION_ID
ON EBILL
FOR EACH ROW
UPDATE EBILL SET UPDATE_DATE = CURRENT_TIMESTAMP;