CREATE DATABASE PRJ301_SE1506_Ass_Group10


USE PRJ301_SE1506_Ass_Group10



--------------------USER------------
DROP TABLE tbl_Users

CREATE TABLE tbl_Users(
	USERID	NVARCHAR(20)	PRIMARY KEY,
	PASSWORD NVARCHAR(20) ,
	FULLNAME NVARCHAR(50) ,
	PHONE INTEGER ,
	ADDRESS NVARCHAR (50) , 
	ROLE INTEGER 
)


INSERT INTO tbl_Users(USERID, PASSWORD, FULLNAME,PHONE, ADDRESS, ROLE)
VALUES	('customer','123','Tung',123456789, 'happyplace','1'),
		('staff','123','Nhung',	123, 'hongcong','0'),
		('customer2','123','Huy',123456789, 'newyork','1');		
	  SELECT * FROM tbl_Users  
------------device--------


DROP TABLE tbl_Shoes;

CREATE TABLE tbl_Shoes (
	SHOESID	VARCHAR(20) PRIMARY KEY,
	SHOESNAME VARCHAR(250),
	PRICE FLOAT NULL,
	DESCRIPTION VARCHAR(250),
	QUANTITY INT NULL,
	IMAGE VARCHAR(250), 
	NOTSALE BIT 	
)

	SELECT * FROM tbl_Users
	SELECT * FROM tbl_Shoes

	
INSERT INTO tbl_Shoes(SHOESID, SHOESNAME, PRICE, DESCRIPTION, QUANTITY, IMAGE, NOTSALE)
VALUES ('SP001','ADIDAS',2,'RED',20,'Adidas1.jpg',0),
		('SP002','ADIDAS',2,'BLUE',50,'Adidas2.jpg',0),
		('SP003','ADIDAS',3,'YELLOW',20,'Adidas3.jpg',1),
        ('SP004','ADIDAS',3,'RED',10,'Adidas4.jpg',1),
        ('SP005','ADIDAS',3,'ORANGE',20,'Adidas5.jpg',1),
        ('SP006','ADIDAS',3,'BLACK',20,'Adidas6.jpg',1),
        ('SP007','ADIDAS',3,'PURPLE',20,'Adidas7.jpg',1),
        ('SP008','ADIDAS',3,'YELLOW',20,'Adidas8.jpg',1),
        ('SP009','ADIDAS',3,'YELLOW',20,'Adidas9.jpg',1),
        ('SP010','CONVERSE',3,'WHITE',20,'Converse1.jpg',1),
        ('SP011','CONVERSE',3,'YELLOW',20,'Converse2.jpg',1),
        ('SP012','CONVERSE',3,'BLACK',20,'Converse3.jpg',1),
        ('SP013','CONVERSE',3,'YELLOW',20,'Converse4.jpg',1),
        ('SP014','CONVERSE',3,'RED',20,'Converse5.jpg',1),
        ('SP015','NIKE',3,'GREEN',20,'Nike1.jpg',1),
        ('SP016','NIKE',3,'YELLOW',20,'Nike2.jpg',1),
        ('SP017','NIKE',3,'BROWN',20,'Nike3.jpg',1),
        ('SP018','NIKE',3,'YELLOW',20,'Nike4.jpg',1),
        ('SP019','NIKE',3,'WHITE',20,'Nike5.jpg',1),
        ('SP020','NIKE',3,'YELLOW',20,'Nike6.jpg',1),
        ('SP021','NIKE',3,'PINK',20,'Nike7.jpg',1),
        ('SP022','NIKE',3,'YELLOW',20,'Nike8.jpg',1),
        ('SP023','NIKE',3,'BLUE',20,'Nike9.jpg',1),
        ('SP024','VANS',3,'YELLOW',20,'Vans1.jpg',1),
        ('SP025','VANS',3,'GREY',20,'Vans2.jpg',1),
        ('SP026','VANS',3,'PINK',20,'Vans3.jpg',1),
        ('SP027','VANS',3,'YELLOW',20,'Vans4.jpg',1);

SELECT * FROM tbl_Shoes

------------------------order---------------
use PRJ301_SE1506_Ass_Group10

IF OBJECT_ID('tbl_order') is not null
	DROP TABLE tbl_order

CREATE	TABLE TBL_ORDER (
	USERID		NVARCHAR(20)	REFERENCES tbl_Users(USERID) NOT NULL,
	PAID_DAY	DATETIME		NOT NULL,
	TOTAL		FLOAT			NULL,
	CONSTRAINT ORDER_KEY PRIMARY KEY (USERID, PAID_DAY)
)

SELECT * FROM TBL_ORDER

---------------------dETAIL
IF OBJECT_ID('tbl_detail') is not null
	DROP TABLE tbl_detail

CREATE	TABLE TBL_DETAIL (
	USERID		NVARCHAR(20)    NOT NULL,
	PAID_DAY	DATETIME		NOT NULL,
	SHOESID		VARCHAR(20)		REFERENCES tbl_Shoes(SHOESID) NOT NULL,
	SHOESNAME	VARCHAR(250)	NULL,
	QUANTITY	INTEGER			NOT NULL,
	PRICE		FLOAT			NOT NULL,
	TOTAL		FLOAT			NULL,	
) 

INSERT INTO TBL_DETAIL (USERID, PAID_DAY, SHOESID, SHOESNAME, QUANTITY, PRICE, TOTAL)
VALUES ('3','3-3-14 2:2:2.2','1','1',1,1,1);
SELECT * FROM TBL_DETAIL
SELECT * FROM TBL_ORDER
SELECt * FROM tbl_Shoes
SELECT * FROM tbl_Users


SELECT * FROM TBL_DETAIL
WHERE SHOESID= 'SP002'

