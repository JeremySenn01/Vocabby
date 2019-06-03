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
//				Person person = new Person(entries.getInt("id"), entries.getString("firstname"),
//						entries.getString("lastname"), entries.getString("email"));
//
//				people.put(String.valueOf(person.getId()), person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub

	}

}
