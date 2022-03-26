
DROP TABLE IF EXISTS users;
CREATE TABLE users (
        id INT AUTO_INCREMENT  PRIMARY KEY,
	fullname CHAR(100) NOT NULL ,
        email CHAR(50) NOT NULL ,
	password varchar(300) NOT NULL,
        access_token varchar(500) not null,
        ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE INDEX email_uniq ON users (email);


DROP TABLE IF EXISTS notes;
CREATE TABLE `notes` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`title` CHAR(50) NOT NULL,
	`body` VARCHAR(10000) NOT NULL,
	`tag` VARCHAR(200) NOT NULL,
        `visibility` ENUM('PUBLIC','PRIVATE') NOT NULL,
        `user_id` INT NOT NULL,
        `create_time` TIMESTAMP not null,
	`ts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
