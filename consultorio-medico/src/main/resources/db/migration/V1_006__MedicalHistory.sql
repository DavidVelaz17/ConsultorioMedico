CREATE TABLE IF NOT EXISTS `medical_history` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `patient_id` INT NOT NULL,
    `height` INT NOT NULL,
    `weight` INT NOT NULL,
    `family_medical_history` TINYINT NOT NULL,
    `specific_family_medical_history` VARCHAR(500) NULL,
    `pathological_history` TINYINT NOT NULL,
    `specific_pathological_history` VARCHAR(500) NULL,
    `non_pathological_history` TINYINT NOT NULL,
    `specific_non_pathological_history` VARCHAR(500) NULL,
    PRIMARY KEY (`id`, `patient_id`),
    CONSTRAINT `fk_medical_history_patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `ConsultingRoomIWA`.`patients` (`id`)
    ) ENGINE = InnoDB;