package UMovie.servlet;

import UMovie.dal.*;
import UMovie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ratingupdate")
public class RatingUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		List<Ratings> rating = ratingsDao.getRatingsByUserName(userName);
        		if(rating == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("rating", rating);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingsCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer ratingId = req.getIntHeader("ratingid");
        if (ratingId == null ) {
            messages.put("success", "Please enter a valid preferenceId.");
        } else {
        	try {
        		Ratings rating = ratingsDao.getRatingByRatingId(ratingId);
        		if(rating == null) {
        			messages.put("success", "ratingId does not exist. No update to perform.");
        		} else {
        			Double newRatingStar = Double.valueOf("ratingStar");
        			if (newRatingStar == null ) {
        	            messages.put("success", "Please enter a valid PreferedGenre.");
        	        } else {
        	        	rating = ratingsDao.updateRating(rating, newRatingStar);
        	        	messages.put("success", "Successfully updated " + ratingId);
        	        }
        		}
        		req.setAttribute("rating", rating);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingsCRUD.jsp").forward(req, resp);
    }
}
