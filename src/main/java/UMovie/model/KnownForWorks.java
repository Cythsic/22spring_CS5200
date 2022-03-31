package UMovie.model;

public final class KnownForWorks {
	private int knownForWorksId;
	private String nConst;
	private String tConst;
	// TODO(Yumeng): replace this by a movie object.
	private String movieName;
	
	
	public KnownForWorks(int knownForWorksId, String nConst, String tConst, String movieName) {
		this.knownForWorksId = knownForWorksId;
		this.nConst = nConst;
		this.tConst = tConst;
		this.movieName = movieName;
	}


	public int getKnownForWorksId() {
		return knownForWorksId;
	}


	public void setKnownForWorksId(int knownForWorksId) {
		this.knownForWorksId = knownForWorksId;
	}


	public String getnConst() {
		return nConst;
	}


	public void setnConst(String nConst) {
		this.nConst = nConst;
	}


	public String gettConst() {
		return tConst;
	}


	public void settConst(String tConst) {
		this.tConst = tConst;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	@Override
	public String toString() {
		return "KnownForWorks [knownForWorksId=" + knownForWorksId + ", nConst=" + nConst + ", tConst=" + tConst
				+ ", movieName=" + movieName + "]";
	}
}