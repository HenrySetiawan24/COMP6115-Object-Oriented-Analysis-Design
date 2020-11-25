package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Advertisment {
	protected static Connect connection = new Connect();
	
	public int advertismentID=0;
	public int companyID=0;
	public String title="";
	public String description="";
	public Advertisment(int advertismentID, int companyID, String title, String description) {
		this.advertismentID=advertismentID;
		this.companyID=companyID;
		this.title=title;
		this.description=description;
	}
	public static Vector<Advertisment> getAll() {
		ResultSet data = connection.execQuery("SELECT * FROM `advertisement`");
		Vector<Advertisment> dataset = new Vector<>();
		try {
			while(data.next()) {
				Advertisment n = new Advertisment(	data.getInt("advertismentID"), 
													data.getInt("companyID"), 
													data.getString("tittle"), 
													data.getString("description"));
				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	
	public static Advertisment find(int advertismentID) {
		ResultSet data = connection.execQuery("SELECT * FROM advertisment WHERE advertismentID="+advertismentID+" AND salary IS NULL");
		try {
			while(data.next()) {
				Advertisment n = new Advertisment(	data.getInt("advertismentID"), 
													data.getInt("companyID"), 
													data.getString("tittle"), 
													data.getString("description"));
				return n;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean insert(int companyID, String title, String description) {
		return connection.execUpdate("INSERT INTO `advertisment` "
									+ "(`advertismentID`, `companyID`, `title`, `description`) VALUES "
									+ "(NULL, '"+companyID+"', '"+title+"', '"+description+"');");
	}
	
	public static boolean delete(int advertismentID) {
		return connection.execUpdate("DELETE FROM `advertisment` "
									+ "WHERE `internship`.`advertismentID` = '"+advertismentID+"' AND salary IS NULL");
	}
	
	public static boolean update(int advertismentID, int companyID, String title, String description) {
		return connection.execUpdate("UPDATE `advertisment` "
									+ "SET `title` = '"+title
									+"', `description` = '"+description
									+"' WHERE `internship`.`advertismentID` = "+advertismentID
									+" AND `internship`.`companyID` = "+companyID+";");
	}
}
