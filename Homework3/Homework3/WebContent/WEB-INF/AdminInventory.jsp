<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="admin-header.tld" prefix="myTag"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inventory</title>
</head>
<body>

	<myTag:AdminHeader />
	<fieldset>
	<h3>Existing food items</h3>
	</fieldset>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Image</th>
				<th>Description</th>
				<th>Action</th>
				
			</tr>
		</thead>
		<tbody>
			
				<c:forEach items="${items}" var="item">
					<tr>
						
						<td> ${item.getName()}</td>
						<td><img src="${item.getImageURL()}" width="100" height="100">
						<td> ${item.getdescription()}</td>	
						<td><form method="get">
								<button name="Submit" value="${item.getName()}">Delete</button>
							</form></td>
					</tr>

				</c:forEach>
		</tbody>
	</table>
	</fieldset>
	<h3>
		To add the new foodItem Go-><a
			href="http://localhost:8080/Homework3/AdminCreateFoodServlet"
			class="link">
			<h4>Here</h4>
		</a>
	</h3>
	<footer>
		<p>© 2017 CalPIZZA-All Rights Reserved</p>
	</footer>


</body>
</html>