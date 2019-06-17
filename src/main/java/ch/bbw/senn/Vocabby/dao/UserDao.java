package ch.bbw.senn.Vocabby.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ch.bbw.senn.Vocabby.User;

public class UserDao implements IDao<User> {

	private Connection connection;

	public UserDao() {
		try {
			connection = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<User> getById(String username) {

		try (ResultSet entries = connection.createStatement().executeQuery("SELECT * FROM user")) {

			while (entries.next()) {
				User user = new User(entries.getString("Username"), entries.getString("Password"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public void save(User user) {

	}

	@Override
	public void update(User user, String[] params) {

	}

	@Override
	public void delete(User user) {

	}

}
