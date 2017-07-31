<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jdbc/customer.tld" prefix="myTag"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=" icon"
	href="http://cdn.shopify.com/s/files/1/0736/3911/t/1/assets/favicon.png?7941968939387777154">
<title>Order</title>
</head>
<body>

	<header> <myTag:CustomerHeader /> </header>

	<main>


	<h2>Order</h2>

	<c:choose>
		<c:when test="${cart.size() == 0}">

			<table>
				<tbody>
					<tr>
						<td>
							<h3>you haven't ordered anything</h3>
							<h3>
								you can go back to <a
									href="/PizzaRestaurant/FoodMenuServlet">Menu</a>.
							</h3>
						</td>
					</tr>
				</tbody>
			</table>
		</c:when>

		<c:otherwise>
			<table>
				<thead>
					<th>Item Name</th>
					<th>Item</th>
					<th>Price</th>
					<th>Action</th>
					</tr>
				</thead>
				<c:forEach items="${cart}" var="item">
					<tbody>
						<tr>
							<td>${item.getName()}</td>
							<td><img src=${item.getImageURL() } width="150" height="100"></td>
							<td>$ ${item.getprice()}</td>

							<td><form method="get">
									<button name="remove" value="${item.getName()}">Remove</button>
								</form></td>

						</tr>
					</tbody>

				</c:forEach>
			</table>
			<h3>Type Your Name</h3>

			<form method="post">
				<label>Your Name:</label><br> <input name='custname'
					type='text' /></br> <br>
				<button>Place Your order</button>
			</form>
		</c:otherwise>
	</c:choose> </main>
	<h3>
		Go to see your Order-Status! <a
			href="/PizzaRestaurant/FinalOrderStatusServlet" class="link">
			<h4>Here</h4>
		</a>
	</h3>
	<footer>
	<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>
</body>
</html>