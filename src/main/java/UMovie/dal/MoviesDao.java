package UMovie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UMovie.model.*;

public class MoviesDao {
  protected ConnectionManager connectionManager;

  private static MoviesDao instance = null;

  protected MoviesDao() {
    connectionManager = new ConnectionManager();
  }
  public static MoviesDao getInstance() {
    if(instance == null) {
      instance = new MoviesDao();
    }
    return instance;
  }

  public List<Movies> searchMoivesByOriginalTitle(String originalName) throws SQLException {
    List<Movies> movies = new ArrayList<>();
    String query =
        "SELECT Tconst, OriginalTitle, IsAdult, StartYear, RuntimeMinutes" +
        "FROM Movies" +
        "WHERE OriginalTitle LIKE '%?%'" +
        "ORDER BY StartYear DESC "+ 
        ";";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(query);
      selectStmt.setString(1, originalName);
      results = selectStmt.executeQuery();

      while(results.next()) {
        String tConst = results.getString("Tconst");
        String originalTitle = results.getString("OriginalTitle");
        String runtimeMinutes = results.getString("RuntimeMinutes");
        int isAdult = results.getInt("IsAdult");
        int startYear = results.getInt("StartYear");
        Movies movie = new Movies(tConst, originalTitle, runtimeMinutes, isAdult, startYear);
        movies.add(movie);
      }
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
    return movies;
  }

  
  
  public Movies getMoviesByTconst(String Tconst) throws SQLException {
	    String query =
	        "SELECT Tconst, OriginalTitle, IsAdult, StartYear, RuntimeMinutes" +
	        "FROM Movies" +
	        "WHERE Tconst LIKE '%?%'" +
	        "ORDER BY StartYear DESC "+ 
	        ";";

	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;

	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(query);
	      selectStmt.setString(1, Tconst);
	      results = selectStmt.executeQuery();

	      while(results.next()) {
	        String tConst = results.getString("Tconst");
	        String originalTitle = results.getString("OriginalTitle");
	        String runtimeMinutes = results.getString("RuntimeMinutes");
	        int isAdult = results.getInt("IsAdult");
	        int startYear = results.getInt("StartYear");
	        Movies movie = new Movies(tConst, originalTitle, runtimeMinutes, isAdult, startYear);
	        return movie;
	      }
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
	    return null;
	  }

}