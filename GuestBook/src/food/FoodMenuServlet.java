package food;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(loadOnStartup=1, urlPatterns={"/menu"})

public class FoodMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		// tell browser this is html document
		response.setContentType("text/html");

		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1> CalPizza-Welcome to the California pizza Factory</h1>");
		out.println("<table>");
		for (FoodItem entry: entries) {
			out.println(
				"<tr>" + 
					"<td>" + entry.getName() + " </td>" + 
					"<td>" + entry.getdescription() + "</td>" +
					"<td>" + "<img src="+entry.getImageURL()+">" + "</td>" +
					"<td>" + entry.getprice() + "</td>" +
					
				"</tr>"
			);
		}
		out.println("</table>");
		
	}
}