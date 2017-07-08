package food;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(loadOnStartup=1, urlPatterns={"/admin/foods/"})

public class FoodItemListAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	public void init() {
		// init the application scope to have pre-set values
		List<FoodItem> entries = new ArrayList<>();
		entries.add(new FoodItem(entries.size(), "Pepperoni Pizza", "Our robust, tasty Pepperoni Pizza starts with our savory "
				+ "sauce of vine-ripened tomatoes, extra virgin olive oil,\r\n" + 
				"garlic, and spices spread over a baked-to-perfection thin and crispy gluten free crust."
				+ " We top it off with premium\r\n" + 
				"mozzarella, a sprinkling of fresh-frozen basil and layer on uncured pepperoni "
				+ "for a hearty and satisfying indulgence.", "http://www.caddyshackomaha.com/wp-content/uploads/2016/02/pepperoni-pizza.jpg", "8.99$"));
		entries.add(new FoodItem(entries.size(), "Margherita Pizza", "Cherry tomatoes, fresh tomato, basil drizzle & mozzarella","http://forumofeurope.eu/wp-content/uploads/2016/09/pizza-margherita.jpg-small.jpg" , "12.00$"));
		entries.add(new FoodItem(entries.size(), "Thai Pizza", "Serve this Thai twist on pizza as is or"
				+ " with optional toppings, such as thinly sliced basil, cilantro, chopped\r\n" + 
				"peanuts and/or crushed red pepper on the side.", "https://copymethat.blob.core.windows.net/media/thai-red-curry-pizza-2016122118465131929902zi2v.jpg", "12.00$"));
		entries.add(new FoodItem(entries.size(), "BBQ Chicken Pizza", "BBQ Chicken pizza is like summer in a box. To create our "
				+ " BBQ Chicken pizza, we use only premium grilled chicken, crunchy fresh onions, and not one, not two,"
				+ " but three cheeses: mozzarella, provolone and cheddar on our hand-tossed crust. The result is a traditional "
				+ "summer cookout treat in one perfect pizza. Whenever you’re thinking BBQ, don’t be afraid to think pizza place. In"
				+ " fact, our  BBQ Chicken pizza is a great way to beat back winter blues when you’re longing for the carefree living"
				+ " of warmer months", "http://www.foxspizza.com/wp-content/uploads/2013/04/barbecue-chicken-pizza.jpg", "12.00$"));
		getServletContext().setAttribute("entries", entries);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		// tell browser this is html document
		response.setContentType("text/html");

		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1> ***CalPizza-Admin-Welcome to the California pizza Factory***</h1>");
		out.println("<table>");
		for (FoodItem entry: entries) {
			out.println(
				"<tr>" + 
					"<td>" + entry.getName() + " </td>" + 
					"<td>" + entry.getdescription() + "</td>" +
					"<td>" + "<img src="+entry.getImageURL()+">" + "</td>" +
					"<td>" + entry.getprice() + "</td>" +
					"<td><a href='/cs3220xstu16/admin/foods/edit?id=" + entry.getId() + "'>Edit</a> " + 
					"<a href='/cs3220xstu16/admin/foods/delete?id=" + entry.getId() + "'>Delete</a></td>" +
				"</tr>"
			);
		}
		out.println("</table>");
		out.println("<a href='/cs3220xstu16/admin/foods/create'>Create FoodItem</a>");
		out.println("<a href='/cs3220xstu16/menu'>Menu</a>");
	}
}