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


@WebServlet("/preferencecreate")
public class PreferenceCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int preferenceId = req.getIntHeader("preferenceid");
        if (preferenceId == 0) {
            messages.put("success", "Invalid preference Id");
        } else {
        	// Create the Preference.
        	try {UsersDao usersDao = UsersDao.getInstance();

        	String userName = req.getParameter("username");
        	String preferedGenre = req.getParameter("preferedgenre");
			Users user = usersDao.getUserByUserName(userName);
	        
	        	Preferences preference = new Preferences(preferenceId, user, preferedGenre);
	        	preference = preferencesDao.create(preference);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PreferencesCRUD.jsp").forward(req, resp);
    }
}
