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


@WebServlet("/preferenceupdate")
public class PreferenceUpdate extends HttpServlet {
	
	protected PreferencesDao preferencesDao;
	
	@Override
	public void init() throws ServletException {
		preferencesDao = PreferencesDao.getInstance();
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
        		List<Preferences> preference = preferencesDao.getPreferencesByUserName(userName);
        		if(preference == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("preference", preference);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        Integer preferenceId = req.getIntHeader("preferenceid");
        if (preferenceId == null ) {
            messages.put("success", "Please enter a valid preferenceId.");
        } else {
        	try {
        		Preferences preference = preferencesDao.getPreferenceByPreferenceId(preferenceId);
        		if(preference == null) {
        			messages.put("success", "preferenceId does not exist. No update to perform.");
        		} else {
        			String newPreferedGenre = req.getParameter("preferedgenre");
        			if (newPreferedGenre == null || newPreferedGenre.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid PreferedGenre.");
        	        } else {
        	        	preference = preferencesDao.updatePreferedGenre(preference, newPreferedGenre);
        	        	messages.put("success", "Successfully updated " + preferenceId);
        	        }
        		}
        		req.setAttribute("preference", preference);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
    }
}
