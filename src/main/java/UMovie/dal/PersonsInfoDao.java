package UMovie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UMovie.model.KnownForWorks;
import UMovie.model.PersonsInfo;

public class PersonsInfoDao {
	private static final String BASE_QUERY = "SELECT Nconst, PrimaryName, BirthYear, DeathYear FROM PersonsInfo ";

	private static PersonsInfoDao dao = null;

	private final ConnectionManager connectionManager;

	protected PersonsInfoDao() {
		connectionManager = new ConnectionManager();
	}

	public static PersonsInfoDao getInstance() {
		if (dao == null) {
			dao = new PersonsInfoDao();
		}
		return dao;
	}

	public PersonsInfo getPersonsInfoByNConst(String nConst) throws SQLException {
		String query = BASE_QUERY + "WHERE Nconst =?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(query);
			selectStmt.setString(1, nConst);
			results = selectStmt.executeQuery();
			List<PersonsInfo> persons = parseResults(results);
			return persons.isEmpty() ? null : persons.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}
	
	public List<PersonsInfo> searchPersonsInfoByName(String name) throws SQLException {
		String query = String.format(BASE_QUERY + "WHERE LOWER(PrimaryName) LIKE '%%%s%%' LIMIT 10;", name);

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<PersonsInfo> persons = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(query);
			results = selectStmt.executeQuery();
			persons = parseResults(results);
			return persons;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}


	private List<PersonsInfo> parseResults(ResultSet results) throws SQLException {
		List<PersonsInfo> result = new ArrayList<>();
		try {
			while (results.next()) {
				String nconst = results.getString("Nconst");
				String primaryName = results.getString("PrimaryName");
				int birthYear = results.getInt("BirthYear");
				int deathYear = results.getInt("DeathYear");
				List<KnownForWorks> knownForWorks = KnownForWorksDao.getInstance().getKnownForWorks(nconst);
				result.add(new PersonsInfo(nconst, primaryName, birthYear, deathYear, knownForWorks));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
