<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jdbc/customer.tld" prefix="myTag"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=" icon"
	href="http://cdn.shopify.com/s/files/1/0736/3911/t/1/assets/favicon.png?7941968939387777154">
<title>Inventory</title>
</head>
<body>

	<myTag:CustomerHeader />
	<fieldset>
		<h3>Menu</h3>
	</fieldset>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Image</th>
				<th>Price</th>
				<th>Action</th>

			</tr>
		</thead>
		<tbody>

			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.getName()}</td>
					<td>${item.getdescription()}</td>
					<td><img src="${item.getImageURL()}" width="100" height="100"></td>
					<td>${item.getprice()}</td>

					<td><form method="get">
							<button name="Add" value="${item.getName()}">Add To The
								Cart</button>
						</form></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<h3>
		Go to see your Orders! <a
			href="/cs3220xstu16/PizzaRestaurant/OrderStatusServlet"
			class="link">
			<h4>Here</h4>
		</a>
	</h3>

	</fieldset>
	<footer>
	<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>


</body>
</html>