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

@WebServlet("/Subscriptions/subscriptioncreate")
public class SubscriptionCreate extends HttpServlet {

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
        //Just render the JSP.
        req.getRequestDispatcher("/Subscriptions/SubscriptionCreate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            // Create the User.
            String nConst = req.getParameter("nconst");
            try {
                Subscriptions subscription = new Subscriptions(userName, nConst);
                subscription = subscriptionsDao.create(subscription);
                req.setAttribute("subscription", subscription);
                messages.put("success", "Successfully created " + userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/Subscriptions/SubscriptionCreate.jsp").forward(req, resp);
    }
}

