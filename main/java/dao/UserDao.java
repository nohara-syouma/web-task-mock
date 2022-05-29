package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Enitity.User;

public class UserDao {

	private static final String SQL_SELECT_ID_AND_PASS = "SELECT * FROM users WHERE login_id = ? AND password = ?";
//	private static final String SQL_SELECT_FIND = "SELECT p.product_id product_id, p.name name, p.price, c.name c_name FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE p.name LIKE ?";
//	private static final String SELECT_ALL = "SELECT p.product_id product_id, p.name name, p.price, c.name c_name FROM products p INNER JOIN categories c ON p.category_id = c.id";
//	
	private Connection connection;

	public UserDao(Connection connection) {
		this.connection = connection;
	}

	public User findByIdAndPass(String id, String pass) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_AND_PASS)) {
			stmt.setString(1, id);
			stmt.setString(2, pass);
			//System.out.println(SQL_SELECT_ID_AND_PASS);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getString("login_id"), rs.getString("name"), rs.getString("password"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}