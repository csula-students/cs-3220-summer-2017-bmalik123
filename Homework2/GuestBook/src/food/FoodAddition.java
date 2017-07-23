package food;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FoodAddition
 */
@WebServlet("/menu/add")
public class FoodAddition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries=(List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem newFoodItem=null;
		for(FoodItem entry:entries)
		{
			if(entry.getId()==id) {
				newFoodItem=entry;
			}
		}
		response.setContentType("text/html");
		PrintWriter printWriter=response.getWriter();
		List<FoodItem> cart=(List<FoodItem>) getServletContext().getAttribute("cart");
		cart.add(new FoodItem(id, newFoodItem.getName(), newFoodItem.getdescription(), newFoodItem.getImageURL(), newFoodItem.getprice()));
	getServletContext().setAttribute("cart",cart);

	response.sendRedirect("/GuestBook/shopping-cart");
	}
	

	
}
