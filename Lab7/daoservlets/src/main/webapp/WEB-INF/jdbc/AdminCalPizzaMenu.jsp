<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jdbc/admin-header.tld" prefix="myTag"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin-Menu</title>
</head>
<body>

	<myTag:AdminHeader />
	<fieldset>
		<h3>Menu(Admin)</h3>
	</fieldset>
	<table>
		<thead>
			<tr>

				<th>Item</th>
				<th>Image</th>
				<th>Description</th>
				<th>Price</th>
				<th></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${list}" var="item">
				<tr>


					<td>${item.getName()}</td>
					<td><img src="${item.getImageURL()}" width="100" height="100">
					</td>
					<td>${item.getdescription()}</td>

					<td>${item.getprice()}</td>

					<td><br>
						<form method="get">
							<button>
								<a
									href="<c:url value='/PizzaRestaurant/editFoodItemsServlet?id=${item.getId()}' />">Edit</a>
							</button>
						</form>
						<form method="get">
							<button name="Submit" value="${item.getId()}">Delete</button>
						</form></td>

				</tr>

			</c:forEach>
		</tbody>
	</table>
	<form method="post">
		<button>
			Add new Food</a>
		</button>
		</fieldset>
	</form>
	<footer>
	<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>


</body>
</html>