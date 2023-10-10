CREATE TABLE IF NOT EXISTS `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_patient` INT NULL,
    `id_doctor` INT NULL,
    `email` VARCHAR(45) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `role` INT NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb3;