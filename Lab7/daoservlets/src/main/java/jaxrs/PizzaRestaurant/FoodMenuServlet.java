package PizzaRestaurant;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/PizzaRestaurant/FoodMenuServlet"})
public class FoodMenuServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FoodItemDAO dao = new FoodItemDAO();

		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		
		String addingfoodname = (request.getParameter("Add"));
		FoodItem addfooditems = null;
		for (FoodItem entries : dao.list()) {
			if (entries.getName().equals(addingfoodname)) {
				addfooditems = entries;
				cart.add(new FoodItem(addfooditems.getId(), addfooditems.getName(),addfooditems.getdescription(),addfooditems.getImageURL(), 
						addfooditems.getprice() ));
			}
		}
		
		getServletContext().setAttribute("cart", cart);
		request.setAttribute("list", dao.list());
		request.getRequestDispatcher("/WEB-INF/jdbc/index.jsp")
			.forward(request, response);
	}
}