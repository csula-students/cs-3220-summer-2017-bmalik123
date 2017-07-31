<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%-- Import the JSTL --%>
<%@ taglib uri="/WEB-INF/jdbc/customer.tld" prefix="myTag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=" icon"
	href="http://cdn.shopify.com/s/files/1/0736/3911/t/1/assets/favicon.png?7941968939387777154">
<title>Status(Order)</title>
</head>
<body>
	<header> <myTag:CustomerHeader /> <nav></header>

	<main>


	<h2>Statuses</h2>

	<c:choose>
		<c:when test="${order.size() == 0}">

			<table>
				<tbody>
					<tr>
						<td>
							<h3>
								<br> Go back to <a
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
					<tr>
						<th>Created</th>
						<th>Item-Name</th>
						<th>Item</th>
						<th>Customer</th>
						<th>Status</th>
					</tr>
				<thead>
				<tbody>
					<c:forEach items="${order}" var="item">
						<tr>
							<td>${item.getdate()}</td>
						<td>${item.getfoodItems().getName()}</td>
							<td><img src=${item.getfoodItems().getImageURL() }
								width="200" height="100"></td>
							<td>${item.getcustName()}</td>
							<td>${item.getStatus()}</td>
						</tr>
				</tbody>

				</c:forEach>

			</table>
		</c:otherwise>
	</c:choose> </main>
	
	<footer>
	<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>
</body>
</html>