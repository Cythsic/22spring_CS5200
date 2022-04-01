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


@WebServlet("/preferencedelete")
public class PreferenceDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Preference");        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate preferenceId
        int preferenceId = req.getIntHeader("preferenceid");
        if (preferenceId == 0) {
            messages.put("title", "Invalid PreferenceId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the preference
        	Preferences preference = new Preferences(preferenceId, null, null);
	        try {
	        	preference = preferencesDao.delete(preference);
	        	// Update the message.
		        if (preference == null) {
		            messages.put("title", "Successfully deleted " + preferenceId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + preferenceId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
    }
}
