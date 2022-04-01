package UMovie.servlet;

import UMovie.dal.*;
import UMovie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ratingcreate")
public class RatingCreate extends HttpServlet {
	
	protected RatingsDao ratingsDao;
	
	@Override
	public void init() throws ServletException {
		ratingsDao = RatingsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/RatingsCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate ratingid.
        int ratingId = req.getIntHeader("ratingid");
        if (ratingId == 0) {
            messages.put("success", "Invalid Rating Id");
        } else {
        	// Create the rating.
        	try {
        	UsersDao usersDao = UsersDao.getInstance();
        	MoviesDao moviesDao = MoviesDao.getInstance();
        	String userName = req.getParameter("username");
        	String tconst = req.getParameter("tconst");
        	double rating = Double.parseDouble(req.getParameter("rating"));
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String stringRatingTime = req.getParameter("ratingtime");
        	Date ratingTime = new Date();
			Users user = usersDao.getUserByUserName(userName);
			Movies movies = moviesDao.getMoviesByTconst(tconst);
	        
			Ratings newRating = new Ratings(ratingId,ratingTime,rating,user,movies);
			newRating = ratingsDao.create(newRating);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingsCRUD.jsp").forward(req, resp);
    }
}