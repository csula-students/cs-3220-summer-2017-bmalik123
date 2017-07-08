
package food;
import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin/foods/edit")
public class EditFoodAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem leEntry = null;
		for (FoodItem entry: entries) {
			if (entry.getId() == id) {
				leEntry = entry;
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Editing comment</h1>");
		out.println("<form method=\"post\">");
		out.println(" Name: <input name='name' type='text' value='" + leEntry.getName() + "'/></br>");
		out.println("Description: <textarea name='description'>" + leEntry.getdescription() + "</textarea></br>");
		out.println(" Image URL: <input name='ImageURL' type='text' value='" + leEntry.getImageURL() + "'/></br>");
		out.println("Price: <textarea name='price'>" + leEntry.getprice() + "</textarea></br>");
		out.println("<button>Edit</button>");
		out.println("</form>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem leEntry = null;
		int index = -1;
		for (int i = 0; i < entries.size(); i ++) {
			if (entries.get(i).getId() == id) {
				leEntry = entries.get(i);
				index = i;
			}
		}
		entries.set(index, new FoodItem(
			leEntry.getId(),
			request.getParameter("name"),
			request.getParameter("description"),
			request.getParameter("ImageURL"),
			request.getParameter("price")
		));
		getServletContext().setAttribute("entries", entries);


		response.sendRedirect("/cs3220xstu16/admin/foods/");
	}
}