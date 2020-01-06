DROP TABLE IF EXISTS ERS_USER_ROLES CASCADE;
CREATE TABLE ERS_USER_ROLES(
ERS_USER_ROLE_ID INTEGER PRIMARY KEY,
USER_ROLE VARCHAR(10)
);

INSERT INTO ERS_USER_ROLES(ERS_USER_ROLE_ID,USER_ROLE)
VALUES (1,'MANAGER'),(2,'EMPLOYEE');

DROP TABLE IF EXISTS ERS_REIMBURSEMENT_STATUS CASCADE;
CREATE TABLE ERS_REIMBURSEMENT_STATUS(
REIMB_STATUS_ID INTEGER PRIMARY KEY,
REIMB_STATUS VARCHAR(10)
);

INSERT INTO ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID,REIMB_STATUS)
VALUES(1,'PENDING'),(2,'APPROVED'),(3,'DENIED');

DROP TABLE IF EXISTS ERS_REIMBURSEMENT_TYPE CASCADE;
CREATE TABLE ERS_REIMBURSEMENT_TYPE(
REIMB_TYPE_ID INTEGER PRIMARY KEY,
REIMB_TYPE VARCHAR(10)
);

INSERT INTO ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID, REIMB_TYPE)
VALUES(1,'LODGING'),(2,'TRAVEL'),(3,'FOOD'),(4,'OTHER');

DROP TABLE IF EXISTS ERS_USERS CASCADE;
CREATE TABLE ERS_USERS(
ERS_USERS_ID SERIAL PRIMARY KEY,
ERS_USERNAME VARCHAR(50) UNIQUE,
ERS_PASSWORD VARCHAR(50),
USER_FIRST_NAME VARCHAR(100),
USER_LAST_NAME VARCHAR(100),
USER_EMAIL VARCHAR(150) UNIQUE,
USER_ROLE_ID INTEGER REFERENCES ers_user_roles(ERS_USER_ROLE_ID)
);

INSERT INTO ERS_USERS(ERS_USERNAME,ERS_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_ROLE_ID)
VALUES ('PHAOENIX','PASSWORD','ANDREW','MILLER','WINNERS@WINNERS.COM', 2),
('user','password', 'bob','theBuilder', 'wecanfixit@housing.revature.com', 1);

DROP TABLE IF EXISTS ERS_REIMBURSEMENT CASCADE;
CREATE TABLE ERS_REIMBURSEMENT(
REIMB_ID SERIAL PRIMARY KEY,
REIMB_AMOUNT NUMERIC (50,2),
REIMB_SUBMITTED TIMESTAMP,
REIMB_RESOLVED TIMESTAMP,
REIMB_DESCRIPTION VARCHAR(250),
REIMB_RECEIPT BYTEA,
REIMB_AUTHOR INTEGER REFERENCES ers_users(ERS_USERS_ID),
REIMB_RESOLVER INTEGER REFERENCES ers_users(ERS_USERS_ID),
REIMB_STATUS_ID INTEGER REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID),
REIMB_TYPE_ID INTEGER REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID)
);

INSERT INTO ERS_REIMBURSEMENT(REIMB_AMOUNT,REIMB_DESCRIPTION,REIMB_AUTHOR,REIMB_RESOLVER,REIMB_STATUS_ID,REIMB_TYPE_ID)
VALUES(12,'FOR FAVORS',1,1,1,1), (126165,'please work',1,1,3,2),(500,'Reimbursement!!', 2,2,3,2);

SELECT * FROM ers_reimbursement er;
