package PizzaRestaurant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/PizzaRestaurant/editFoodItemsServlet" })
public class EditFoodItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		FoodItemDAO dao = new FoodItemDAO();
		FoodItem item = dao.get(id).get();
		request.setAttribute("item", item);
		request.getRequestDispatcher("/WEB-INF/jdbc/editfooditems.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FoodItem foodUpdate = new FoodItem(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),request.getParameter("description") ,request.getParameter("image"),Double.parseDouble(request.getParameter("price")) );
		FoodItemDAO dao = new FoodItemDAO();
		dao.update(foodUpdate);

		response.sendRedirect("/cs3220xstu16/PizzaRestaurant/AdminCalPizzaServlet");
	}

}