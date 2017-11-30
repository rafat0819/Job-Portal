package Base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class bs_User {
	/* Class Members Declarations */
	private int _UserID;
	private String _Username;
	private String _Password;
	private boolean _Status;
	private String _QnA;
	
	
	/* Default Constructor (If not necessary remove it) */
	
	public bs_User(){
		this(-1, null, null, false, null);
	}
	
	/* Parameterized Constructor (Necessary) */
	public bs_User(int USERID, String USERNAME, String PASSWORD, boolean STATUS, String QNA){
		this.set_UserID(USERID);
		this.set_Username(USERNAME);
		this.set_Password(PASSWORD);
		this.set_Status(STATUS);
		this.set_QnA(QNA);
	}
	
	public bs_User(String USERNAME, String PASSWORD, boolean STATUS, String QNA){
		this.set_Username(USERNAME);
		this.set_Password(PASSWORD);
		this.set_Status(STATUS);
		this.set_QnA(QNA);
	}
	
	/* Get and Set Methods */
	/**
	 * @return the _UserID
	 */
	public int get_UserID() {
		return _UserID;
	}
	/**
	 * @param _UserID the _UserID to set
	 */
	public void set_UserID(int _UserID) {
		this._UserID = _UserID;
	}
	/**
	 * @return the _Username
	 */
	public String get_Username() {
		return _Username;
	}
	/**
	 * @param _Username the _Username to set
	 */
	public void set_Username(String _Username) {
		this._Username = _Username;
	}
	/**
	 * @return the _Password
	 */
	public String get_Password() {
		return _Password;
	}
	/**
	 * @param _Password the _Password to set
	 */
	public void set_Password(String _Password) {
		this._Password = _Password;
	}
	
	/**
	 * @return the _Status
	 */
	public boolean get_Status() {
		return _Status;
	}

	/**
	 * @param _Status the _Status to set
	 */
	public void set_Status(boolean _Status) {
		this._Status = _Status;
	}
	
	/**
	 * @return the _QnA
	 */
	public String get_QnA() {
		return _QnA;
	}

	/**
	 * @param _QnA the _QnA to set
	 */
	public void set_QnA(String _QnA) {
		this._QnA = _QnA;
	}

	/**
	 * Other Methods (What a User can do) =>
	 */
	
	public abstract String LogMeIn(String MessageOnSuccess, String MessageOnFail);
	
	public boolean LogMeOut(int LoggedUserID){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement Statement = null;
    	ResultSet rs = null;
    	boolean result = false;
		try{
			
			String usrSQL = "UPDATE `jp_users` SET `usr_status` = ? WHERE `usr_id` = ?";
			
			Statement = mysqlConnect.connect().prepareStatement(usrSQL);
			
			Statement.setBoolean(1, false);
			Statement.setInt(2, LoggedUserID);
			
			Statement.executeUpdate();
			result = true;
			
			return result;
		}catch(SQLException ERROR){
			result = false;
			ERROR.printStackTrace();	
		}finally {
			if(rs != null){
				try 
				{
					rs.close();
				} 
				catch (SQLException sqlEx) 
				{
					rs = null;
				}
			}
			if(Statement != null){
				try 
				{
					Statement.close();
				} 
				catch (SQLException sqlEx) 
				{
					Statement = null;
				}
			}		
			mysqlConnect.disconnect();
		}
		return result;
	}
	
	public abstract boolean RegMe();
	
	public boolean CheckExistingUsername(String USERNAME){
		boolean result = false;
		bs_DBConnect mysqlConnect = new bs_DBConnect();
    	PreparedStatement checkStatement = null;
		ResultSet rsCount = null;
    	try {
    		String checkSQL = "SELECT COUNT(*) AS rowcount FROM `jp_users` WHERE `usr_username` = ?";
			
			checkStatement = mysqlConnect.connect().prepareStatement(checkSQL);
			checkStatement.setString(1, USERNAME.toString());
			
			rsCount = checkStatement.executeQuery();
			
			rsCount.next();
			int count = rsCount.getInt("rowcount");
			if(count > 0){
				result = false;
			}else{
				result = true;
			}
			return result;
		} catch (SQLException ERROR) {
			ERROR.printStackTrace();
		}finally{
			if(rsCount != null){
				try 
				{
					rsCount.close();
				} 
				catch (SQLException sqlEx) 
				{
					rsCount = null;
				}
			}
			if(checkStatement != null){
				try 
				{
					checkStatement.close();
				} 
				catch (SQLException sqlEx) 
				{
					checkStatement = null;
				}
			}		
			mysqlConnect.disconnect();
		}
		return result;
	}
}
