CREATE
    DATABASE IF NOT EXISTS database5;
USE
    `database5`;

DROP TABLE IF EXISTS sub_category;
DROP TABLE IF EXISTS goods_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS shop_goods;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS parent_company;
DROP TABLE IF EXISTS shop;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(45) NULL,
    phone_number VARCHAR(45) NOT NULL,
    name         VARCHAR(45) NOT NULL
);

CREATE TABLE goods
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(45) NOT NULL,
    price           INT NOT NULL,
    expiration_date VARCHAR(45) NULL,
    customer_id     BIGINT      NULL,
    CONSTRAINT FK_goods_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
);

CREATE TABLE parent_company
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(45) NOT NULL
);

CREATE TABLE shop
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(45) NOT NULL,
    min_order_amount BIGINT      NOT NULL,
    parent_company_id INT NULL
);

CREATE TABLE shop_goods
(
    shop_id  BIGINT NOT NULL,
    goods_id BIGINT NOT NULL,
    PRIMARY KEY (shop_id, goods_id)
);

# ALTER TABLE shop_goods
#     ADD CONSTRAINT FK_shop_goods_goods
#         FOREIGN KEY (goods_id)
#             REFERENCES goods (id),
#
#     ADD CONSTRAINT FK_shop_goods_shop
#         FOREIGN KEY (shop_id)
#             REFERENCES shop (id);

CREATE TABLE category
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE goods_category
(
    goods_id    BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (goods_id, category_id)
);


ALTER TABLE goods_category
    ADD CONSTRAINT FK_goods_category_category
        FOREIGN KEY (category_id)
            REFERENCES category (id),

    ADD CONSTRAINT FK_goods_category_goods
        FOREIGN KEY (goods_id)
            REFERENCES goods (id);

CREATE TABLE sub_category
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(45) NOT NULL,
    category_id BIGINT      NULL,
    CONSTRAINT FK_sub_category_category
        FOREIGN KEY (category_id)
            REFERENCES category (id)
);

INSERT INTO `customer` (email, phone_number, name)
VALUES (NULL, '0965566778', 'Ivan'),
       ('example2@gmail.com', '0680000163', 'Demian'),
       ('example3@gmail.com', '0681111111', 'Oleh'),
       ('example4@gmail.com', '0960000000', 'Ihor'),
       ('example5@gmail.com', '0960000000', 'Roman'),
       ('example6@gmail.com', '0960000000', 'Katia'),
       ('example7@gmail.com', '0960000000', 'Ania'),
       ('example8@gmail.com', '0960000000', 'Pablo'),
       ('example9@gmail.com', '0960000000', 'Maxim'),
       ('example10@gmail.com', '0960000000', 'Volodymur');

INSERT INTO `goods` (name, price, expiration_date, customer_id)
VALUES ('laptop', 30000, NULL, 5),
       ('hlibny_dar', 100, '10.09.2023', 6),
       ('shovel', 200, NULL, 2),
       ('deodorant', 80, '30.01.2024', 3),
       ('intoduction_to_programming', 2150, NULL, 10),
       ('teddy_bear', 500, NULL, 1),
       ('washing_maschine', 10000, '11.09.2030', 4),
       ('soccer_ball', 150, NULL, 9),
       ('frock', 7000, NULL, 7),
       ('dog_food_4paws', 20, '18.09.2025', 8),
       ('jeans', 3456, NULL, 1);

INSERT INTO `parent_company`(name)
VALUES ('amazon'),
       ('dior'),
       ('ЄКнигарня'),
       ('ДержХарчування');

INSERT INTO `shop` (name, min_order_amount, parent_company_id)
VALUES ('shemki', 1000, 1),
       ('zara', 10000, 2),
       ('eva', 1, 2),
       ('lego_land', 1, 3),
       ('knowledge', 50, 3),
       ('atb', 1, 4),
       ('bosh', 500, 1),
       ('sport_life', 5, 3),
       ('zoo_gav', 100, 4),
       ('harvest', 20, 4);

INSERT INTO `shop_goods` (shop_id, goods_id)
VALUES (1, 1),
       (6, 2),
       (10, 3),
       (3, 4),
       (6, 4),
       (5, 5),
       (4, 6),
       (7, 7),
       (8, 8),
       (2, 9),
       (9, 10);

INSERT INTO `category` (name)
VALUES ('clothes'),
       ('electronics'),
       ('pet_products'),
       ('sport'),
       ('alcohol_and_products'),
       ('housegold_appliances'),
       ('cosmetic'),
       ('garden'),
       ('toys'),
       ('books');

INSERT INTO `goods_category` (goods_id, category_id)
VALUES (9, 1),
       (1, 2),
       (7, 2),
       (10, 3),
       (8, 4),
       (2, 5),
       (7, 6),
       (4, 7),
       (3, 8),
       (6, 9),
       (5, 10);

INSERT INTO `sub_category` (name, category_id)
VALUES ('for_women', 1),
       ('for_children', 1),
       ('without_artificial_colors', 7),
       ('cheeses', 5),
       ('laptops', 2),
       ('science_fiction', 10),
       ('football', 4),
       ('lego', 9),
       ('coffee_makers', 6),
       ('seed', 8),
       ('cat_food', 3),
       ('dog_food', 3);