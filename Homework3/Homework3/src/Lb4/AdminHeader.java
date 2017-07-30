package Lb4;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class AdminHeader extends SimpleTagSupport {
	
	  public void doTag() throws JspException, IOException {
			JspWriter out = getJspContext().getOut();
		
			
			
			out.println("<header>");
			out.println("<section class='container'>");
				out.println("<img class='logo' src='http://lh6.ggpht.com/raTqkvNCCyy25tiX22VKq1uSnVUqKspeJ5opFceK0Vjnf3mzUfwfI6X2QSMrrIWsUkI=w300' alt='' style='width:90px;height:60px;'>");
				out.println("<h1>CalPizza-Admin</h1>");
				out.println("<span class='fill'></span>");
				out.println("<ul>");
				out.println("<li><a class='active' href='http://localhost:8080/Homework3/FoodMenuServlet'>Customer-Menu</a></li>");
					out.println("<li><a class='active' href='http://localhost:8080/Homework3/RestaurantAdminServlet'>Admin-Menu</a></li>");
					
					out.println("<li><a href='http://localhost:8080/Homework3/AdminCreateFoodServlet'>AddFood</a></li>");
					out.println("<li><a href='http://localhost:8080/Homework3/AdminOrderStatusesServlet'> OrderStatuses</a></li>");
				
				out.println("</ul>");
			out.println("</section>");
		out.println("</header>");
		}


}
