package UMovie.servlet;

import UMovie.dal.UsersDao;
import UMovie.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Users/userupdate")
public class UserUpdate extends HttpServlet {

    protected UsersDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = UsersDao.getInstance();
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
                Users user = userDao.getUserByUserName(userName);
                if(user == null) {
                    messages.put("success", "UserName does not exist.");
                }
                else {
                	messages.put("success", "Updating information for user: " + userName);
                }
                req.setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/Users/UserUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
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
                Users user = userDao.getUserByUserName(userName);
                if(user == null) {
                    messages.put("success", "UserName does not exist. No update to perform.");
                } else {
                    String newFirstName = req.getParameter("firstname");
                    String newLastName = req.getParameter("lastname");
                    String newEmail = req.getParameter("email");
                    String newPhone = req.getParameter("phone");

                    if((newFirstName == null || newFirstName.trim().isEmpty()) && (newLastName == null || newLastName.trim().isEmpty()) && (newEmail == null || newEmail.trim().isEmpty()) && (newPhone == null || newPhone.trim().isEmpty())) {
                        messages.put("success", "No changes updated! Please enter a valid information.");
                    }
                    else {
                        if (newFirstName != null && !newFirstName.trim().isEmpty()) {
                            user.setFirstName(newFirstName);
                        }


                        if (newLastName != null && !newLastName.trim().isEmpty()) {
                            user.setLastName(newLastName);
                        }

                        if (newEmail != null && !newEmail.trim().isEmpty()) {
                            user.setEmail(newEmail);
                        }

                        if (newPhone != null && !newPhone.trim().isEmpty()) {
                            user.setPhone(newPhone);
                        }
                    }
                    user = userDao.update(user);
                    messages.put("success", "Successfully updated " + userName);
                }
                req.setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/Users/UserUpdate.jsp").forward(req, resp);
    }
}

