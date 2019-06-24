package ch.bbw.senn.Vocabby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ch.bbw.senn.Vocabby.User;

public class UserDao implements IDao<User> {

	@Override
	public Optional<User> getById(String id) {
		ResultSet resultSet = null;
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE Id = ?")) {

			statement.setString(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = new User(resultSet.getString("Username"), resultSet.getString("Password"), null);
				user.setId(UUID.fromString(id));
				;
				return Optional.of(user);

			}
			connection.close();
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<User> getAll(String id_FK) {
		List<User> userResult = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				ResultSet result = connection.createStatement().executeQuery("SELECT * FROM user")) {

			while (result.next()) {
				User user = new User(result.getString("Username"), result.getString("Password"), null);
				user.setId(UUID.fromString(result.getString("Id")));
				userResult.add(user);
			}
			connection.close();
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return userResult;
	}

	@Override
	public boolean save(User user) {
		if (getById(user.getId().toString()).isPresent()) {
			return false;
		}

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?)")) {

			statement.setString(1, user.getId().toString());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.execute();

			statement.close();
			connection.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return false;

	}

	@Override
	public void update(User user, String[] params) {

	}

	@Override
	public boolean delete(User user) {
		return false;
		
	}

	@Override
	public void deleteAll(List<User> t) {
		
	}

}
