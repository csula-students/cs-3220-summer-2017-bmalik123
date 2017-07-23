package food;
import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/shopping-cart/delete")
public class FoodDeletion extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		int carti= -1;
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getId() == id) {
				carti = i;
			}
		}
		cart.remove(carti);
		getServletContext().setAttribute("cart", cart);
		response.sendRedirect("/GuestBook/shopping-cart");
	}
}