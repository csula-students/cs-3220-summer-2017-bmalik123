package food;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/orders/edit")
public class EditOrderStatusServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		Order newOrder = null;

		for (Order entries : order) {
			if (entries.getId() == id) {
				newOrder = entries;
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Editing Order Status</h1>");
		
		out.println("<form method=\"post\">");
		out.println("</br></br><select id =\"status\" name = \"status\"></br></br>");
		out.println("<option value =\"IN_QUEUE\" selected>" + "IN_QUEUE" + "</option>");
		out.println("<option value =\"IN_PROGRESS\">" + "IN_PROGRESS" + "</option>");
		out.println("<option value =\"COMPLETED\">" + "COMPLETED" + "</option>");
		out.println("</select>");
		out.println("<button>Edit</button>");
		out.println("</form>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		Order newOrder = null;
		int orderi = -1;
		for (int i = 0; i < order.size(); i++) {
			if (order.get(i).getId() == id) {
				newOrder = order.get(i);
				orderi = i;
			}
		}
		String inputStat = request.getParameter("status");
		Order.Status stat=Order.Status.valueOf(inputStat);
		newOrder.setStatus(stat);
		order.set(orderi, new Order(newOrder.getId(), newOrder.getcustName(), newOrder.getStatus(),
				newOrder.getdate(),newOrder.getfoodItems()));
		getServletContext().setAttribute("order", order);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.sendRedirect("/GuestBook/admin/orders");
		

	}
}