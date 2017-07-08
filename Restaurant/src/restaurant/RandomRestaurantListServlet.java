package restaurant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/suggest/restaurants/random/list")
public class RandomRestaurantListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<restaurant> entries = (List<restaurant>) getServletContext().getAttribute("entries");
		response.setContentType("text/html");

		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1> ***Whats for lunch?***</h1>");
		out.println("<table>");
		for (restaurant entry: entries) {
			out.println(
				"<tr>" + 
					"<td>" + entry.getName() + " </td>" + 
			
					"<td>" + "<img src="+entry.getImageURL()+">" + "</td>" +
					"<td>" + entry.getdesignRatings()+">"+"/td>"+
					"<td>" + entry.gettasteRatings()+">"+"</td>" +
			        
					
				    "</tr>"
			);
		}
		out.println("</table>");
		out.println("  &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp <button><a href='/cs3220xstu16/suggest/restaurants/random'>Feeling luck?</a></button>");
		out.println("  &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   &nbsp   <button><a href='/cs3220xstu16/suggest/restaurants/random/list'>See the List</a></button>");
	}
}