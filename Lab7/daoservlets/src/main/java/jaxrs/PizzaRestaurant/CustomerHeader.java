package PizzaRestaurant;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class CustomerHeader extends SimpleTagSupport {
	
	  public void doTag() throws JspException, IOException {
			JspWriter out = getJspContext().getOut();
		
			
			
			out.println("<header>");
			out.println(
					"<link rel=' icon' href='http://cdn.shopify.com/s/files/1/0736/3911/t/1/assets/favicon.png?7941968939387777154'>");
			out.println("<title> Home-CalPizza</title>");
			out.println("<section class='container'>");
				out.println("<img class='logo' src='http://lh6.ggpht.com/raTqkvNCCyy25tiX22VKq1uSnVUqKspeJ5opFceK0Vjnf3mzUfwfI6X2QSMrrIWsUkI=w300' alt='' style='width:90px;height:60px;'>");
				out.println("<h1>CalPizza</h1>");
				out.println("<span class='fill'></span>");
				out.println("<ul>");
				out.println("<li><a class='active' href='/PizzaRestaurant/FoodMenuServlet'>Customer-Menu</a></li>");
				out.println("<li><a class='active' href='/PizzaRestaurant/AdminCalPizzaServlet'>Admin-Menu</a></li>");
					out.println("<li><a class='active' href='/PizzaRestaurant/OrderStatusServlet'>Order</a></li>");
					out.println("<li><a class='active' href='/PizzaRestaurant/FinalOrderStatusServlet'>Statuses</a></li>");
					
				
				out.println("</ul>");
			out.println("</section>");
		out.println("</header>");
		}


}