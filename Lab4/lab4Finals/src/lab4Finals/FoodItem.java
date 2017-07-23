package lab4Finals;

public class FoodItem {

	public final int id;
	public final String name;
	public final String description;
	public final String ImageURL;
	public final double price;

	public FoodItem (int id, String name, String description,String ImageURL,Double price) {
		this.id = id;
		this.name = name;
		this.description=description;
		this.ImageURL=ImageURL;
		this.price=price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getdescription() {
		return description;
	}
	public String getImageURL() {
	 return ImageURL;
		
	}
	public double getprice() {
		return price;
	}

}