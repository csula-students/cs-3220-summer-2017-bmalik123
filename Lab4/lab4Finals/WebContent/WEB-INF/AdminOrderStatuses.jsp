<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="admin-header.tld" prefix="myTag"%>
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
	<title>Admin-OrderStatus</title>
</head>
<body>
<fieldset>
	<h3>Statuses(Order)</h3>
	</fieldset>
	<table id="order_status_control_table" class="center horizontal">
		<thead>
            <th class="horizontal"><h3>Item-Name</h3></th>
			<th class="horizontal"><h3>Item</h3></th>
			
			<th class="horizontal"><h3>Created</h3></th>
			
			<th class="horizontal"><h3>Status</h3></th>
			
			<th class="horizontal"><h3>Customer Name</h3></th>
		</thead>
		<tbody>
			<c:forEach items="${fooditems}" var="item">
                     <td>${item.getName()}</td>
					<td><img src="${item.getImageURL()}" width="100" height="100">
					</td>
					<td><fmt:formatDate value="${date}" type="time" /></td>

					<td><form name="status" method="get" action="#">
							<select>
							
								<option>In Queue</option>
								
								<option>Completed</option>
								
								<option>In progress</option>

							</select>
						</form></td>
					<td>Bhupen</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button>Update statuses</button>

	</fieldset>
<fieldset>
	<h4>
		Go back to menu! <a
			href="http://localhost:8080/lab4Finals/AdminCalPizzaServlet"
			class="link">
			<h4>Here</h4>
		</a>
	</h4>
	|
	<h4>
		Go back to create new Food Items! <a
			href="http://localhost:8080/lab4Finals/AdminCreateFoodItemsServlet"
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