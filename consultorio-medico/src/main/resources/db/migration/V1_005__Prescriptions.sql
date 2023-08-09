CREATE TABLE IF NOT EXISTS `prescriptions` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `patient_id` INT NOT NULL,
    `doctor_id` INT NOT NULL,
    `register_date` DATE NOT NULL,
    `description` VARCHAR(500),
    PRIMARY KEY (`id`, `patient_id`, `doctor_id`),
    CONSTRAINT `fk_prescriptions_patient` FOREIGN KEY (`patient_id`) REFERENCES `ConsultingRoomIWA`.`patients` (`id`),
    CONSTRAINT `fk_prescriptions_doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `ConsultingRoomIWA`.`doctors` (`id`))
    ENGINE = InnoDB;