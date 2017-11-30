package Base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bs_UserEmployer extends bs_User {
	/* Class Members Declarations */
	private String _CompnayName;
	private String _CompnayAddress;
	private String _Contact;
	private String _Email;
	private String _CompnayDesc;
	private String _Website;
	
	private final String _UserType = "Employer";
	
	public bs_UserEmployer(){
		this(-1, "", "", false, "", "", "", "", "", "", "");
	}
	
	public bs_UserEmployer(int USERID, String USERNAME, String PASSWORD, boolean STATUS, String QNA, String COMNAME, String COMADDRESS, String CONTACT, String EMAIL, String DESC, String WEBSITE){
		super(USERID, USERNAME, PASSWORD, STATUS, QNA);
		this.set_CompnayName(COMNAME);
		this.set_CompnayAddress(COMADDRESS);
		this.set_Contact(CONTACT);
		this.set_Email(EMAIL);
		this.set_CompnayDesc(DESC);
		this.set_Website(WEBSITE);
	}
	
	public bs_UserEmployer(String USERNAME, String PASSWORD, boolean STATUS, String QNA, String COMNAME, String COMADDRESS, String CONTACT, String EMAIL, String DESC, String WEBSITE){
		super(USERNAME, PASSWORD, STATUS, QNA);
		this.set_CompnayName(COMNAME);
		this.set_CompnayAddress(COMADDRESS);
		this.set_Contact(CONTACT);
		this.set_Email(EMAIL);
		this.set_CompnayDesc(DESC);
		this.set_Website(WEBSITE);
	}
	
	public bs_UserEmployer(String USERNAME, String PASSWORD, boolean STATUS, String QNA, String COMNAME, String COMADDRESS, String CONTACT, String EMAIL, String WEBSITE){
		super(USERNAME, PASSWORD, STATUS, QNA);
		this.set_CompnayName(COMNAME);
		this.set_CompnayAddress(COMADDRESS);
		this.set_Contact(CONTACT);
		this.set_Email(EMAIL);
		this.set_CompnayDesc("");
		this.set_Website(WEBSITE);
	}
	
	public bs_UserEmployer(String COMNAME, String COMADDRESS, String CONTACT, String EMAIL, String DESC, String WEBSITE){
		this.set_CompnayName(COMNAME);
		this.set_CompnayAddress(COMADDRESS);
		this.set_Contact(CONTACT);
		this.set_Email(EMAIL);
		this.set_CompnayDesc(DESC);
		this.set_Website(WEBSITE);
	}
	
	/**
	 * @return the _CompnayName
	 */
	public String get_CompnayName() {
		return _CompnayName;
	}

	/**
	 * @param _CompnayName the _CompnayName to set
	 */
	public void set_CompnayName(String _CompnayName) {
		this._CompnayName = _CompnayName;
	}

	/**
	 * @return the _CompnayAddress
	 */
	public String get_CompnayAddress() {
		return _CompnayAddress;
	}

	/**
	 * @param _CompnayAddress the _CompnayAddress to set
	 */
	public void set_CompnayAddress(String _CompnayAddress) {
		this._CompnayAddress = _CompnayAddress;
	}

	/**
	 * @return the _Contact
	 */
	public String get_Contact() {
		return _Contact;
	}

	/**
	 * @param _Contact the _Contact to set
	 */
	public void set_Contact(String _Contact) {
		this._Contact = _Contact;
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
	 * @return the _CompnayDesc
	 */
	public String get_CompnayDesc() {
		return _CompnayDesc;
	}

	/**
	 * @param _CompnayDesc the _CompnayDesc to set
	 */
	public void set_CompnayDesc(String _CompnayDesc) {
		this._CompnayDesc = _CompnayDesc;
	}

	/**
	 * @return the _Website
	 */
	public String get_Website() {
		return _Website;
	}

	/**
	 * @param _Website the _Website to set
	 */
	public void set_Website(String _Website) {
		this._Website = _Website;
	}

	/**
	 * @return the _UserType
	 */
	public String get_UserType() {
		return _UserType;
	}

	
	@Override
	public boolean RegMe() {
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
			
			
			String applicantSQL = "INSERT INTO `jp_employer_users`(`usr_id`, `company_name`, `company_address`, `company_contact`, `company_email`, `company_desc`, `company_website`)" + 
					"VALUES(LAST_INSERT_ID(), ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement empStatement = mysqlConnect.connect().prepareStatement(applicantSQL);
			
			empStatement.setString(1, this.get_CompnayName());
			empStatement.setString(2, this.get_CompnayAddress());
			empStatement.setString(3, this.get_Contact());
			empStatement.setString(4, this.get_Email());
			empStatement.setString(5, "");
			empStatement.setString(6, this.get_Website());
			
			empStatement.executeUpdate();
			
			empStatement.close();
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
	
	public String LogMeOut(String MessageOnSuccess, String MessageOnFail){
		try{
			
			return MessageOnSuccess;
		}catch(Exception ERROR){
			return (MessageOnFail + ERROR);
		}
	}
	
}
