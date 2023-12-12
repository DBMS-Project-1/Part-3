use testdb;
drop table Trees; 
drop table Quotes;

CREATE TABLE Users(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	creditCardNum CHAR(16),
    password VARCHAR(20) NOT NULL,
	email VARCHAR(50) UNIQUE,
    role VARCHAR(20)
);

CREATE TABLE Quotes(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	contractorid INTEGER,
	clientid INTEGER,
	price DOUBLE,
	schedulestart DATETIME,
	scheduleend DATETIME,
    userAccept BOOLEAN,
    davidAccept BOOLEAN,
    userResponse VARCHAR(200),
    davidResponse VARCHAR(200),
	FOREIGN KEY (contractorid) REFERENCES Users(id),
	FOREIGN KEY (clientid) REFERENCES Users(id)
);


CREATE TABLE Trees(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	quoteid INTEGER,
	size DOUBLE,
	height DOUBLE,
	distanceFromHouse DOUBLE,
	FOREIGN KEY(quoteid) REFERENCES Quotes(id)
);

CREATE TABLE Bills(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    quoteid INTEGER,
    amountDue DOUBLE,
    amountPaid DOUBLE,
    paymentDate DATETIME,
    billGeneratedDate DATETIME,
    FOREIGN KEY(quoteid) REFERENCES Quotes(id)
);


INSERT IGNORE INTO Users(id, firstname, lastname, creditCardNum, password, email)
VALUES 
    (1, 'root', 'root', '1234567890123456', 'pass1234', 'root'),
	(2, 'David', 'Smith', '1111222233334444', 'pass1234', 'David Smith'),
	(12, 'Bob', 'Johnson', '2222333344445555', 'bob1234', 'bob.johnson@example.com'),
	(13, 'Eva', 'Williams', '3333444455556666', 'eva1234', 'eva.williams@example.com'),
	(14, 'Michael', 'Brown', '4444555566667777', 'michael1234', 'michael.brown@example.com'),
	(15, 'Olivia', 'Davis', '5555666677778888', 'olivia1234', 'olivia.davis@example.com'),
	(16, 'Daniel', 'Taylor', '6666777788889999', 'daniel1234', 'daniel.taylor@example.com'),
	(17, 'Sophia', 'Anderson', '7777888899990000', 'sophia1234', 'sophia.anderson@example.com'),
	(18, 'William', 'Moore', '8888999900001111', 'william1234', 'william.moore@example.com'),
	(19, 'Emily', 'Clark', '9999000011112222', 'emily1234', 'emily.clark@example.com');

insert into Quotes(contractorid, clientid) values
(2, 4);
insert into Trees(quoteid) values
(4);

select * from Users;  
select * from Trees;
select * from Quotes;   

drop table Trees; 
drop table Quotes;

delete from Trees;
delete from Quotes;