package restaurant;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RandomRestaurantServlet
 */
@WebServlet("/suggest/restaurants/random")
public class RandomRestaurantServlet extends HttpServlet {
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
		    out.println("<h2> You Should go to :<h2><br>");
			out.println("Image URL: <input name='ImageURL' type='text'/></br>");
			out.println("<fieldset>");
			out.println("<h2> Design:</h2>");
			out.println("<form method=\"post\">\r\n" + 
					"	<input name=\"designRate\" id=\"design_rate_1\" type=\"radio\" value=\"1\" >\r\n" + 
					"	<label for=\"design_rate_1\">1</label>\r\n" + 
					"\r\n" + 
					"	<input name=\"designRate\" id=\"design_rate_2\" type=\"radio\" value=\"2\">\r\n" + 
					"	<label for=\"design_rate_2\">2</label>\r\n" + 
					"</form>");
			out.println("<h2> taste:</h2>");
			out.println("<form method=\"post\">\r\n" + 
					"	<input name=\"designRate\" id=\"design_rate_1\" type=\"radio\" value=\"1\" checked>\r\n" + 
					"	<label for=\"design_rate_1\">1</label>\r\n" + 
					"\r\n" + 
					"	<input name=\"designRate\" id=\"design_rate_2\" type=\"radio\" value=\"2\">\r\n" + 
					"	<label for=\"design_rate_2\">2</label>\r\n" + 
					"</form>");
			out.println("  &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp <button><a href='/cs3220xstu16/suggest/restaurants/random'>Feeling luck?</a></button>");
			out.println("  &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   <button><a href='/cs3220xstu16/suggest/restaurants/random/list'>See the List</a></button>");
		}
}
