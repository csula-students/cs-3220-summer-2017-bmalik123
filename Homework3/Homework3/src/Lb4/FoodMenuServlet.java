package Lb4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Lb4.FoodItem;

@WebServlet(loadOnStartup = 1, urlPatterns = {"/FoodMenuServlet"})
public class FoodMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> items = (List<FoodItem>) getServletContext().getAttribute("items");
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		String addfoodname = (request.getParameter("Add"));
		System.out.println(addfoodname);
		FoodItem foodItem = null;

		for (FoodItem entries : items) {
			if (entries.getName().equals(addfoodname)) {
				foodItem = entries;
				cart.add(new FoodItem(foodItem.getId(), foodItem.getName(), foodItem.getdescription(),
						foodItem.getImageURL(), foodItem.getprice()));
			}
		}

		getServletContext().setAttribute("cart", cart);

		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

}
