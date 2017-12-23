CREATE DATABASE userDB;
USE userDB;

CREATE TABLE inventory
(
	productID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	vehicleName VARCHAR(30),
    vehicleGenre VARCHAR(30),
    vehicleModel VARCHAR(30),
    vehicleColor VARCHAR(30),
    description VARCHAR(30),
    yearReleased DATE,
    askPrice DEC(8,2),
    sellPrice DEC(8,2)
);

SELECT * FROM inventory;

CREATE TABLE users
(
	userID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    userName VARCHAR(30),
    userPass VARCHAR(30),
    salt CHAR(128),
    sinNum VARCHAR(30),
    phoneNum VARCHAR(30),
    emailAddress VARCHAR(30),
    dob DATE,
    isAdmin BOOLEAN
);

SELECT * FROM users;

INSERT INTO users (firstName, lastName, userName, userPass, sinNum, phoneNum, emailAddress, dob, isAdmin) VALUES ('John', 'Doe', 'johndoe', 'test', '123 456 789', '705-896-9515', 'johndoe@gmail.com', DATE '1992-12-17', TRUE);
INSERT INTO users (firstName, lastName, userName, userPass, sinNum, phoneNum, emailAddress, dob, isAdmin) VALUES ('Jane', 'Doe', 'janedoe', 'test', '123 456 789', '705-896-9505', 'janedoe@gmail.com', DATE'1992-10-12', FALSE);
