package lab6Finale;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab6Finale.FoodItem;
/**
 * Servlet implementation class AdminOrderStatusesServlet
 */
@WebServlet("/AdminOrderStatusesServlet")
public class AdminOrderStatusesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		
		request.setAttribute("date", new Date());
		request.getRequestDispatcher("/WEB-INF/jdbc/AdminOrderStatuses.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		String editfoodname = (request.getParameter("edit"));
		
		Order leEntry = null;
		int index = -1;
		for (int i = 0; i < order.size(); i++) {
			if (order.get(i).getfoodItems().getName().equals(editfoodname)) {
				leEntry = order.get(i);
				index = i;
			}
		}
		String inputStat = request.getParameter("status");
		Order.Status stat = Order.Status.valueOf(inputStat);
		leEntry.setStatus(stat);
		order.set(index, new Order(leEntry.getId(), leEntry.getcustName(),
		 leEntry.getStatus(),leEntry.getdate(),leEntry.getfoodItems()));

		getServletContext().setAttribute("order", order);
		
		request.getRequestDispatcher("/WEB-INF/jdbc/AdminOrderStatuses.jsp").forward(request, response);

}
}
