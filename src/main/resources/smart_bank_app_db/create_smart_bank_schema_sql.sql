DROP SCHEMA `smart_bank`;
CREATE SCHEMA `smart_bank`;
USE `smart_bank`;

CREATE TABLE `User` (
`user_id` int NOT NULL AUTO_INCREMENT,
`user_name` varchar(45) NOT NULL,
`user_type` varchar(45) NOT NULL CHECK(`user_type` IN ('NEW_USER', 'REWARD_USER', 'PLATINUM_USER', 'VIP')),
`password` varchar(45) NOT NULL CHECK(LENGTH(`password`) >= 6),
`first_name` varchar(45) NOT NULL,
`last_name` varchar(45) NOT NULL,
`phone` varchar(45) NOT NULL,
`email` varchar(45) NOT NULL,
`credit_score` int NOT NULL DEFAULT 300,
`annual_income` decimal(10,2) NOT NULL CHECK(`annual_income` >= 0),
`loan_amount` decimal(10,2) NOT NULL DEFAULT 0.00,
`registration_date` date NOT NULL DEFAULT '2024-10-30',
PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Account` (
`account_id` int NOT NULL AUTO_INCREMENT,
`user_id` int NOT NULL,
`account_type` varchar(45) NOT NULL,
`balance` decimal(10,2) NOT NULL DEFAULT 0 CHECK (`balance` >= 0),
PRIMARY KEY(`account_id`),
CONSTRAINT `fk_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `LoanApplication` (
`application_id` int NOT NULL AUTO_INCREMENT,
`user_id` int NOT NULL,
`loan_type` varchar(45) NOT NULL,
`amount` decimal(10,2) NOT NULL CHECK(`amount` >= 0),
`start_date` date NOT NULL DEFAULT '2024-10-30',
`end_date` date NOT NULL DEFAULT '2025-12-31',
`application_status` varchar(45) NOT NULL DEFAULT 'pending' CHECK(`application_status` IN ('pending', 'declined', 'approved')),
`application_date` date NOT NULL DEFAULT '2024-10-30',
PRIMARY KEY(`application_id`),
CONSTRAINT `fk_loan_application_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Transaction` (
`transaction_id` int NOT NULL AUTO_INCREMENT,
`account_id` int NOT NULL,
`transaction_type` varchar(45) NOT NULL CHECK(`transaction_type` IN ('deposit', 'withdrawal')),
`transaction_amount` decimal(10,2) NOT NULL DEFAULT 0 CHECK(`transaction_amount` >= 0),
`transaction_date` date NOT NULL DEFAULT '2024-10-30',
`account_balance_before` double NOT NULL DEFAULT 0,
`account_balance_after` double NOT NULL DEFAULT 0,
PRIMARY KEY(`transaction_id`),
CONSTRAINT `fk_transaction_account_id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`account_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;