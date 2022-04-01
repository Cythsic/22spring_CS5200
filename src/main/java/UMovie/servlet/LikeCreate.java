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

@WebServlet("/Likes/likecreate")
public class LikeCreate extends HttpServlet {

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
        //Just render the JSP.
        req.getRequestDispatcher("/Likes/LikeCreate.jsp").forward(req, resp);
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
            String tConst = req.getParameter("tconst");
            try {
                Likes like = new Likes(userName, tConst,new java.sql.Timestamp(new java.util.Date().getTime()));
                like = likesDao.create(like);
                req.setAttribute("like", like);
                messages.put("success", "Successfully created " + userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/Likes/LikenCreate.jsp").forward(req, resp);
    }
}

