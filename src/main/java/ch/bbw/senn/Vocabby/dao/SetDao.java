package ch.bbw.senn.Vocabby.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ch.bbw.senn.Vocabby.Set;

public class SetDao implements IDao<Set> {

	@Override
	public Optional<Set> getById(String id) {
		ResultSet resultSet = null;
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM StudySet WHERE Id = ?")) {

			statement.setString(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Set set = new Set(UUID.fromString(resultSet.getString("UserId_FK")), resultSet.getString("Name"), resultSet.getString("Theme"),
						resultSet.getDate("CreationDate").toLocalDate(), null);
				set.setId(UUID.fromString(id));

				return Optional.of(set);

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
	public List<Set> getAll(String userId_FK) {
		List<Set> setResult = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM StudySet WHERE UserId_FK = ?")) {

			statement.setString(1, userId_FK);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Set set = new Set(UUID.fromString(resultSet.getString("UserId_FK")), resultSet.getString("Name"), resultSet.getString("Theme"),
						resultSet.getDate("CreationDate").toLocalDate(), null);
				set.setId(UUID.fromString(resultSet.getString("Id")));
				setResult.add(set);
			}
			connection.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return setResult;
	}

	@Override
	public boolean save(Set set) {
		if (getById(set.getId().toString()).isPresent()) {
			return false;
		}

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO StudySet VALUES (?,?,?,?)")) {

			statement.setString(1, set.getId().toString());
			statement.setString(2, set.getName());
			statement.setString(3, set.getTheme());
			statement.setDate(3, Date.valueOf(set.getCreationDate()));

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
	public void update(Set set, String[] params) {

	}

	@Override
	public boolean delete(Set set) {
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM StudySet WHERE Id = ?")) {

			statement.setString(1, set.getId().toString());
			statement.executeQuery();

			return statement.executeUpdate() > 0;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deleteAll(List<Set> t) {
		
	}

}
