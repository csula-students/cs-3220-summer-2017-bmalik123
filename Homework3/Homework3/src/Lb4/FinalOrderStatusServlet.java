package Lb4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Lb4.FoodItem;
/**
 * Servlet implementation class FinalOrderStatusServlet
 */
@WebServlet(loadOnStartup = 1, urlPatterns = {"/FinalOrderStatusServlet"})
public class FinalOrderStatusServlet extends HttpServlet{
	public void init() {

		List<Order> order = new ArrayList<>();
		getServletContext().setAttribute("order", order);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");

		// send food menu data to index.jsp
		request.getRequestDispatcher("/WEB-INF/statuses.jsp").forward(request, response);
	}
}
