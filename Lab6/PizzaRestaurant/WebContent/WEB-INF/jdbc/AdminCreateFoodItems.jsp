<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jdbc/admin-header.tld" prefix="myTag"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Food Item</title>
</head>
<body>
	<myTag:AdminHeader />
	<fieldset>
		<h3>Add New Food Item</h3>
	</fieldset>
	<form id="create_menu" method="post">

		<fieldset>
			<label><h3>Name</h3></label></br> <input type="text" name="name"
				id="FoodItem_name" size="55"></input> <br> 
				<label><h3>Image
					URL</h3></label>
					</br> <input type="text" name="image" id="FoodItem_image" size="55"></input>



			<br> <label><h3>Description</h3></label></br> <input type="text"
				name="description" id="FoodItem_description" size="55"></input> <br>
			<label><h3>Price</h3></label></br> <input type="text" name="price"
				id="FoodItem_price" size="55"></input> <br> <br>
		</fieldset>
		<button name="Submit" value="Submit">Add Food</button>



	</form>
	<h4>
		Go back to Admin-menu! <a href="/PizzaRestaurant/AdminCalPizzaServlet"
			class="link">
			<h4>Here</h4>
		</a>
	</h4>
	<footer>
	<h4>
		<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</h4>
	</footer>


</body>
</html>