package Lb4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Lb4.FoodItem;

/**
 * Servlet implementation class RestaurantAdminServlet
 */
@WebServlet(loadOnStartup = 1, urlPatterns = { "/RestaurantAdminServlet" })
public class RestaurantAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
		List<FoodItem> items = new ArrayList<>();
		items.add(new FoodItem(items.size(), "Pepperoni Pizza",
				"Our robust, tasty Pepperoni Pizza starts with our savory sauce of vine-ripened tomatoes, extra virgin olive oil,garlic, and spices spread over a baked-to-perfection thin and crispy gluten free crust. We top it off with premium mozzarella, a sprinkling of fresh-frozen basil and layer on uncured pepperoni for a hearty and satisfying indulgence.",
				"https://3eyesdotme.files.wordpress.com/2012/12/pepperonipizza.png?w=300", 8.99));
		items.add(new FoodItem(items.size(), "Thai Pizza",
				"Serve this Thai twist on pizza as is or with optional toppings, such as thinly sliced basil, cilantro, chopped peanuts and/or crushed red pepper on the side.",
				"https://static1.squarespace.com/static/55282462e4b008238c306c6e/t/5706b5141d07c09d2c5a2a8f/1463346750494/ultimate+thai+pizza+recipe?format=300w",
				12.99));
		items.add(new FoodItem(items.size(), "Margherita Pizza",
				"Cherry tomatoes, fresh tomato, basil drizzle & mozzarella .",
				"http://pizzeriavecchia.com/wp-content/uploads/000-margherita-pizza-300x200.jpg", 14.99));
		items.add(new FoodItem(items.size(), "BBQ Chicken Pizza",
				"BBQ Chicken pizza is like summer in a box. To create our "
						+ " BBQ Chicken pizza, we use only premium grilled chicken, crunchy fresh onions, and not one, not two,"
						+ " but three cheeses: mozzarella, provolone and cheddar on our hand-tossed crust. The result is a traditional "
						+ "summer cookout treat in one perfect pizza. Whenever you’re thinking BBQ, don’t be afraid to think pizza place. In"
						+ " fact, our  BBQ Chicken pizza is a great way to beat back winter blues when you’re longing for the carefree living"
						+ " of warmer months",
				"http://www.foxspizza.com/wp-content/uploads/2013/04/barbecue-chicken-pizza.jpg", 12.99));
		getServletContext().setAttribute("items", items);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// delete items from menu
		List<FoodItem> items = (List<FoodItem>) getServletContext()
				.getAttribute("items");
		String deletefooditems = (request.getParameter("Submit"));

		int index = -1;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(deletefooditems)) {
				index = i;
				items.remove(index);
			}
		}

		getServletContext().setAttribute("items", items);

		request.getRequestDispatcher("/WEB-INF/AdminInventory.jsp").forward(request, response);
	}
}
