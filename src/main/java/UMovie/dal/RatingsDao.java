package UMovie.dal;

import UMovie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RatingsDao {
	protected ConnectionManager connectionManager;

	private static RatingsDao instance = null;
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	protected int ratingId;
	protected Date ratingTime;
	protected Double rating;
	protected Users user;
	protected Movies movie;
	
	public Ratings create(Ratings rating) throws SQLException {
		String insertRating =
				"INSERT INTO Ratings(RatingTime,RatingStar,UserName,Tconst) " + "VALUES(?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertRating,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setTimestamp(1, new Timestamp(rating.getRatingTime().getTime()));
				insertStmt.setDouble(2, rating.getRatingStar());
				insertStmt.setString(3, rating.getUser().getUserName());
				insertStmt.setString(4, rating.getMovie().gettConst());
				insertStmt.executeUpdate();
				resultKey = insertStmt.getGeneratedKeys();
				int ratingId = -1;
				if(resultKey.next()) {
					ratingId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				rating.setRatingId(ratingId);
				return rating;
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
	
	
	
	public List<Ratings> getRatingsByUserName(String userName) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRating = "SELECT RatingId,RatingTime,Rating,UserName,Tconst FROM Ratings WHERE UserName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRating);
				selectStmt.setString(1, userName);
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				MoviesDao moviesDao = MoviesDao.getInstance();
				while(results.next()) {
					int ratingId = results.getInt("RatingId");
					Date ratingTime = new Date(results.getTimestamp("RatingTime").getTime());
					Double ratingStar = results.getDouble("Rating");
					String resultUserName = results.getString("userName");
					String tconst = results.getString("Tconst");
					
					Users user = usersDao.getUserByUserName(resultUserName);
					Movies movie = moviesDao.getMoviesByTconst(tconst);
					Ratings rating = new Ratings(ratingId,ratingTime,ratingStar,user,movie);
					ratings.add(rating);
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
		return ratings;
	}
	
    public Ratings getRatingByRatingId(Integer ratingId) throws SQLException {
        String selectRating =
                "SELECT RatingId,UserName,Tconst " +
                        "FROM Ratings " +
                        "WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRating);
            selectStmt.setInt(1, ratingId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
			MoviesDao moviesDao = MoviesDao.getInstance();
            if(results.next()) {
                Integer resultRatingId = results.getInt("RatingId");
				Date ratingTime = new Date(results.getTimestamp("RatingTime").getTime());
				Double ratingStar = results.getDouble("RatingStar");
                String userName = results.getString("UserName");
                String tconst = results.getString("Tconst");
                Users user = usersDao.getUserByUserName(userName);
				Movies movie = moviesDao.getMoviesByTconst(tconst);
                Ratings rating = new Ratings(resultRatingId,ratingTime,ratingStar,user,movie);
                return rating;
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
	
	public Ratings updateRating(Ratings rating, Double newRatingStar) throws SQLException {
		String updateRating = "UPDATE Ratings SET Rating=? WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRating);
			updateStmt.setDouble(1, rating.getRatingStar());
			updateStmt.setInt(2, rating.getRatingId());
			updateStmt.executeUpdate();
			rating.setRatingStar(newRatingStar);
			return rating;
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

	
	public Ratings delete(Ratings review) throws SQLException {
		String deleteRating = "DELETE FROM Ratings WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRating);
			deleteStmt.setInt(1, review.getRatingId());
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
