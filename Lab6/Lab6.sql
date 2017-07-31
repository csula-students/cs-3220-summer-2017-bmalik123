USE lab6;

CREATE TABLE food_items (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    food_name      VARCHAR(255) NOT NULL,
    description text,
    image text,
	price DOUBLE 
);
insert into food_items(id,food_name,description,image,price) values(1, 'Pepperoni Pizza',
					'Our robust, tasty Pepperoni Pizza starts with our savory sauce of vine-ripened tomatoes, extra virgin olive oil,garlic, and spices spread over a baked-to-perfection thin and crispy gluten free crust. We top it off with premium mozzarella, a sprinkling of fresh-frozen basil and layer on uncured pepperoni for a hearty and satisfying indulgence.',
					'https://3eyesdotme.files.wordpress.com/2012/12/pepperonipizza.png?w=300',8.99);
insert into food_items(id,food_name,description,image,price) values(2,'Thai Pizza',
					'Serve this Thai twist on pizza as is or with optional toppings, such as thinly sliced basil, cilantro, chopped peanuts and/or crushed red pepper on the side.',
					'https://static1.squarespace.com/static/55282462e4b008238c306c6e/t/5706b5141d07c09d2c5a2a8f/1463346750494/ultimate+thai+pizza+recipe?format=300w', 12.99);
insert into food_items(id,food_name,description,image,price) values(3, 'Margherita Pizza',
					'Cherry tomatoes, fresh tomato, basil drizzle & mozzarella .',
					'http://pizzeriavecchia.com/wp-content/uploads/000-margherita-pizza-300x200.jpg', 14.99);
insert into food_items(id,food_name,description,image,price) values(4, 'BBQ Chicken Pizza',
					'Cherry tomatoes, fresh tomato, basil drizzle & mozzarella .',
					'http://www.foxspizza.com/wp-content/uploads/2013/04/barbecue-chicken-pizza.jpg', 15.99);