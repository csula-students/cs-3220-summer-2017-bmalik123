<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jdbc/admin-header.tld" prefix="myTag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Statuses</title>
</head>
<body>
	<myTag:AdminHeader />
	<title>Update Order Status</title>
</head>
<body>
	<fieldset>
		<h3>Statuses(Order)</h3>
	</fieldset>
	<table id="order_status_control_table" class="center horizontal">
		<thead>
			<th class="horizontal"><h3>Item Name</h3></th>
			<th class="horizontal"><h3>Item</h3></th>
			<th class="horizontal"><h3>Created</h3></th>
			<th class="horizontal"><h3>Edit Status</h3></th>
			<th class="horizontal"><h3>Status</h3></th>
			<th class="horizontal"><h3>Customer Name</h3></th>
		</thead>
		<tbody>
			<c:forEach items="${order}" var="item">
				<tr>
					<td>${item.getfoodItems().getName()}</td>
					<td><img src="${item.getfoodItems().getImageURL()}"
						width="100" height="100"></td>
					<td>${item.getdate()}</td>

					<td><form method="post">
							<select name="status">
								<option value="IN_QUEUE" selected>In Queue</option>
								<option value="COMPLETED">Completed</option>
								<option value="IN_PROGRESS">In progress</option>
							</select>
							<button name="edit" value="${item.getfoodItems().getName()}">Update
									statuses</button>
						</form></td>
					<td>${item.getStatus()}</td>
					<td>${item.getcustName()}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>


	</fieldset>
	<fieldset>
		<h4>
			Go back to menu! <a
				href="/PizzaRestaurant/AdminCalPizzaServlet"
				class="link">
				<h4>Here</h4>
			</a>
		</h4>
		|
		<h4>
			Go back to create new Food Items! <a
				href="/PizzaRestaurant/AdminCreateFoodItemsServlet"
				class="link">
				<h4>Here</h4>
			</a>
		</h4>
	</fieldset>

	<footer>
	<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>

</body>
</html>