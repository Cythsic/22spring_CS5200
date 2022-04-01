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

import UMovie.dal.PreferencesDao;
import UMovie.model.Preferences;


/**
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /PreferencesCRUD
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 */
@WebServlet("/findpreferences")
public class FindPreferences extends HttpServlet {
	
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

        List<Preferences> preferences = new ArrayList<Preferences>();
        
        // Retrieve and validate name.
        // username is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Preferences, and store as a message.
        	try {
        		preferences = preferencesDao.getPreferencesByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousUserName", userName);
        }
        req.setAttribute("preferences", preferences);
        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Preferences> preferences = new ArrayList<Preferences>();
        
        // Retrieve and validate name.
        // userName is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in PreferencesCRUD.jsp).
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		preferences = preferencesDao.getPreferencesByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        }
        req.setAttribute("preferences", preferences);
        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
    }
}
