USE `database5`;

/*2a, 2c, 2d 6 lab db*/

DROP PROCEDURE IF EXISTS GoodsTestInserts;
DELIMITER //
CREATE PROCEDURE GoodsTestInserts(
    IN new_goods_name VARCHAR(50),
    IN new_goods_price VARCHAR(50))
BEGIN
    DECLARE max_id INT;
    DECLARE idx INT;
    SELECT MAX(id) + 1 INTO max_id FROM `goods`;
    IF max_id IS NULL THEN
        SET max_id = 1;
    END IF;
    SET idx = 1;
    label1:
    WHILE idx < 11
        DO
            INSERT INTO `goods` (name, price)
            VALUES (CONCAT(new_goods_name, max_id), CONCAT(new_goods_price, max_id));
            SET max_id = max_id + 1;
            SET idx = idx + 1;
            ITERATE label1;
        END WHILE;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS ShopParamInsert;
DELIMITER //
CREATE PROCEDURE ShopParamInsert(
    IN new_name VARCHAR(50),
    IN new_min_order_amount INT,
    IN new_parent_company_id INT)
BEGIN
    INSERT INTO `shop` (name, min_order_amount, parent_company_id) VALUES (new_name, new_min_order_amount, new_parent_company_id);
    SELECT id, name, min_order_amount, parent_company_id FROM `shop` WHERE name = new_name AND min_order_amount = new_min_order_amount and parent_company_id = new_parent_company_id;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS CalcAverageMinOrderAmount;
DELIMITER //
CREATE PROCEDURE CalcAverageMinOrderAmount()
BEGIN
    DECLARE label VARCHAR(20);
    SELECT GetAverageMinOrderAmount() AS min_order_amount;
END //



/*2b*/
DROP PROCEDURE IF EXISTS AddShopGoodsRelationship //
CREATE PROCEDURE AddShopGoodsRelationship(
    IN shop_name VARCHAR(50),
    IN goods_name VARCHAR(50))
BEGIN
    DECLARE shop_id, goods_id INT;
    SELECT id INTO shop_id FROM `shop` WHERE name = shop_name;
    SELECT id INTO goods_id FROM `goods` WHERE name = goods_name;
    IF (shop_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: no such shop found';
    END IF;
    IF (goods_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: no such goods found';
    END IF;
    INSERT INTO `shop_goods` (shop_id, goods_id) VALUES (shop_id, goods_id);
END //


DROP PROCEDURE IF EXISTS CreateTablesWithCursor //
CREATE PROCEDURE CreateTablesWithCursor()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE goods_name VARCHAR(50);
    DECLARE my_cursor CURSOR
        FOR SELECT name FROM `goods`;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN my_cursor;
    my_loop: LOOP
        FETCH my_cursor INTO goods_name;
        IF (done = true) THEN LEAVE my_loop;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', goods_name, DATE_FORMAT(NOW(), '_%Y_%m_%d_%H_%i_%s'), ' (', goods_name, '1 INT, ', goods_name, '2 BOOL);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //

DELIMITER ;

