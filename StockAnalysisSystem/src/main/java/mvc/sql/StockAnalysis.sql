
drop table if exists user;
-- 建立 User
create table if not exists user(
	userId int auto_increment primary key,
    fullname varchar(50) not null,
    email varchar(50) not null,
    username varchar(50) unique not null,
    password varchar(50) not null
);

INSERT INTO user (userId, fullname, email, username, password) VALUES
(101,'捷克' ,'123@gmail.com', 'user123','pass123'),
(102,'高通','456@yahoo.com.tw','user456' ,'pass456'),
(103,'蕭丁棍','789@gmail.com','user789', 'pass789');


drop table if exists Symbol;
-- 建立 Stock Symbol
create table if not exists Symbol(
	stockSymbol varchar(30)
    
);

DROP TABLE IF EXISTS BuyingList;
-- 建立 BuyingList
CREATE TABLE IF NOT EXISTS BuyingList(
	stockListId int auto_increment primary key,
    stockName VARCHAR(30) NOT NULL,
    quantity VARCHAR(30) NOT NULL,
    price VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS SemiProductStock;
create table if not exists SemiProductStock (
	trx_id int not null, -- semi_product_stock_trx_seq
    stockName varchar(30),
    stockNumber varchar(30),
    price varchar(30),
    openingPrice varchar(30),
    highPrice varchar(30),
    lowPrice varchar(30),
    averagePrice varchar(30),
    transactionAmountBillion varchar(30),
    previousClosingPrice varchar(30),
    changePercentage varchar(30),
    changeP varchar(30),
	totalVolume varchar(30),
	previousVolume varchar(30),
	amplitude varchar(30),
    createDate datetime default current_timestamp
);

DROP TABLE IF EXISTS sequence; 
CREATE TABLE sequence ( 
	name VARCHAR(50) NOT NULL, 
	current_value INT NOT NULL, 
	increment INT NOT NULL DEFAULT 1, 
	PRIMARY KEY (name) 
) ENGINE=InnoDB;

DROP FUNCTION IF EXISTS currval; 
DELIMITER $ 
CREATE FUNCTION currval (seq_name VARCHAR(50)) 
RETURNS INTEGER
LANGUAGE SQL 
DETERMINISTIC 
CONTAINS SQL 
SQL SECURITY DEFINER 
COMMENT ''
BEGIN
DECLARE value INTEGER; 
SET value = 0; 
SELECT current_value INTO value 
FROM sequence
WHERE name = seq_name; 
RETURN value; 
END
$ 
DELIMITER ;

DROP FUNCTION IF EXISTS nextval; 
DELIMITER $ 
CREATE FUNCTION nextval (seq_name VARCHAR(50)) 
RETURNS INTEGER
LANGUAGE SQL 
DETERMINISTIC 
CONTAINS SQL 
SQL SECURITY DEFINER 
COMMENT ''
BEGIN
UPDATE sequence
SET current_value = current_value + increment 
WHERE name = seq_name; 
RETURN currval(seq_name); 
END
$ 
DELIMITER ;

DROP FUNCTION IF EXISTS setval; 
DELIMITER $ 
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER) 
RETURNS INTEGER
LANGUAGE SQL 
DETERMINISTIC 
CONTAINS SQL 
SQL SECURITY DEFINER 
COMMENT ''
BEGIN
UPDATE sequence
SET current_value = value 
WHERE name = seq_name; 
RETURN currval(seq_name); 
END
$ 
DELIMITER ;

INSERT INTO sequence VALUES ('semi_product_stock_trx_seq', 0, 1);
-- 添加一个sequence名称和初始值，以及自增幅度  添加一个名为TestSeq 的自增序列

SELECT SETVAL('semi_product_stock_trx_seq', 0);
-- 设置指定sequence的初始值    这里设置TestSeq 的初始值为0

SELECT CURRVAL('semi_product_stock_trx_seq');  
-- 查询指定sequence的当前值   这里是获取TestSeq当前值

SELECT NEXTVAL('semi_product_stock_trx_seq');  
-- 查询指定sequence的下一个值  这里是获取TestSeq下一个值