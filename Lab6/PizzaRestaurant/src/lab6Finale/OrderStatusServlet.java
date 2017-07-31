package lab6Finale;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab6Finale.FoodItem;
@WebServlet(loadOnStartup = 1, urlPatterns = {"/OrderStatusServlet"})
public class OrderStatusServlet extends HttpServlet {

	public void init() {
		
		List<FoodItem> cart = new ArrayList<>();
		getServletContext().setAttribute("cart", cart);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		String removefoodname = (request.getParameter("remove"));

		int index = -1;
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getName().equals(removefoodname)) {
				index = i;
				cart.remove(index);
			}
		}

		getServletContext().setAttribute("cart", cart);

		
		request.getRequestDispatcher("/WEB-INF/jdbc/order.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");

		for (FoodItem entry : cart) {
		
			order.add(new Order(order.size(),request.getParameter("custname"),Order.Status.IN_QUEUE,new Date(),entry));
			getServletContext().setAttribute("order", order);
			
			
		}

		cart.clear();
		request.getRequestDispatcher("/WEB-INF/jdbc/statuses.jsp").forward(request, response);

	}
}
