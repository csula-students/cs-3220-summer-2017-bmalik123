
package food;
import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin/foods/edit")
public class EditFoodAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem leEntry = null;
		for (FoodItem entry: entries) {
			if (entry.getId() == id) {
				leEntry = entry;
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
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
		out.println("<h1>Editing The Status</h1>");
		out.println("<form method=\"post\">");
		out.println(" Name: <input name='name' type='text' value='" + leEntry.getName() + "'/></br>");
		out.println("Description: <textarea name='description'>" + leEntry.getdescription() + "</textarea></br>");
		out.println(" Image URL: <input name='ImageURL' type='text' value='" + leEntry.getImageURL() + "'/></br>");
		out.println("Price: <textarea name='price'>" + leEntry.getprice() + "</textarea></br>");
		out.println("<button>Edit</button>");
		out.println("</form>");
		
		out.println("</main>");
		out.println("<footer>");
		out.println("<p> © 2017 CalPIZZA-All Rights Reserved </p>");
		out.println("</footer>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem leEntry = null;
		int index = -1;
		for (int i = 0; i < entries.size(); i ++) {
			if (entries.get(i).getId() == id) {
				leEntry = entries.get(i);
				index = i;
			}
		}
		entries.set(index, new FoodItem(
			leEntry.getId(),
			request.getParameter("name"),
			request.getParameter("description"),
			request.getParameter("ImageURL"),
			Double.parseDouble(request.getParameter("price"))
		));
		getServletContext().setAttribute("entries", entries);


		response.sendRedirect("/GuestBook/admin/foods/");
	}
}