package UMovie.model;

public class Movies {
  protected String tConst;
  protected String originalTitle;
  protected String runtimeMinutes;
  protected int isAdult;
  protected int startYear;

  public Movies(String tConst, String originalTitle, String runtimeMinutes, int isAdult, int startYear) {
    this.tConst = tConst;
    this.originalTitle = originalTitle;
    this.runtimeMinutes = runtimeMinutes;
    this.isAdult = isAdult;
    this.startYear = startYear;
  }

  public Movies(String tConst) {
    this.tConst = tConst;
  }


  public String gettConst() {
    return tConst;
  }

  public void settConst(String tConst) {
    this.tConst = tConst;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public String getRuntimeMinutes() {
    return runtimeMinutes;
  }

  public void setRuntimeMinutes(String runtimeMinutes) {
    this.runtimeMinutes = runtimeMinutes;
  }

  public int getIsAdult() {
    return isAdult;
  }

  public void setIsAdult(int isAdult) {
    this.isAdult = isAdult;
  }

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(int startYear) {
    this.startYear = startYear;
  }
}