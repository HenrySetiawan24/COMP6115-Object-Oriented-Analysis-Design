package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Job extends Internship{
	public long salary=0;
	
	public Job(int jobID, int companyID, String name, String description, long salary) {
		super(jobID, companyID, name, description);
		this.salary=salary;
	}
	public static Vector<Job> getAllJobs(){
		ResultSet data = connection.execQuery("SELECT * FROM internship WHERE salary IS NOT NULL");
		Vector<Job> dataset = new Vector<>();
		try {
			while(data.next()) {
				Job n = new Job(	data.getInt("jobID"), 
									data.getInt("companyID"), 
									data.getString("name"), 
									data.getString("description"),
									data.getLong("salary"));
				dataset.add(n);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}
	public static Job find(int jobID) {
		ResultSet data = connection.execQuery("SELECT * FROM internship WHERE jobID="+jobID+" AND salary IS NOT NULL");
		try {
			while(data.next()) {
				Job n = new Job(	data.getInt("jobID"), 
									data.getInt("companyID"), 
									data.getString("name"), 
									data.getString("description"),
									data.getLong("salary"));
				return n;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insert(int companyID, String name, String description, long salary) {
		return connection.execUpdate("INSERT INTO `internship` "
									+ "(`jobID`, `companyID`, `name`, `description`, `salary`) VALUES "
									+ "(NULL, '"+companyID+"', '"+name+"', '"+description+"', '"+salary+"');");
	}
	
	public static boolean delete(int jobID) {
		return connection.execUpdate("DELETE FROM `internship` "
									+ "WHERE `internship`.`jobID` = '"+jobID+"' AND salary IS NOT NULL");
	}
	
	public static boolean update(int jobID, int companyID, String name, String description, long salary) {
		return connection.execUpdate("UPDATE `internship` "
									+ "SET `name` = '"+name
									+"', `description` = '"+description
									+"', `salary` = "+salary+" "
									+ "WHERE `internship`.`jobID` = "+jobID
									+" AND `internship`.`companyID` = "+companyID+";");
	}
}
