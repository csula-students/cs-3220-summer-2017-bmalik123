package PizzaRestaurant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminCreateFoodItemsServlet
 */
@WebServlet("/PizzaRestaurant/AdminCreateFoodItemsServlet")
public class AdminCreateFoodItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jdbc/AdminCreateFoodItems.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FoodItemDAO dao = new FoodItemDAO();
		int id = dao.list().size();
		String name = request.getParameter("name");
		String image= request.getParameter("image");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		dao.add(new FoodItem(id,name,description,image,price));
		response.sendRedirect("/PizzaRestaurant/AdminCalPizzaServlet");
	}
}