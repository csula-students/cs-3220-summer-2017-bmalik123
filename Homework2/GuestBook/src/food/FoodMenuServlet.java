package food;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/menu" })

public class FoodMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		// tell browser this is html document
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
		out.println("<h3>Menu</h3>");
		out.println("<table  border=\"1\" width=\"100%\" id=\"myTable\">");
		out.println("	<thead>\r\n" + "				<tr>\r\n" + "					<th >Name</th>\r\n"
				+ "					<th style='table-layout:fixed'>Description</th>\r\n" + "					<th>Image</th>\r\n"
				+ "					<th>Price</th>\r\n" +"					<th>Action</th>\r\n"+"\r\n" + "				</tr>\r\n" + "			</thead>");
		out.println("<tbody>");
		for (FoodItem entry : entries) {
			out.println("<tr>" + "<td>" + entry.getName() + " </td>" + "<td style='table-layout:fixed'>" + entry.getdescription() + "</td>"
					+ "<td>" + "<img style=\" \" src=" + entry.getImageURL() + ">" + "</td>" + "<td>" + entry.getprice() + "$" +
					"</td>"
					
					+ "<td><a style=\\\"padding:1em; text-decoration: none; color:black;\\\" href='/GuestBook/menu/add?id="
					+ entry.getId() + "'>Add to Cart</a> " + "</td>" +

					"</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</main>");
		out.println("<footer>");
		out.println("<p> © 2017 CalPIZZA-All Rights Reserved </p>");
		out.println("</footer>");

	}
}