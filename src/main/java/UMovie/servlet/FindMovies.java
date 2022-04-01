package UMovie.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UMovie.dal.*;
import UMovie.model.*;
/**
 *
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 *
 */
@WebServlet("/findMovies")
public class FindMovies extends HttpServlet {
  private MoviesDao MoviesDao;

  @Override
  public void init() throws ServletException {
    MoviesDao = MoviesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Movies> movies = new ArrayList<>();

    // Retrieve the parameter.
    String originalTitle = req.getParameter("orginalTitle");
    if (originalTitle == null || originalTitle.trim().isEmpty()) {
      messages.put("success", "Please enter a valid title.");
    } else {
      // Retrieve BlogUsers, and store as a message.
      try {
        movies = MoviesDao.searchMoivesByOriginalTitle(originalTitle);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + originalTitle);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previous title", originalTitle);
    }
    req.setAttribute("movies", movies);

    req.getRequestDispatcher("/movies.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<Movies> movies = new ArrayList<>();
    // Retrieve the parameter.
    String originalTitle = req.getParameter("orginalTitle");
    if (originalTitle == null || originalTitle.trim().isEmpty()) {
      messages.put("success", "Please enter a title.");
    } else {
      // Retrieve BlogUsers, and store as a message.
      try {
        movies = MoviesDao.searchMoivesByOriginalTitle(originalTitle);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + originalTitle);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previous original title", originalTitle);
    }
    req.setAttribute("movies", movies);

    req.getRequestDispatcher("/movies.jsp").forward(req, resp);
  }

}
