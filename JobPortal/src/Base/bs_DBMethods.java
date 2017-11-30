package Base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bs_DBMethods implements bs_DBMethodsInterface{
	public String ExecuteOnDB(String SQL, String MessageOnSuccess, String MessageOnFail){
		bs_DBConnect mysqlConnect = new bs_DBConnect();
		try{		
			PreparedStatement Statement = mysqlConnect.connect().prepareStatement(SQL);
			Statement.execute();
			
			return MessageOnSuccess;
		}catch(SQLException ERROR){
			return (MessageOnFail + ERROR);
		}finally {
			mysqlConnect.disconnect();
		}
	}
	
	
}
