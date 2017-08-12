package PizzaRestaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import PizzaRestaurant.FoodItem;

public class FoodItemDAO implements DAO<FoodItem> {
	public List<FoodItem> list() {
		List<FoodItem> list = new ArrayList<>();
		Database db = new Database();
		try (Connection c = db.connection()) {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM food_items");
			while (rs.next()) {
				list.add(new FoodItem(rs.getInt("id"), rs.getString("food_name"), rs.getString("description"),
						rs.getString("image"), rs.getDouble("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}

	public Optional<FoodItem> get(int id) {
		Optional<FoodItem> toReturn = Optional.empty();
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement("SELECT * FROM food_items WHERE food_items.id = ? ");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				FoodItem foodToEdit = new FoodItem(rs.getInt("id"), rs.getString("food_name"),
						rs.getString("description"), rs.getString("image"), (double) rs.getFloat("price"));
				toReturn = Optional.of(foodToEdit);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	public void add(FoodItem entry) {
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"INSERT INTO food_items ( food_name, description, image, price) VALUES (?, ?, ?, ?)");
			pstmt.setString(1, entry.getName());
			pstmt.setString(2, entry.getdescription());
			pstmt.setString(3, entry.getImageURL());
			pstmt.setFloat(4, (float) entry.getprice());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(FoodItem entry) {
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"UPDATE food_items SET id = ?, food_name = ?, description = ?, image = ?, price = ? WHERE id = ?");
			pstmt.setInt(1, entry.getId());
			pstmt.setString(2, entry.getName());
			pstmt.setString(3, entry.getdescription());
			pstmt.setString(4, entry.getImageURL());
			pstmt.setDouble(5, entry.getprice());
			pstmt.setInt(6, entry.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement("DELETE FROM food_items WHERE food_items.id = ? ");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
