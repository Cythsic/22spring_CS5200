package UMovie.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represent a single row in our MySql table {@code PersonsInfo}
 *
 */
public class PersonsInfo {
	private String nConst; // this is the primary key.
	private String primaryName;
	private int birthYear;
	private int deathYear;
	private List<KnownForWorks> knownForWorks;

	public PersonsInfo(String nConst, String primaryName, int birthYear, int deathYear, List<KnownForWorks> knownForWorks) {
		this.nConst = nConst;
		this.primaryName = primaryName;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.knownForWorks = knownForWorks;
	}

	public String getnConst() {
		return nConst;
	}

	public void setnConst(String nConst) {
		this.nConst = nConst;
	}

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}
	

	public List<KnownForWorks> getKnownForWorks() {
		return knownForWorks;
	}
	
	public List<String> getKnownForWorksTitle() {
		return this.knownForWorks.stream().map(k -> k.getMovieName()).collect(Collectors.toList());
	}

	public void setKnownForWorks(List<KnownForWorks> knownForWorks) {
		this.knownForWorks = knownForWorks;
	}

	@Override
	public String toString() {
		return "PersonsInfo [nConst=" + nConst + ", primaryName=" + primaryName + ", birthYear=" + birthYear
				+ ", deathYear=" + deathYear + ", knownForWorks=" + knownForWorks + "]";
	}
}