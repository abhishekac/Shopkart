CREATE TABLE Cart (
    Cart_ID integer NOT NULL AUTO_INCREMENT,
    Amount integer,
    PRIMARY KEY (Cart_ID)
);

ALTER TABLE Cart AUTO_INCREMENT=1000;

CREATE TABLE Customer (
    Email varchar(50) NOT NULL,
	Name varchar(50) NOT NULL,
	Phone varchar(15) NOT NULL,
	Address varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
	Cart_ID integer,
    PRIMARY KEY (Email),
	FOREIGN KEY (Cart_ID) references Cart(Cart_ID)
);

CREATE TABLE Category (
    Cat_ID varchar(20) NOT NULL,
	Cat_Name varchar(50) NOT NULL,
    PRIMARY KEY (Cat_ID)
);

CREATE TABLE Product (
    Prod_ID integer NOT NULL,
	Cat_ID varchar(20) NOT NULL,
	Prod_Name varchar(50) NOT NULL,
	Prod_Desc varchar(400) NOT NULL,
	Price numeric NOT NULL,
	Quantity integer(2) NOT NULL,
    PRIMARY KEY (Prod_ID),
	FOREIGN KEY (Cat_ID) references Category(Cat_ID)
);

CREATE TABLE Payment (
	Email varchar(50) NOT NULL,
	Tns_ID integer NOT NULL AUTO_INCREMENT,
	Cart_ID integer NOT NULL,
	Payment_Mode varchar(20) NOT NULL,
	PRIMARY KEY (Tns_ID),
	FOREIGN KEY (Email) references Customer(Email),
	FOREIGN KEY (Cart_ID) references Cart(Cart_ID)
);

ALTER TABLE Payment AUTO_INCREMENT=1527643692;

CREATE TABLE Cart_Products (
	Cart_ID integer NOT NULL,
	Prod_ID integer NOT NULL,
	PRIMARY KEY (Cart_ID,Prod_ID),
	FOREIGN KEY (Cart_ID) references Cart(Cart_ID),
	FOREIGN KEY (Prod_ID) references Product(Prod_ID)
);

DELIMITER $$ 
CREATE TRIGGER After_Cart_Products_Insert AFTER INSERT ON Cart_Products FOR EACH ROW 
BEGIN 
	CALL CartAmount(NEW.Cart_ID);
END;$$ 
DELIMITER ;

DELIMITER $$ 
CREATE TRIGGER After_Cart_Products_Delete AFTER DELETE ON Cart_Products FOR EACH ROW 
BEGIN 
	CALL CartAmount(OLD.Cart_ID);
END;$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE CartAmount(IN cartid integer)
BEGIN
	UPDATE Cart 
	SET Amount=(SELECT SUM(P.Price)
				FROM Product P,Cart_Products CP 
				WHERE CP.Cart_ID=cartid AND P.Prod_ID=CP.Prod_ID)
	WHERE Cart_ID=cartid;
END;$$
DELIMITER ;

DELIMITER $$ 
CREATE TRIGGER Before_SignUp_Insert BEFORE INSERT ON Customer FOR EACH ROW 
BEGIN 
	INSERT INTO Cart(Amount) VALUES(0); 
END;$$ 
DELIMITER ;

DELIMITER $$
CREATE TRIGGER After_Checkout_Insert 
	AFTER INSERT ON Payment 
	FOR EACH ROW 
BEGIN 
	INSERT INTO Cart(Amount) VALUES(0);
	CALL AfterCheckoutInsert(NEW.Cart_ID);
END;$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE AfterCheckoutInsert(IN cartid integer)
BEGIN
	UPDATE Product SET Quantity=Quantity-1 
	WHERE Prod_ID IN (SELECT Prod_ID FROM Cart_Products WHERE Cart_ID=cartid);
END;$$
DELIMITER ;

INSERT INTO CATEGORY VALUES (“C1”,”Electronics”);
INSERT INTO CATEGORY VALUES (“C2”,”Lifestyle”);
INSERT INTO CATEGORY VALUES (“C3”,”Appliances”);
INSERT INTO CATEGORY VALUES (“C4”,”Books & Stationaries”);

