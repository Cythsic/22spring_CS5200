package UMovie.model;


/**
 * Represent a single row in our MySql table {@code Subscriptions}
 *
 */
public class Subscriptions {
	private int subscriptionId;
	private String userName;
	private String nConst;

	public Subscriptions(int subscriptionId, String userName, String nConst) {
		this.subscriptionId = subscriptionId;
		this.userName = userName;
		this.nConst = nConst;
	}

	public Subscriptions(String userName, String nConst) {
		this.userName = userName;
		this.nConst = nConst;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNConst() {
		return nConst;
	}

	public void setnConst(String nConst) {
		this.nConst = nConst;
	}
}