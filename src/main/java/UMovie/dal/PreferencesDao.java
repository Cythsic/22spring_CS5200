package UMovie.dal;

import UMovie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PreferencesDao {
	protected ConnectionManager connectionManager;
	
	private static PreferencesDao instance = null;
	protected PreferencesDao() {
		connectionManager = new ConnectionManager();
	}
	public static PreferencesDao getInstance() {
		if(instance == null) {
			instance = new PreferencesDao();
		}
		return instance;
	}

	public Preferences create(Preferences preference) throws SQLException {
		String insertPreference = "INSERT INTO Preferences(UserName,PreferedGenres) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPreference,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, preference.getUser().getUserName());
			insertStmt.setString(2, preference.getPreferedGenre());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int preferenceId = -1;
			if(resultKey.next()) {
				preferenceId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			preference.setPreferenceId(preferenceId);
			return preference;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
}


	public List<Preferences> getPreferencesByUserName(String userName) throws SQLException {
		List<Preferences> preferences = new ArrayList<Preferences>();
		String selectPreferences =
			"SELECT PreferenceId,UserName,PreferedGenres FROM Preferences WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPreferences);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int preferenceId = results.getInt("PreferenceId");
				String preferedGenre = results.getString("PreferedGenres");
				Users user = usersDao.getUserByUserName(userName);
				Preferences preference = new Preferences(preferenceId, user, preferedGenre);
				preferences.add(preference);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return preferences;
	}
	
    public Preferences getPreferenceByPreferenceId(Integer preferenceId) throws SQLException {
        String selectPreference =
                "SELECT PreferenceId,UserName,PreferedGenres " +
                        "FROM Preferences " +
                        "WHERE PreferenceId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPreference);
            selectStmt.setInt(1, preferenceId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            if(results.next()) {
                Integer resultPreferenceId = results.getInt("PreferenceId");
                String userName = results.getString("UserName");
                String preferedGenre = results.getString("PreferedGenres");
                Users user = usersDao.getUserByUserName(userName);
                Preferences preference = new Preferences(resultPreferenceId, user, preferedGenre);
                return preference;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
	
	
	public Preferences updatePreferedGenre(Preferences preference, String newPreferedGenre) throws SQLException {
		String updatePreference = "UPDATE Preferences SET PreferedGenres=? WHERE PreferenceId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePreference);
			updateStmt.setString(1, newPreferedGenre);
			updateStmt.setInt(2, preference.getPreferenceId());
			updateStmt.executeUpdate();
			preference.setPreferedGenre(newPreferedGenre);
			return preference;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Preferences delete(Preferences preference) throws SQLException {
		String deletePreference = "DELETE FROM Preferences WHERE PreferenceId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePreference);
			deleteStmt.setInt(1, preference.getPreferenceId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
