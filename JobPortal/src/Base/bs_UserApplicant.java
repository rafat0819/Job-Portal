package Base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bs_UserApplicant extends bs_User{
	/* Class Members Declarations */
	private String _FirstName;
	private String _LastName;
	private String _Gender;
	private String _Email;
	private String _CV;
	private String _Mobile;
	private String _AboutMe;
	private String _EduBG;

	private final String _UserType = "Applicant";
	
	/* Default Constructor (If not necessary remove it) */
	public bs_UserApplicant(){
		this(-1, null, null, false, null, null, null, null, null, null, null, null, null);
	}
	
	/* Parameterized Constructor (Necessary) */
	public bs_UserApplicant(int USERID, String USERNAME, String PASSWORD, boolean STATUS, String QNA, String FIRSTNAME, String LASTNAME, String GENDER, String EMAIL, String CV, String MOBILE, String ABOUTME, String EDUBG){
		super(USERID, USERNAME, PASSWORD, STATUS, QNA);
		this.set_FirstName(FIRSTNAME);
		this.set_LastName(LASTNAME);
		this.set_Gender(GENDER);
		this.set_Email(EMAIL);
		this.set_Mobile(MOBILE);
		this.set_CV(CV);
		this.set_AboutMe(ABOUTME);
		this.set_EduBG(EDUBG);
	}
	
	public bs_UserApplicant(String USERNAME, String PASSWORD, boolean STATUS, String QNA, String FIRSTNAME, String LASTNAME, String GENDER, String EMAIL, String CV, String MOBILE, String ABOUTME, String EDUBG){
		super(USERNAME, PASSWORD, STATUS, QNA);
		this.set_FirstName(FIRSTNAME);
		this.set_LastName(LASTNAME);
		this.set_Gender(GENDER);
		this.set_Email(EMAIL);
		this.set_Mobile(MOBILE);
		this.set_CV(CV);
		this.set_AboutMe(ABOUTME);
		this.set_EduBG(EDUBG);
	}
	
	public bs_UserApplicant(String USERNAME, String PASSWORD, boolean STATUS, String QNA, String FIRSTNAME, String LASTNAME, String GENDER, String EMAIL, String MOBILE){
		super(USERNAME, PASSWORD, STATUS, QNA);
		this.set_FirstName(FIRSTNAME);
		this.set_LastName(LASTNAME);
		this.set_Gender(GENDER);
		this.set_Email(EMAIL);
		this.set_Mobile(MOBILE);
	}
	
	public bs_UserApplicant(String FIRSTNAME, String LASTNAME, String GENDER, String EMAIL, String CV, String MOBILE, String ABOUTME, String EDUBG){
		this.set_FirstName(FIRSTNAME);
		this.set_LastName(LASTNAME);
		this.set_Gender(GENDER);
		this.set_Email(EMAIL);
		this.set_Mobile(MOBILE);
		this.set_CV(CV);
		this.set_AboutMe(ABOUTME);
		this.set_EduBG(EDUBG);
	}

	/* Get and Set Methods */
	/**
	 * @return the _FullName
	 */
	public String get_FirstName() {
		return _FirstName;
	}

	/**
	 * @param _FullName the _FullName to set
	 */
	public void set_FirstName(String _FirstName) {
		this._FirstName = _FirstName;
	}
	
	/**
	 * @return the _FullName
	 */
	public String get_LastName() {
		return _LastName;
	}

	/**
	 * @param _FullName the _FullName to set
	 */
	public void set_LastName(String _LastName) {
		this._LastName = _LastName;
	}

	/**
	 * @return the _Gender
	 */
	public String get_Gender() {
		return _Gender;
	}

	/**
	 * @param _Gender the _Gender to set
	 */
	public void set_Gender(String _Gender) {
		this._Gender = _Gender;
	}

	/**
	 * @return the _Email
	 */
	public String get_Email() {
		return _Email;
	}

	/**
	 * @param _Email the _Email to set
	 */
	public void set_Email(String _Email) {
		this._Email = _Email;
	}

	/**
	 * @return the _CV
	 */
	public String get_CV() {
		return _CV;
	}

	/**
	 * @param _CV the _CV to set
	 */
	public void set_CV(String _CV) {
		this._CV = _CV;
	}

	/**
	 * @return the _Mobile
	 */
	public String get_Mobile() {
		return _Mobile;
	}

	/**
	 * @param _Mobile the _Mobile to set
	 */
	public void set_Mobile(String _Mobile) {
		this._Mobile = _Mobile;
	}
	
	public String get_UserType() {
		return _UserType;
	}
	/**
	 * @return the _AboutMe
	 */
	public String get_AboutMe() {
		return _AboutMe;
	}

	/**
	 * @param _AboutMe the _AboutMe to set
	 */
	public void set_AboutMe(String _AboutMe) {
		this._AboutMe = _AboutMe;
	}

	/**
	 * @return the _EduBG
	 */
	public String get_EduBG() {
		return _EduBG;
	}

	/**
	 * @param _EduBG the _EduBG to set
	 */
	public void set_EduBG(String _EduBG) {
		this._EduBG = _EduBG;
	}

	

	/**
	 * Other Methods (What an Applicant can do) =>
	 */
	public boolean RegMe(){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
		boolean result = false;
		try{
			String usrSQL = "INSERT INTO `jp_users`(`usr_username`, `usr_password`, `usr_status`, `usr_qna`)" + 
							"VALUES(?, ?, ?, ?)";
			
			PreparedStatement usrStatement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			usrStatement.setString(1, super.get_Username());
			usrStatement.setString(2, super.get_Password());
			usrStatement.setBoolean(3, super.get_Status());
			usrStatement.setString(4, super.get_QnA());
			
			usrStatement.executeUpdate();
			
			usrStatement.close();
			
			
			String applicantSQL = "INSERT INTO `jp_applicant_users`(`usr_id`, `usr_firstname`, `usr_lastname`, `usr_gender`, `usr_email`, `usr_mobile`, `usr_cv`, `usr_aboutme`, `usr_edubg`)" + 
					"VALUES(LAST_INSERT_ID(), ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement applicantStatement = mysqlConnect.connect().prepareStatement(applicantSQL);
			
			applicantStatement.setString(1, this.get_FirstName());
			applicantStatement.setString(2, this.get_LastName());
			applicantStatement.setString(3, this.get_Gender());
			applicantStatement.setString(4, this.get_Email());
			applicantStatement.setString(5, this.get_Mobile());
			applicantStatement.setString(6, this.get_CV());
			applicantStatement.setString(7, this.get_AboutMe());
			applicantStatement.setString(8, this.get_EduBG());
			
			applicantStatement.executeUpdate();
			
			applicantStatement.close();
			result = true;
			
			return result;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();
		}finally {
			mysqlConnect.disconnect();
		}
		return result;
	}
	
	public String LogMeIn(String MessageOnSuccess, String MessageOnFail){
		try{
			
			return MessageOnSuccess;
		}catch(Exception ERROR){
			return (MessageOnFail + ERROR);
		}
	}

	public String SearchJobs(String MessageOnSuccess, String MessageOnFail){
		try{
			
			return MessageOnSuccess;
		}catch(Exception ERROR){
			return (MessageOnFail + ERROR);
		}
	}
	
	public String ApplyJobs(String MessageOnSuccess, String MessageOnFail){
		try{
			
			return MessageOnSuccess;
		}catch(Exception ERROR){
			return (MessageOnFail + ERROR);
		}
	}
	
	public String PostCV(String MessageOnSuccess, String MessageOnFail){
		try{
			
			return MessageOnSuccess;
		}catch(Exception ERROR){
			return (MessageOnFail + ERROR);
		}
	}

	
	
	
	
	
	
}
