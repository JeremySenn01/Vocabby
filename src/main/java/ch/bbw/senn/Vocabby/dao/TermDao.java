package ch.bbw.senn.Vocabby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ch.bbw.senn.Vocabby.Term;

public class TermDao implements IDao<Term> {

	@Override
	public Optional<Term> getById(String id) {
		ResultSet resultSet = null;
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Card WHERE Id = ?")) {

			statement.setString(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Term term = new Term(UUID.fromString(resultSet.getString("SetId_FK")), resultSet.getString("Original"), resultSet.getString("Translated"));
				term.setId(UUID.fromString(id));

				return Optional.of(term);

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
	public List<Term> getAll(String setId_FK) {
		List<Term> termResult = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Card")) {

			while (resultSet.next()) {
				Term term = new Term(UUID.fromString(resultSet.getString("SetId_FK")), resultSet.getString("Original"), resultSet.getString("Translated"));
				term.setId(UUID.fromString(resultSet.getString("Id")));
				termResult.add(term);
			}
			connection.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return termResult;
	}

	@Override
	public boolean save(Term term) {
		if (getById(term.getId().toString()).isPresent()) {
			return false;
		}

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO Card VALUES (?,?,?)")) {

			statement.setString(1, term.getId().toString());
			statement.setString(2, term.getOriginal());
			statement.setString(3, term.getTranslated());

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
	public void update(Term term, String[] params) {

	}

	@Override
	public boolean delete(Term term) {
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Card WHERE Id = ?")) {

			statement.setString(1, term.getId().toString());
			statement.executeQuery();

			return statement.executeUpdate() > 0;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deleteAll(List<Term> t) {
		
	}
}
