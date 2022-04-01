package UMovie.servlet;

import UMovie.dal.LikesDao;
import UMovie.model.Likes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/Likes/likedelete")
public class LikeDelete extends HttpServlet {

    protected LikesDao likesDao;

    @Override
    public void init() throws ServletException {
        likesDao = LikesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Like");
        req.getRequestDispatcher("/Likes/LikeDelete.jsp").forward(req, resp);
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
            Likes like = new Likes(userName, null, null);
            try {
                like = likesDao.delete(like);
                // Update the message.
                if (like == null) {
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

        req.getRequestDispatcher("/Likes/LikeDelete.jsp").forward(req, resp);
    }
}
