package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Application {

	protected static Connect connection = new Connect();
	
	public int applicationID=0;
	public int userID=0;
	public int jobID = 0;
	public String name="";
	public String cvdescription="";
	public String transcriptdescription="";
	public String type="";

	public Application(int applicationID, int userID,int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		this.applicationID=applicationID;
		this.userID=userID;
		this.jobID=jobID;
		this.name=name;
		this.cvdescription=cvdescription;
		this.transcriptdescription=transcriptdescription;
		this.type=type;

	}
	public static Vector<Application> getAll() {
		ResultSet data = connection.execQuery("SELECT * FROM `application`");
		Vector<Application> dataset = new Vector<>();
		try {
			while(data.next()) {
				Application n = new Application(	data.getInt("applicationID"), 
													data.getInt("userID"), 
													data.getInt("jobID"), 
													data.getString("name"), 
													data.getString("cvdescription"),
													data.getString("transcriptdescription"),
													data.getString("type"));

				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	
	public static Vector<Application> getAll(int userID) {
		ResultSet data = connection.execQuery("SELECT * FROM `application` WHERE userID = '"+userID+"'");
		Vector<Application> dataset = new Vector<>();
		try {
			while(data.next()) {
				Application n = new Application(	data.getInt("applicationID"), 
													data.getInt("userID"), 
													data.getInt("jobID"), 
													data.getString("name"), 
													data.getString("cvdescription"),
													data.getString("transcriptdescription"),
													data.getString("type"));

				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	public static Vector<Application> getAllCompany(int CompanyID) {
		ResultSet data = connection.execQuery("SELECT * FROM `application` WHERE jobID in (SELECT internship.jobID FROM `internship` WHERE companyID = '"+CompanyID+"')");
		Vector<Application> dataset = new Vector<>();
		try {
			while(data.next()) {
				Application n = new Application(	data.getInt("applicationID"), 
													data.getInt("userID"), 
													data.getInt("jobID"), 
													data.getString("name"), 
													data.getString("cvdescription"),
													data.getString("transcriptdescription"),
													data.getString("type"));

				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	public static Application find(int applicationID) {
		ResultSet data = connection.execQuery("SELECT * FROM application WHERE applicationID="+applicationID);
		try {
			while(data.next()) {
				Application n = new Application(	
						data.getInt("applicationID"), 
						data.getInt("userID"), 
						data.getInt("jobID"), 
						data.getString("name"), 
						data.getString("cvdescription"),
						data.getString("transcriptdescription"),
						data.getString("type"));
				return n;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insert(int userID, int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		return connection.execUpdate("INSERT INTO `application` "
									+ "(`userID`,`jobID`, `name`, `cvdescription`, `transcriptdescription`, `type`) VALUES "
									+ "('"+userID+"', '"+jobID+"', '"+name+"', '"+cvdescription+"', '"+transcriptdescription+"', '"+type+"');");
	}
	
	public static boolean delete(int applicationID) {
		return connection.execUpdate("DELETE FROM `application` "
									+ "WHERE `applicationID` = "+applicationID);
	}
	
	public static boolean update(int applicationID, int userID, int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		return connection.execUpdate("UPDATE `application` "
									+ "SET `userID` = '"+userID
									+"', `jobID` = '"+jobID
									+"', `name` = '"+name
									+"', `cvdescription` = '"+cvdescription
									+"', `transcriptdescription` = '"+transcriptdescription
									+"', `type` = '"+type
									+"' WHERE `applicationID` = "+applicationID
//									+" AND `companyID` = "+companyID+";"
									);
	}

}
