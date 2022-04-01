package UMovie.model;

import java.util.Date;

public class Ratings {

	protected int ratingId;
	protected Date ratingTime;
	protected Double ratingStar;
	protected Users user;
	protected Movies movie;
	
	public Ratings(int ratingId, Date ratingTime, Double ratingStar, Users user, Movies movie) {
		this.ratingId = ratingId;
		this.ratingTime = ratingTime;
		this.ratingStar = ratingStar;
		this.user = user;
		this.movie = movie;
	}
	
	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public Date getRatingTime() {
		return ratingTime;
	}

	public void setRatingTime(Date ratingTime) {
		this.ratingTime = ratingTime;
	}

	public Double getRatingStar() {
		return ratingStar;
	}

	public void setRatingStar(Double ratingStar) {
		this.ratingStar = ratingStar;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}
}
