package jaxrs;

import java.util.Date;

import jaxrs.FoodItem;
import jaxrs.Order.Status;

public class Order {

	public final int id;
	public final FoodItem foodItems;
	public final String custname;
	public Status status;
	public final Date date;
	public enum  Status{
		IN_QUEUE,IN_PROGRESS,COMPLETED
	
	}

	public Order (int id, String custname, Status status,Date date,FoodItem foodItems) {
		this.id = id;
		this.custname = custname;
		this.status=status;
		this.date=date;
		this.foodItems=foodItems;
	}
	
	public Order () {
		this.id = 0;
		this.custname = "";
		this.status=null;
		this.date=null;
		this.foodItems=null;
	}

	public int getId() {
		return id;
	}

	public String getcustName() {
		return custname;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	

	
	public Date getdate() {
	 return date;
		
	}
	public FoodItem getfoodItems() {
		return foodItems;
	}

	

}
