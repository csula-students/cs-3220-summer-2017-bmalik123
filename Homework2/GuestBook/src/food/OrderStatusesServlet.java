package food;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Order Status of a customer after confirm his order */

@WebServlet(loadOnStartup = 1, urlPatterns = { "/orders" })
public class OrderStatusesServlet extends HttpServlet {
	public void init() {

		List<Order> order = new ArrayList<>();
		getServletContext().setAttribute("order", order);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		// tell browser this is html document
		response.setContentType("text/html");

		response.setContentType("text/html");

		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<link rel='stylesheet' type='text/css' href='app.css'>");

		out.println("<link href='https://fonts.googleapis.com/css?family=Dancing+Script' rel='stylesheet'>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Cuprum' rel='stylesheet'>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet'>");

		out.println(
				"<link rel=' icon' href='http://cdn.shopify.com/s/files/1/0736/3911/t/1/assets/favicon.png?7941968939387777154'>");
		out.println("<title> Home-CalPizza</title>");
		out.println("<meta name='viewport' content='width=device-width,initial-scale=1' />");
		out.println("<style>body { " + "}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header>");

		out.println("<section class='container' >");
		out.println(
				"<img class='logo' src='http://lh6.ggpht.com/raTqkvNCCyy25tiX22VKq1uSnVUqKspeJ5opFceK0Vjnf3mzUfwfI6X2QSMrrIWsUkI=w300' alt='' style='width:100px;height:100px;'>");
		out.println("<h1> CalPizza-Welcome to the California pizza Factory</h1>");
		out.println("<span class='fill'></span>");
		out.println("<ul>");
		out.println("<li><a  href='/GuestBook/menu'>Menu</a></li>");
		out.println("<li><a href='/GuestBook/orders'>Order</a></li>");
		out.println("<li><a href='/GuestBook/admin/foods/ '>Admin</a></li>");
		
		out.println("</ul>");
		out.println("</section>");
		out.println("</header>");
		out.println("<main>");
		out.println("<h1>Orders-Status</h1>");
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");

		if (order.size() == 0) {
			out.println("<table width='100%'>");
			out.println("<tr style=\"padding:1em;\">" + "<td style=\"padding:1em;\">"
					+ "<h3> There is nothing </h3>" + "</td>" + "</tr>");
			out.println("</table>");
			out.println(
					"<h3>Go Back to  <a  href='/GuestBook/menu'>Menu</a><h3>");

		} else {
			out.println("<table width='100%'>");
			for (Order newOrder : order) {
				out.println("<tr>" + "<td >" + "<h3>Name:</h3>"
						+ newOrder.getfoodItems().getName() + "</td>" + "<td>" + "<h3>Price:</h3>"
						+ newOrder.getfoodItems().getprice() + "</td>" + "<td>" + "<img src="
						+ newOrder.getfoodItems().getImageURL() + ">" + "</td>" + "<td>"
						+ "<h3>Customer Name:</h3>" + newOrder.getcustName() + "</td>" + "<td>"
						+ "<h3>Status:</h3>" + newOrder.getStatus() + "</td>" + "<td>"
						+ "<h3>Date:</h3>" + newOrder.getdate() + "</td>" + "</tr>");
			}
			out.println("</table>");

		}
		out.println(
				"<h3><a  class='styles' style=\"padding:1em; text-decoration: none;\" href='/GuestBook/menu'>Menu</a><h3>");
		out.println(
				"<h3><a  class='styles' style=\"padding:1em; text-decoration: none;\" href='/GuestBook/shopping-cart'>Cart Items</a></h3>");
		out.println(
				"<h3><a  class='styles' style=\"padding:1em; text-decoration: none;\" href='/GuestBook/admin/orders'>Order Status(Admin)</a><h3>");
	}

}