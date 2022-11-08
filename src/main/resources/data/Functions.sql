USE `database5`;

DROP FUNCTION IF EXISTS GetAverageMinOrderAmount;
DELIMITER //
CREATE FUNCTION GetAverageMinOrderAmount()
    RETURNS DECIMAL(8, 2)
    DETERMINISTIC
BEGIN
    RETURN (SELECT AVG(min_order_amount) FROM `shop`);
END //
DELIMITER ;