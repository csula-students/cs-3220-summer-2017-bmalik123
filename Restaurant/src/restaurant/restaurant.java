package restaurant;

import java.util.ArrayList;
import java.util.List;

public class restaurant {
	

		public final int id;
		public final String name;
		
		public final String ImageURL;
		List<Integer> designRatings=new ArrayList<Integer>();
		List<Integer> tasteRatings=new ArrayList<Integer>();
		

		public restaurant(int id, String name,String ImageURL,List<Integer> designRatings,List<Integer> tasteRatings) {
			this.id = id;
			this.name = name;
			
			this.ImageURL=ImageURL;
			this.designRatings=designRatings;
			this.tasteRatings=tasteRatings;
			
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		
		public String getImageURL() {
		 return ImageURL;
			
		}
		public List<Integer> getdesignRatings(){
			return designRatings;
		}
		public List<Integer> gettasteRatings(){
			return tasteRatings;
		}
		

	}


