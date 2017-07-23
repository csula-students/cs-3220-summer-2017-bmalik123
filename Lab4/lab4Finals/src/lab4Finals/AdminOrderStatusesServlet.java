package lab4Finals;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminOrderStatusesServlet
 */
@WebServlet("/AdminOrderStatusesServlet")
public class AdminOrderStatusesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("date", new Date());
		
		request.getRequestDispatcher("/WEB-INF/AdminOrderStatuses.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	
			throws ServletException, IOException {

		doGet(request, response);

}
}