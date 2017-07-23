USE cs3220xstu16;



/*----------------------- 1.Create restaurant food item table*-----------------------------*/
CREATE TABLE Fooditems (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
   price DOUBLE 
);







/*-----------------------2.Create restaurant order statuses table--------------------------------------*/
CREATE TABLE Orders(
id INTEGER AUTO_INCREMENT PRIMARY KEY,
Customer_name      VARCHAR(255) NOT NULL,
Created           datetime  

);

/*----------------------- ###Create Order Food table*-----------------------------*/
CREATE TABLE OrderFoods(
Order_ID INTEGER   REFERENCES Orders(id),
Food_ID  INTEGER    REFERENCES Fooditems(id) ,
Quantity INTEGER  NOT NULL 

);





/*----------------------- 3.Create restaurant cart table*-----------------------------*/
CREATE TABLE ShoppingCart(
ID INTEGER ,
Customer_Name VARCHAR(255) REFERENCES  Orders(Customer_name),
FoodID   INTEGER REFERENCES OrderFoods(Food_ID),
Quantity INTEGER NOT NULL



);





/*----------------------- 4.Insert default food items into food items table*-----------------------------*/
insert into Fooditems values(1,'Hamburger' ,'A Hamburger' , 9.99);
insert into Fooditems values(2,'Fries' ,'Some Fries' , 4.99);
insert into Fooditems values(3,'Coke' ,'Coka Cola' , 2.99);





/*----------------------- 5.Insert default order statuses into order statuses table*-----------------------------*/
insert into Orders values(1,'Eric' ,	'2017-07-20 13:35:55');
insert into Orders values(2,'John' ,'2017-07-22 10:35:55');
insert into Orders values(3,'Jane' ,'2017-07-22 15:35:55');
insert into Orders values(4,'Alice' ,'2017-07-22 16:35:55');




/*----------------------- ####.Insert order foods into orderFoods table*-----------------------------*/

insert into OrderFoods values(1,1 ,	1);
insert into OrderFoods values(1,2 ,2);
insert into OrderFoods values(2, 2,2);
insert into OrderFoods values(2,3 ,1);
insert into OrderFoods values(3,3 ,1);
insert into OrderFoods values(4,2 ,1);
insert into OrderFoods values(4,3 ,2);




/*----------------------- 5.Insert default order statuses into order statuses table*-----------------------------*/
insert into ShoppingCart values(1,'Anonymous' ,	1,2);
insert into ShoppingCart values(1,'Anonymous' ,2,1);
insert into ShoppingCart values(1,'Anonymous' ,2,1);
insert into ShoppingCart values(2,'Mike' ,1,1);
insert into ShoppingCart values(2,'Mike' ,2,1);
insert into ShoppingCart values(3,'Bob' ,3,1);





/*----------------------- 6.Update food item name from "Hamburger" to "Salad"-----------------------------*/
UPDATE Fooditems
SET name = 'Salad'
WHERE id = 1;


/*----------------------- 7.Update customer name from "Jane" to "Doe"-----------------------------*/
UPDATE Orders
SET Customer_name = 'Doe'
WHERE id = 3;


/*----------------------- 8.Find out which food item has the most orders-----------------------------*/

SELECT Fooditems.name,sum(quantity) 
FROM OrderFoods JOIN Fooditems 
WHERE Fooditems.id= OrderFoods.Food_ID
group by name 
order by SUM(Quantity) desc limit 1;




/*----------------------- 9.Find out which food item in least shopping carts-----------------------------*/

SELECT Fooditems.name,SUM(Quantity) FROM ShoppingCart JOIN Fooditems WHERE Fooditems.id= ShoppingCart.ID GROUP BY name ORDER BY sum(Quantity) ASC LIMIT 1;


/*----------------------- 10.Find out all restaurant food items using SELECT query-----------------------------*/
SELECT * FROM Fooditems;

/*----------------------- 11.Find out all restaurant order statuses using SELECT query-----------------------------*/

SELECT * FROM Orders;
/*----------------------- 12.Find out the order statuses that is created today----------------------------*/
SELECT * FROM Orders WHERE  DATE(Created) = DATE(now());




/*----------------------- 13.DELETE restaurant food items table-----------------------------*/


Drop table Fooditems;



/*----------------------- 14.Delete restaurant order statuses table-----------------------------*/
 Drop table Orders;

/*----------------------- 15.Delete restaurant cart table-----------------------------*/

Drop table ShoppingCart;
