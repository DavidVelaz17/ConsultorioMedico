CREATE TABLE IF NOT EXISTS `appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patients_id` int NOT NULL,
  `doctors_id` int NOT NULL,
  `date` date NOT NULL,
  `hour` int NOT NULL,
  `notes` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`,`patients_id`,`doctors_id`),
  KEY `fk_appointments_patients1_idx` (`patients_id`),
  KEY `fk_appointments_doctors1_idx` (`doctors_id`),
  CONSTRAINT `fk_appointments_doctors1` FOREIGN KEY (`doctors_id`) REFERENCES `doctors` (`id`),
  CONSTRAINT `fk_appointments_patients1` FOREIGN KEY (`patients_id`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;