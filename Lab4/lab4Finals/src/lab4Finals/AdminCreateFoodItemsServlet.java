package lab4Finals;

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

@WebServlet(urlPatterns= {"/AdminCreateFoodItemsServlet"})
public class AdminCreateFoodItemsServlet extends HttpServlet {

	 Double price=0.0;
	 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		 RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/AdminCreateFoodItems.jsp");
	        
	       dispatcher.forward(request, response);
		
	}
	
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
		 	List<FoodItem> foodItems = (List<FoodItem>) request.getSession(false).getAttribute("fooditems");
		 	
		 	if(foodItems==null)
		 		
		 		foodItems=new ArrayList<FoodItem>();
		 	
			String name = request.getParameter("name");
			
			String description = request.getParameter("description");
			
			String ImageUrl = request.getParameter("ImageURL");
			
			try{
				
			 price = Double.parseDouble(request.getParameter("price"));
			}
			catch(Exception ex){
				
			
			}
			
			foodItems.add(new FoodItem(foodItems.size(), name, description, ImageUrl, price));
			
			request.setAttribute("date", new Date());
			
			request.getSession(false).setAttribute("fooditems", foodItems);
			
		response.sendRedirect(request.getContextPath()+"/AdminCalPizzaServlet");
	    }
	

}