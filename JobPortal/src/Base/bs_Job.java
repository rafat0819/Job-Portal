package Base;


import java.sql.Date;

public class bs_Job {
	private int _JobID;
	private String _JobTitle;
	private String _JobDetails;
	private Date _IssueDate;
	private Date _DeadlineDate;
	private boolean _JobStatus;
	private int _Vacancy;
	private int _MinExp;
	private int _JobPostedBy;
	private int _catID;
	
	public bs_Job(){
		this(-1, null, null, null, null, false, -1, -1, -1, -1);
	}
	
	public bs_Job(int JOBID, String JOBTITLE, String JOBDETAILS, Date ISSUEDATE, Date DEADLINEDATE, boolean JOBSTATUS, int VACANCY,int MINEXP, int JOBPOSTEDBY, int CATID){
		this.set_JobID(JOBID);
		this.set_JobTitle(JOBTITLE);
		this.set_JobDetails(JOBDETAILS);
		this.set_IssueDate(ISSUEDATE);
		this.set_DeadlineDate(DEADLINEDATE);
		this.set_JobStatus(JOBSTATUS);
		this.set_Vacancy(VACANCY);
		this.set_MinExp(MINEXP);
		this.set_JobPostedBy(JOBPOSTEDBY);
		this.set_catID(CATID);
	}
	
	public bs_Job(String JOBTITLE, String JOBDETAILS, Date ISSUEDATE, Date DEADLINEDATE, boolean JOBSTATUS, int VACANCY,int MINEXP, int JOBPOSTEDBY, int CATID){
		this.set_JobTitle(JOBTITLE);
		this.set_JobDetails(JOBDETAILS);
		this.set_IssueDate(ISSUEDATE);
		this.set_DeadlineDate(DEADLINEDATE);
		this.set_JobStatus(JOBSTATUS);
		this.set_Vacancy(VACANCY);
		this.set_MinExp(MINEXP);
		this.set_JobPostedBy(JOBPOSTEDBY);
		this.set_catID(CATID);
	}
	
	public bs_Job(int JOBID, String JOBTITLE, String JOBDETAILS, Date ISSUEDATE, Date DEADLINEDATE, boolean JOBSTATUS, int VACANCY,int MINEXP, int CATID){
		this.set_JobID(JOBID);
		this.set_JobTitle(JOBTITLE);
		this.set_JobDetails(JOBDETAILS);
		this.set_IssueDate(ISSUEDATE);
		this.set_DeadlineDate(DEADLINEDATE);
		this.set_JobStatus(JOBSTATUS);
		this.set_Vacancy(VACANCY);
		this.set_MinExp(MINEXP);
		this.set_catID(CATID);
	}
	
	public int get_JobID() {
		return _JobID;
	}
	public void set_JobID(int _JobID) {
		this._JobID = _JobID;
	}
	public String get_JobTitle() {
		return _JobTitle;
	}
	public void set_JobTitle(String _JobTitle) {
		this._JobTitle = _JobTitle;
	}
	public String get_JobDetails() {
		return _JobDetails;
	}
	public void set_JobDetails(String _JobDetails) {
		this._JobDetails = _JobDetails;
	}
	public Date get_IssueDate() {
		return _IssueDate;
	}
	public void set_IssueDate(Date _IssueDate) {
		this._IssueDate = _IssueDate;
	}
	public Date get_DeadlineDate() {
		return _DeadlineDate;
	}
	public void set_DeadlineDate(Date _DeadlineDate) {
		this._DeadlineDate = _DeadlineDate;
	}
	public boolean is_JobStatus() {
		return _JobStatus;
	}
	public void set_JobStatus(boolean _JobStatus) {
		this._JobStatus = _JobStatus;
	}
	public int get_Vacancy() {
		return _Vacancy;
	}
	public void set_Vacancy(int _Vacancy) {
		this._Vacancy = _Vacancy;
	}
	public int get_JobPostedBy() {
		return _JobPostedBy;
	}
	public void set_JobPostedBy(int _JobPostedBy) {
		this._JobPostedBy = _JobPostedBy;
	}

	/**
	 * @return the _MinExp
	 */
	public int get_MinExp() {
		return _MinExp;
	}

	/**
	 * @param _MinExp the _MinExp to set
	 */
	public void set_MinExp(int _MinExp) {
		this._MinExp = _MinExp;
	}

	/**
	 * @return the _catID
	 */
	public int get_catID() {
		return _catID;
	}

	/**
	 * @param _catID the _catID to set
	 */
	public void set_catID(int _catID) {
		this._catID = _catID;
	}
	
	
	
	
}
