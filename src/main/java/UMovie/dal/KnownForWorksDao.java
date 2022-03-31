package UMovie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UMovie.model.KnownForWorks;

public class KnownForWorksDao {
	private static final String BASE_QUERY = "SELECT NConst, KnownForWorkId, "
			+ "KnownForWorks.Tconst as Tconst, Movies.OriginalTitle as OriginalTitle "
			+ "FROM "
			+ "KnownForWorks AS KnownForWorks "
			+ "INNER JOIN Movies AS Movies "
			+ "ON KnownForWorks.Tconst = Movies.Tconst ";

	private static KnownForWorksDao dao = null;

	private final ConnectionManager connectionManager;

	protected KnownForWorksDao() {
		connectionManager = new ConnectionManager();
	}

	public static KnownForWorksDao getInstance() {
		if (dao == null) {
			dao = new KnownForWorksDao();
		}
		return dao;
	}

	public List<KnownForWorks> getKnownForWorks(String nConst) throws SQLException {
		String query = BASE_QUERY + "WHERE Nconst =?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(query);
			selectStmt.setString(1, nConst);
			results = selectStmt.executeQuery();
			return parseResults(results);
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
	

	private List<KnownForWorks> parseResults(ResultSet results) throws SQLException {
		List<KnownForWorks> result = new ArrayList<>();
		try {
			while (results.next()) {
				String nconst = results.getString("Nconst");
				int id = results.getInt("KnownForWorkId");
				String tconst = results.getString("Tconst");
				String originalTitle = results.getString("OriginalTitle");
				result.add(new KnownForWorks(id, nconst, tconst, originalTitle));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
