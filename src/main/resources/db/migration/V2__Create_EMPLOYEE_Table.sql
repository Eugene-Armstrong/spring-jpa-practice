CREATE TABLE `tw_employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `company_id` BIGINT,
  `name` VARCHAR(30)  NOT NULL,
  `gender` VARCHAR(20)  NOT NULL,
  `created_date` TIMESTAMP DEFAULT NOW()
);
