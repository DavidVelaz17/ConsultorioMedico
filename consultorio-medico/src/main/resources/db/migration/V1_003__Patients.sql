CREATE TABLE IF NOT EXISTS `patients` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `date_of_birth` date NOT NUll,
    `gender` int NOT NULL,
    `rfc` varchar(13) NOT NULL,
    `address` varchar(500) NOT NULL,
    `city` varchar(500) NOT NULL,
    `phone_number` bigint NOT NULL,
    `email` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;