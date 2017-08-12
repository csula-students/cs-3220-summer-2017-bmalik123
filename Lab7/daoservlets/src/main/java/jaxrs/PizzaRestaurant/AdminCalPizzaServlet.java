package PizzaRestaurant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PizzaRestaurant/AdminCalPizzaServlet")
public class AdminCalPizzaServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FoodItemDAO dao = new FoodItemDAO();
		if (request.getParameter("Submit") != null) {
			int id = Integer.parseInt(request.getParameter("Submit"));
			dao.delete(id);
		}

		request.setAttribute("list", dao.list());
		request.getRequestDispatcher("/WEB-INF/jdbc/AdminCalPizzaMenu.jsp").forward(request, response);
	}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			response.sendRedirect(request.getContextPath() + "/PizzaRestaurant/AdminCreateFoodItemsServlet");

		}
	
}
