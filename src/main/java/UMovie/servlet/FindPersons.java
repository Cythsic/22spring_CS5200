package UMovie.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UMovie.dal.PersonsInfoDao;
import UMovie.model.PersonsInfo;
/**
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 */
@WebServlet("/PersonsInfo/findPersons")
public class FindPersons extends HttpServlet {
	private PersonsInfoDao personsInfoDao;
	
	@Override
	public void init() throws ServletException {
		personsInfoDao = PersonsInfoDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<PersonsInfo> persons = new ArrayList<>();
        
        // Retrieve the parameter.
        String primaryName = req.getParameter("primaryName");
        if (primaryName == null || primaryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	persons = this.personsInfoDao.searchPersonsInfoByName(primaryName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + primaryName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previous primary name", primaryName);
        }
        req.setAttribute("persons", persons);
        
        req.getRequestDispatcher("/PersonsInfo/persons.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<PersonsInfo> persons = new ArrayList<>();
        
        // Retrieve the parameter.
        String primaryName = req.getParameter("primaryName");
        if (primaryName == null || primaryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	persons = this.personsInfoDao.searchPersonsInfoByName(primaryName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + primaryName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previous primary name", primaryName);
        }
        req.setAttribute("persons", persons);
        
        req.getRequestDispatcher("/PersonsInfo/FindPersons.jsp").forward(req, resp);
    }

}
