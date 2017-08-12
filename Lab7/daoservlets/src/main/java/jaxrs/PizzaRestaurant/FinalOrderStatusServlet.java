package PizzaRestaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PizzaRestaurant.FoodItem;
/**
 * Servlet implementation class FinalOrderStatusServlet
 */
@WebServlet(loadOnStartup = 1, urlPatterns = {"/PizzaRestaurant/FinalOrderStatusServlet"})
public class FinalOrderStatusServlet extends HttpServlet{
	public void init() {

		List<Order> order = new ArrayList<>();
		getServletContext().setAttribute("order", order);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");

		// send food menu data to index.jsp
		request.getRequestDispatcher("/WEB-INF/jdbc/statuses.jsp").forward(request, response);
	}
}
