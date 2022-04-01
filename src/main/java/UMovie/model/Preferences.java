package UMovie.model;

public class Preferences {
	
	protected int preferenceId;
	protected Users user;
	protected String preferedGenre;

	public Preferences(int preferenceId, Users user, String preferedGenre) {
		this.preferenceId = preferenceId;
		this.user = user;
		this.preferedGenre = preferedGenre;
	}
	
	public int getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getPreferedGenre() {
		return preferedGenre;
	}

	public void setPreferedGenre(String preferedGenre) {
		this.preferedGenre = preferedGenre;
	}
}
