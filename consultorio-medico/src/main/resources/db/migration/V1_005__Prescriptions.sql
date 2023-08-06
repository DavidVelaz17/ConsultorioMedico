CREATE TABLE IF NOT EXISTS `prescriptions` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `patients_id` INT NOT NULL,
    `doctors_id` INT NOT NULL,
    `date` DATE NOT NULL,
    `description` VARCHAR(500),
    PRIMARY KEY (`id`, `patients_id`, `doctors_id`),
    CONSTRAINT `fk_prescriptions_patients` FOREIGN KEY (`patients_id`) REFERENCES `ConsultingRoomIWA`.`patients` (`id`),
    CONSTRAINT `fk_prescriptions_doctors1` FOREIGN KEY (`doctors_id`) REFERENCES `ConsultingRoomIWA`.`doctors` (`id`))
    ENGINE = InnoDB;