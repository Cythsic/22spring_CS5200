package UMovie.dal;

import UMovie.model.Likes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikesDao {
	protected ConnectionManager connectionManager;

	private static LikesDao instance = null;
	protected LikesDao() {
		connectionManager = new ConnectionManager();
	}
	public static LikesDao getInstance() {
		if(instance == null) {
			instance = new LikesDao();
		}
		return instance;
	}

	public Likes create(Likes like) throws SQLException {
		String insertLikes = "INSERT INTO Likes(UserName,Tconst,LikeTime) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLikes, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, like.getUserName());
			insertStmt.setString(2, like.getTConst());
			insertStmt.setTimestamp(3, like.getLikeTime());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int likeId = -1;
			if(resultKey.next()) {
				likeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			like.setLikeId(likeId);
			return like;
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

	public Likes delete(Likes like) throws SQLException {
		String deleteLikes = "DELETE FROM Likes WHERE LikeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLikes);
			deleteStmt.setInt(1, like.getLikeId());
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

	public List<Likes> getLikesByUserName(String userName) throws SQLException {
		List<Likes> likes = new ArrayList<Likes>();
		String selectLikes =
				"SELECT LikeId,UserName,Tconst,LikeTime" +
						" FROM Likes WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikes);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int likeId = results.getInt("LikeId");
				Timestamp likeTime =  results.getTimestamp("LikeTime");
				String tConst = results.getString("Tconst");
				String resultUserName = results.getString("UserName");
				Likes like = new Likes(likeId,  resultUserName, tConst, likeTime);
				likes.add(like);
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
		return likes;
	}
}
