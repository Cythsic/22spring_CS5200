package UMovie.servlet;

import UMovie.dal.SubscriptionsDao;
import UMovie.model.Subscriptions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/Subscriptions/subscriptiondelete")
public class SubscriptionDelete extends HttpServlet {

    protected SubscriptionsDao subscriptionsDao;

    @Override
    public void init() throws ServletException {
        subscriptionsDao = SubscriptionsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Like");
        req.getRequestDispatcher("/Subscriptions/SubscriptionDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("disableSubmit", "false");
        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the Like.
            Subscriptions subscription = new Subscriptions(userName,  null);
            try {
                subscription = subscriptionsDao.delete(subscription);
                // Update the message.
                if (subscription == null) {
                    messages.put("success", "Successfully deleted " + userName);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("success", "Failed to delete " + userName);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/Subscriptions/SubscriptionDelete.jsp").forward(req, resp);
    }
}
