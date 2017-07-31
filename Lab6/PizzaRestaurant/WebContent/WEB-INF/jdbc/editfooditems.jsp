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
<title>Admin-Menu</title>
</head>
<body>

	<myTag:AdminHeader />

		<h3>Edit FoodItems</h3>
	

	<form id="create_menu" method="post">

		<fieldset>
			<label><h3>Name</h3></label></br> <input type="text" name="name"
				id="FoodItem_name" value="${item.getName()}" size="55"></input> <br>
			<label><h3>Image URL</h3></label></br> <input type="text" name="image"
				id="FoodItem_image" size="55" value="${item.getImageURL()}"></input>
			<br> <label><h3>Description</h3></label></br> <input type="text"
				name="description" id="FoodItem_description" size="55"
				value="${item.getdescription()}"></input> <br> <label><h3>Price</h3></label></br>
			<input type="text" name="price" id="FoodItem_price" size="55"
				value="${item.getprice()}"></input> <br> <br>
		</fieldset>
		<button name="Submit" value="Submit">Edit</button>



	</form>


	<footer>
	<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>


</body>
</html>