USE `database5`;

DELIMITER //
DROP TRIGGER IF EXISTS WhileAddShopCheckMonopoly //
CREATE TRIGGER WhileAddShopCheckMonopoly
    BEFORE INSERT
    ON `database5`.`shop` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM `database5`.`parent_company`
            WHERE id = NEW.parent_company_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No parent_company with such id';
    END IF;
END //

DROP TRIGGER IF EXISTS UpdateShopCheckMonopoly //
CREATE TRIGGER UpdateShopCheckMonopoly
    BEFORE UPDATE
    ON `database5`.`shop` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM `database5`.`parent_company`
            WHERE id = NEW.parent_company_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No  parent_company with such id';
    END IF;
END //

DROP TRIGGER IF EXISTS UpdateMonopolyCheckId //
CREATE TRIGGER UpdateMonopolyCheckId
    BEFORE UPDATE
    ON `database5`.`parent_company` FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT parent_company_id FROM `database5`.`shop`
            WHERE parent_company_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t update row with record present in related table';
    END IF;
END //


DROP TRIGGER IF EXISTS DeleteMonopolyCheckId //
CREATE TRIGGER DeleteMonopolyCheckId
    BEFORE DELETE
    ON `database5`.`shop` FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT  parent_company_id FROM `database5`.`shop`
            WHERE parent_company_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t delete row with record present in related table';
    END IF;
END //




DROP TRIGGER IF EXISTS CheckMinOrderAmountCardinality//
CREATE TRIGGER CheckMinOrderAmountCardinality
    BEFORE INSERT
    ON `database5`.`shop` FOR EACH ROW
BEGIN
    IF (NEW.min_order_amount < 0)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Value error: min_order_amount can`t be less than 0';
    END IF;
END //

DROP TRIGGER IF EXISTS CheckShopName //
CREATE TRIGGER CheckShopName
    BEFORE INSERT
    ON `database5`.`shop` FOR EACH ROW
BEGIN
    IF (NEW.name NOT RLIKE '^[a-zA-Z0-9]')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Value error: invalid name format';
    END IF;
END //

DROP TRIGGER IF EXISTS ForbidDeleteSubCategory //
CREATE TRIGGER ForbidDeleteSubCategory
    BEFORE DELETE
    ON `database5`.`sub_category` FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Forbidden method: you can`t delete data from table `sub_category`';
END //

DELIMITER ;