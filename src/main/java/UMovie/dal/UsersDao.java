package UMovie.dal;

import UMovie.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    protected ConnectionManager connectionManager;
    private static UsersDao instance = null;
    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }
    public static UsersDao getInstance() {
        if(instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public Users create(Users user) throws SQLException{
        String insertUser =
                "INSERT INTO Users(UserName,FirstName,LastName,Email,Phone) " +
                        "VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);
            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getFirstName());
            insertStmt.setString(3, user.getLastName());
            insertStmt.setString(4, user.getEmail());
            insertStmt.setString(5, user.getPhone());

            insertStmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    /**
     * Get the Users record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Users instance.
     *
     * @param userName target user's username
     * @return User instance contains information of given username
     * @throws SQLException
     */
    public Users getUserByUserName(String userName) throws SQLException {
        String selectUser =
                "SELECT UserName,FirstName,LastName,Email,Phone " +
                        "FROM Users " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            if(results.next()) {
                String resultUserName = results.getString("UserName");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String email = results.getString("Email");
                String phone = results.getString("Phone");
                Users user = new Users(resultUserName, firstName, lastName, email, phone);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }

    /**
     * Update user info based on UserName
     * This runs a UPDATE statement
     *
     * @param user new user's info
     * @return new user
     * @throws SQLException
     */
    public Users update(Users user) throws SQLException {
        String updateUser = "UPDATE Users " +
                "SET FirstName=?, LastName=?, Email=?, Phone=? " +
                "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            updateStmt.setString(1, user.getFirstName());
            updateStmt.setString(2, user.getLastName());
            updateStmt.setString(3, user.getEmail());
            updateStmt.setString(4, user.getPhone());
            updateStmt.setString(5, user.getUserName());
            updateStmt.executeUpdate();
            return this.getUserByUserName(user.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    /**
     * Delete the Users instance.
     * This runs a DELETE statement.
     *
     * @param user User instance which is going to be deleted
     * @return null
     * @throws SQLException
     */
    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUserName());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
