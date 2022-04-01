package UMovie.servlet;

import UMovie.dal.*;
import UMovie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ratingdelete")
public class RatingDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Rating");        
        req.getRequestDispatcher("/RatingsCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate preferenceId
        int ratingId = req.getIntHeader("ratingid");
        if (ratingId == 0) {
            messages.put("title", "Invalid RatingId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the preference
        	Ratings rating = new Ratings(ratingId, null, null,null,null);
	        try {
	        	rating = ratingsDao.delete(rating);
	        	// Update the message.
		        if (rating == null) {
		            messages.put("title", "Successfully deleted " + ratingId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + ratingId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingsCRUD.jsp").forward(req, resp);
    }
}