Insert into product values (“0”, “C1”, “iPhone X”, “Meet the iPhone X - the device that’s so smart that it responds to a tap, your voice, and even a glance. Elegantly designed with a large 14.73 cm (5.8) Super Retina screen and a durable front-and-back glass, this smartphone is designed to impress.”,  “82000”, “5”);  
Insert into product values (“1”, “C1”, “Beats Studio Over Ear Headphone”, “Beats by Dr. Dre Studio 2.0 Over Ear Headphone - Red The world's most famous headphone has been completely redesigned and re-imagined. The new Beats Studio® is lighter, sexier, stronger, and more comfortable, with precision sound, Adaptive Noise Canceling, a 20-hour rechargeable battery, and RemoteTalkTM.”, “19500”, “3”);  
Insert into product values (“2”, “C1”, “Canon DSLR”,  “It uses an 18-megapixel APS-C size sensor and the DIGIC 4+ image processor - which even professional photographers recognize as high performance with core features”, “24900”, “4”);  
Insert into product values (“3”, “C1”, “JBL – GO Wireless Speaker”, “The Speaker comes with an inbuilt microphone to enable you to attend your important phone calls without any disruption. This speaker comes backed with 600 mAh rechargeable battery thus keeps you entertained for 5 hours, once completely charged.”,  “2099”, “7”);  
Insert into product values (“4”, “C2”, “Nike Running Shoe”, “A pair of blue running sports shoes, has regular styling, lace-up detail, Textile upper,  Cushioned footbed.”,  “7000”, “5”);

  
Insert into product values (“5”, “C2”, “Rolex”, “The Rolex oyster perpetual  is by far the most prestigious Rolex model for entrepreneurs around the world. The visible elegance is the result of its carefully crafted bracelet, bezel, and dial, which can differ greatly based on the particular model. ”,  “200000”, “2”);  
Insert into product values (“6”, “C2”, “Louis Philippe Suit”, “81% Terylene and 19% rayon, Long sleeve, Single breasted notch lapel classic 2 piece suit, Slim fit, Classic collar, Structured pattern, Dry clean only”,  “12000”, “3”);  

Insert into product values (“7”, “C2”, “HP Odyssey Laptop Bag”, “The adjustable sternum strap of the Odyssey Backpack help stabilize your wearing comfort and the gusset straps decrease the unused space. The thick back panel padding covered with mesh and wide air channels of the laptop backpack provide you a great degree of comfort for all day wearing.”,  “2000”, “8”);  
Insert into product values (“8”, “C3”, “Bosch Washing Machine”, “A clever feature that adjusts the level of water bysensing the amount of load. In fact, it reduces the water usage that helps reduce the usage of electricity. Not just that, it uses hot water to thoroughly remove the detergent residue and bacteria formed inside the wash drum, increasing the life of the machine and giving you a perfect washing experience.”,  “34000”, “4”);  
Insert into product values (“9”, “C3”, “Samsung Refrigerator”, “The Samsung refrigerator features an all-around cooling technique that maintains the temperature from top to bottom. This refrigerator has digital inverter technology that ensures reliable operation. The smart connect inverter function ensures a hassle-free operation during power cuts.”,  “60000”, “3”);  
Insert into product values (“10”, “C3”, “Philips Mixer”, “Automatic shut-off: Yes Non-slip feet: Yes Speed setting: 3 and pulse Power indicator: Yes Product warranty: 24 months from the date of purchase Power: 750 W Voltage: 230 V.”,  “4000”, “8”);  
Insert into product values (“11”, “C3”, “Morphy Richards Toaster”, “The Morphy Richards at 201 pop up toaster serves as an efficient kitchen aid that dishes out crispy toasts in minutes. It's an easy way to prepare light and uniformly browned toasts for breakfast.”,  “1499”, “6”);  
Insert into product values (“12”, “C4”, “Complete Reference Java”, “Herbert Schildt is the world's leading programming author and a leading authority on Java, C++ and C#. Herb's acclaimed books include Java: The Complete Reference, Java: A Beginner's Guide, C++: The Complete Reference and C#: The Complete Reference.”,  “500”, “10”);  
Insert into product values (“13”, “C4”, “Complete Reference J2EE”, “Develop robust, industrial-strength J2EE applications that take advantage of the efficiencies of distributive, Web services technology. J2EE: The Complete Reference explains the J2EE architecture and Web services, covers J2EE's massive collection of APIs, and presents strategies for designing and building J2EE components.”,  “650”, “10”);  
Insert into product values (“14”, “C4”, “HeadFirst Android”, “Based on the latest research in cognitive science and learning theory, Head First Android Development uses a visually rich format to engage your mind, rather than a text-heavy approach that puts you to sleep.”,  “799”, “10”);  
Insert into product values (“15”, “C4”, “PHP and mySQL”, “Long acknowledged as the clearest and most practical guide to PHP/MySQL web development, the brand-new Fifth Edition of PHP and MySQL Web Development fully reflects the latest versions of PHP and MySQL to help your students master today's best practices for succeeding with PHP 7 and MySQL 5.7 web database development.”,  “600”, “10”);

