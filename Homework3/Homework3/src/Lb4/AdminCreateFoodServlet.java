package Lb4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/AdminCreateFoodServlet" })
public class AdminCreateFoodServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Double price = 0.00;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/AdminCreateFood.jsp");

		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<FoodItem> items = (List<FoodItem>) getServletContext().getAttribute("items");

		String name = request.getParameter("name");

		String description = request.getParameter("description");

		String imageUrl = request.getParameter("ImageURL");
		try {
			price = Double.parseDouble(request.getParameter("price"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		items.add(new FoodItem(items.size(), name, description, imageUrl, price));

		request.setAttribute("items", items);

		response.sendRedirect(request.getContextPath() + "/RestaurantAdminServlet");
	}

}