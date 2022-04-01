package UMovie.dal;

import UMovie.model.Subscriptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsDao {
	protected ConnectionManager connectionManager;

	private static SubscriptionsDao instance = null;
	protected SubscriptionsDao() {
		connectionManager = new ConnectionManager();
	}
	public static SubscriptionsDao getInstance() {
		if(instance == null) {
			instance = new SubscriptionsDao();
		}
		return instance;
	}

	public Subscriptions create(Subscriptions subscription) throws SQLException {
		String insertSubscriptions = "INSERT INTO Likes(UserName,Nconst) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSubscriptions, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, subscription.getUserName());
			insertStmt.setString(2, subscription.getNConst());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int subscriptionId = -1;
			if(resultKey.next()) {
				subscriptionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			subscription.setSubscriptionId(subscriptionId);
			return subscription;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Subscriptions delete(Subscriptions subscription) throws SQLException {
		String deleteSubscription = "DELETE FROM Subscriptions WHERE SubscriptionId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSubscription);
			deleteStmt.setInt(1, subscription.getSubscriptionId());
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

	public List<Subscriptions> getSubscriptionsByUserName(String userName) throws SQLException {
		List<Subscriptions> subscriptions = new ArrayList<Subscriptions>();
		String selectSubscriptions =
				"SELECT SubscriptionId,UserName,Nconst" +
						" FROM Subscriptions WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSubscriptions);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int subscriptionId = results.getInt("SubscriptionId");
				String nConst = results.getString("Nconst");
				String resultUserName = results.getString("UserName");
				Subscriptions subscription = new Subscriptions(subscriptionId,  resultUserName, nConst);
				subscriptions.add(subscription);
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
		return subscriptions;
	}
}
