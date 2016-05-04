package hello;


public class IndeedJob {
	
	private String company;
	private String snippet;
/*	private String jobtitle;
	private String city;
	private String state;
	private String jobkey;
	private Boolean sponsored;
	private String formattedLocationFull;
	private String formattedRelativeTime;
*/
	
	public IndeedJob() {
		super();
	}

/*
	public IndeedJob(String jobtitle, String company, String city, String state,
			String snippet, String jobkey, Boolean sponsored,
			String formattedLocationFull, String formattedRelativeTime) {
		super();
		this.jobtitle = jobtitle;
		this.company = company;
		this.city = city;
		this.state = state;
		this.snippet = snippet;
		this.jobkey = jobkey;
		this.sponsored = sponsored;
		this.formattedLocationFull = formattedLocationFull;
		this.formattedRelativeTime = formattedRelativeTime;
	}
*/

	public IndeedJob(String compName, String snipText) {
		this.company = compName;
		this.snippet = snipText;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	
	
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * @return the snippet
	 */
	public String getSnippet() {
		return snippet;
	}
	
	
	/**
	 * @param snippet the snippet to set
	 */
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	

//	/**
//	 * @return the jobtitle
//	 */
//	public String getJobtitle() {
//		return jobtitle;
//	}
//
//
//	/**
//	 * @param jobtitle the jobtitle to set
//	 */
//	public void setJobtitle(String jobtitle) {
//		this.jobtitle = jobtitle;
//	}
//
//
//
//	/**
//	 * @return the city
//	 */
//	public String getCity() {
//		return city;
//	}
//
//
//	/**
//	 * @param city the city to set
//	 */
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//
//	/**
//	 * @return the state
//	 */
//	public String getState() {
//		return state;
//	}
//
//
//	/**
//	 * @param state the state to set
//	 */
//	public void setState(String state) {
//		this.state = state;
//	}
//
//
//
//	/**
//	 * @return the jobkey
//	 */
//	public String getJobkey() {
//		return jobkey;
//	}
//
//
//	/**
//	 * @param jobkey the jobkey to set
//	 */
//	public void setJobkey(String jobkey) {
//		this.jobkey = jobkey;
//	}
//
//
//	/**
//	 * @return the sponsored
//	 */
//	public Boolean getSponsored() {
//		return sponsored;
//	}
//
//
//	/**
//	 * @param sponsored the sponsored to set
//	 */
//	public void setSponsored(Boolean sponsored) {
//		this.sponsored = sponsored;
//	}
//
//
//	/**
//	 * @return the formattedLocationFull
//	 */
//	public String getFormattedLocationFull() {
//		return formattedLocationFull;
//	}
//
//
//	/**
//	 * @param formattedLocationFull the formattedLocationFull to set
//	 */
//	public void setFormattedLocationFull(String formattedLocationFull) {
//		this.formattedLocationFull = formattedLocationFull;
//	}
//
//
//	/**
//	 * @return the formattedRelativeTime
//	 */
//	public String getFormattedRelativeTime() {
//		return formattedRelativeTime;
//	}
//
//
//	/**
//	 * @param formattedRelativeTime the formattedRelativeTime to set
//	 */
//	public void setFormattedRelativeTime(String formattedRelativeTime) {
//		this.formattedRelativeTime = formattedRelativeTime;
//	}

	@Override
	public String toString() {
		return "Company: " + company + ", Snippet:" + snippet;
	}
	
}
