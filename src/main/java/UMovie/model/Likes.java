package UMovie.model;

import java.sql.Timestamp;

/**
 * Represent a single row in our MySql table {@code Likes}
 *
 */
public class Likes {
	private int likeId;
	private String userName;
	private String tConst;
	private Timestamp likeTime;

	public Likes(int likeId, String userName, String tConst, Timestamp likeTime) {
		this.likeId = likeId;
		this.userName = userName;
		this.tConst = tConst;
		this.likeTime = likeTime;
	}

	public Likes(String userName, String tConst, Timestamp likeTime) {
		this.userName = userName;
		this.tConst = tConst;
		this.likeTime = likeTime;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTConst() {
		return tConst;
	}

	public void settConst(String tConst) {
		this.tConst = tConst;
	}

	public Timestamp getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}
}