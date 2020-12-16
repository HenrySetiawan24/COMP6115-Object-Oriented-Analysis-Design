package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Internship {
	protected static Connect connection = new Connect();
	
	public int jobID=0;
	public int companyID=0;
	public String name="";
	public String description="";
	
	public Internship(int jobID, int companyID, String name, String description) {
		this.jobID=jobID;
		this.companyID=companyID;
		this.name=name;
		this.description=description;
	}
	
	public static Vector<Internship> getAll() {//Internship dan Job merupakan 1 tabel di DB, bedanya internship tidak ada salary.
		ResultSet data = connection.execQuery("SELECT * FROM internship WHERE salary IS NULL");
		Vector<Internship> dataset = new Vector<>();
		try {
			while(data.next()) {
				Internship n = new Internship(	data.getInt("jobID"), 
												data.getInt("companyID"), 
												data.getString("name"), 
												data.getString("description"));
				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	public static Vector<Internship> getAll(int companyID) {
		ResultSet data = connection.execQuery("SELECT * FROM internship WHERE salary IS NULL AND companyID='"+companyID+"'");
		Vector<Internship> dataset = new Vector<>();
		try {
			while(data.next()) {
				Internship n = new Internship(	data.getInt("jobID"), 
												data.getInt("companyID"), 
												data.getString("name"), 
												data.getString("description"));
				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	public static Internship find(int jobID) {
		ResultSet data = connection.execQuery("SELECT * FROM internship WHERE jobID="+jobID+" AND salary IS NULL");
		try {
			while(data.next()) {
				Internship n = new Internship(	data.getInt("jobID"), 
											data.getInt("companyID"), 
											data.getString("name"), 
											data.getString("description"));
				return n;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insert(int companyID, String name, String description) {
		return connection.execUpdate("INSERT INTO `internship` "
									+ "(`jobID`, `companyID`, `name`, `description`) VALUES "
									+ "(NULL, '"+companyID+"', '"+name+"', '"+description+"');");
	}
	
	public static boolean delete(int jobID) {
		return connection.execUpdate("DELETE FROM `internship` "
									+ "WHERE `internship`.`jobID` = '"+jobID+"' AND salary IS NULL");
	}
	
	public static boolean update(int jobID, int companyID, String name, String description) {
		return connection.execUpdate("UPDATE `internship` "
									+ "SET `name` = '"+name
									+"', `description` = '"+description
									+"' WHERE `internship`.`jobID` = "+jobID
									+" AND `internship`.`companyID` = "+companyID+";");
	}
}
