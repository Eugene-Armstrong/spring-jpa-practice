CREATE TABLE `tw_company` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name`         VARCHAR(20)  NOT NULL,
  `created_date` TIMESTAMP DEFAULT NOW()
);
