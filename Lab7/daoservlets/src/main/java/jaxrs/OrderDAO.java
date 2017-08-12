package jaxrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import jaxrs.Order.Status;

import java.util.ArrayList;

public class OrderDAO implements DAO<Order> {

	public List<Order> list() {
		List<Order> list = new ArrayList<>();
		Database db = new Database();
		try (Connection c = db.connection()) {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM orders INNER JOIN order_foods INNER JOIN food_items WHERE orders.id = order_foods.order_id AND food_items.id = order_foods.food_id");
			while (rs.next()) {
				int id = rs.getInt("orders.id");

				FoodItem fooditem = new FoodItem(rs.getInt("food_items.id"), rs.getString("food_name"),
						rs.getString("description"), rs.getString("image"), rs.getDouble("price"));

				list.add(new Order(rs.getInt("orders.id"), rs.getString("customer_name"),
						Status.valueOf(rs.getString("status")), rs.getDate("date"), fooditem));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}

	@Override
	public Optional<Order> get(int id) {
		Optional<Order> toReturn = Optional.empty();
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"SELECT * FROM orders INNER JOIN order_foods INNER JOIN food_items WHERE orders.id = ? AND order_foods.order_id = ? AND order_foods.food_id = food_items.id");
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			ResultSet rs = pstmt.executeQuery();

			boolean nOrder = false;
			Order newOrder = null;
			if (rs.next()) {

				FoodItem add_fooditems = new FoodItem(rs.getInt("food_items.id"), rs.getString("food_name"),
						rs.getString("description"), rs.getString("image"), rs.getDouble("price"));

				newOrder = new Order(rs.getInt("orders.id"), rs.getString("customer_name"), Status.valueOf(rs.getString("status")),
						rs.getDate("date"), add_fooditems);

				toReturn = Optional.of(newOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public void add(Order orders) {

		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c
					.prepareStatement("INSERT INTO orders (customer_name, status, date) VALUES ( ?, ?, ?)");
			pstmt.setString(1, orders.getcustName());
			pstmt.setString(2, orders.getStatus().toString());
			java.util.Date util_StartDate = orders.getdate();
			java.sql.Date sql_StartDate = new java.sql.Date(util_StartDate.getTime());
			pstmt.setDate(3, sql_StartDate);
			pstmt.executeUpdate();

			PreparedStatement ps = c.prepareStatement("INSERT INTO order_foods (order_id, food_id) VALUES (?, ?)");
			ps.setInt(1, orders.getId());
			ps.setInt(2, orders.getfoodItems().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Order order) {
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement("UPDATE orders SET status= ? WHERE id= ?");
			pstmt.setString(1, order.getStatus().toString());
			pstmt.setInt(2, order.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		Database db = new Database();
		try (Connection c = db.connection()) {
			PreparedStatement pstmt = c.prepareStatement("DELETE FROM orders WHERE id = ? ");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			pstmt = c.prepareStatement("DELETE FROM order_foods WHERE order_id = ? ");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
