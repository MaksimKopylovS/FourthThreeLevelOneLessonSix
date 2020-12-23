CREATE SCHEMA `magproduct` ;

CREATE TABLE `magproduct`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `magproduct`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `cost` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `magproduct`.`basket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NULL,
  `id_product` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_id_user_idx` (`id_user` ASC) VISIBLE,
  INDEX `FK_id_product_idx` (`id_product` ASC) VISIBLE,
  CONSTRAINT `FK_id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `magproduct`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `magproduct`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
ALTER TABLE `magproduct`.`basket` 
ADD COLUMN `quantuty` INT NULL AFTER `id_product`;
ALTER TABLE `magproduct`.`basket` 
ADD COLUMN `old_cost` INT NULL AFTER `quantuty`,
ADD COLUMN `data` VARCHAR(45) NULL AFTER `old_cost`;


INSERT INTO `magproduct`.`user` (`login`, `password`) VALUES ('user1', 'user1');

INSERT INTO `magproduct`.`product` (`title`, `cost`) VALUES ('makaron', '111');
INSERT INTO `magproduct`.`product` (`title`, `cost`) VALUES ('vermishel', '222');
INSERT INTO `magproduct`.`product` (`title`, `cost`) VALUES ('kartoshka', '333');


INSERT INTO `magproduct`.`basket` (`id_user`, `id_product`, `quantuty`) VALUES ('1', '1', '1');
INSERT INTO `magproduct`.`basket` (`id_user`, `id_product`, `quantuty`) VALUES ('1', '2', '2');
