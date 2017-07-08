package restaurant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			
			// tell browser this is html document
			response.setContentType("text/html");

			out.println("<head>");
			out.println("<style>body { " +
			"}</style>");
			out.println("</head>");
            
			out.println("<h1 > &nbsp &nbsp&nbsp&nbsp  &nbsp  &nbsp  &nbsp &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp             What's For lunch?    &nbsp  &nbsp   &nbsp                           </h1>");
			
			out.println("  &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp <button><a href='/cs3220xstu16/suggest/restaurants/random'>Feeling luck?</a></button>");
			out.println("  &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   <button><a href='/cs3220xstu16/suggest/restaurants/random/list'>See the List</a></button>");
		}
}
