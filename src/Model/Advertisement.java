package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Advertisement {
	protected static Connect connection = new Connect();
	
	public int advertisementID=0;
	public int companyID=0;
	public String title="";
	public String description="";
	public Advertisement(int advertisementID, int companyID, String title, String description) {
		this.advertisementID=advertisementID;
		this.companyID=companyID;
		this.title=title;
		this.description=description;
	}
	public static Vector<Advertisement> getAll() {
		ResultSet data = connection.execQuery("SELECT * FROM `advertisement`");
		Vector<Advertisement> dataset = new Vector<>();
		try {
			while(data.next()) {
				Advertisement n = new Advertisement(	data.getInt("advertisementID"), 
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
	
	public static Advertisement find(int advertisementID) {
		ResultSet data = connection.execQuery("SELECT * FROM advertisement WHERE advertisementID="+advertisementID);
		try {
			while(data.next()) {
				Advertisement n = new Advertisement(	data.getInt("advertisementID"), 
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
		return connection.execUpdate("INSERT INTO `advertisement` "
									+ "(`advertisementID`, `companyID`, `tittle`, `description`) VALUES "
									+ "(NULL, '"+companyID+"', '"+title+"', '"+description+"');");
	}
	
	public static boolean delete(int advertisementID) {
		return connection.execUpdate("DELETE FROM `advertisement` "
									+ "WHERE `advertisementID` = "+advertisementID);
	}
	
	public static boolean update(int advertisementID, int companyID, String title, String description) {
		return connection.execUpdate("UPDATE `advertisement` "
									+ "SET `tittle` = '"+title
									+"', `description` = '"+description
									+"' WHERE `advertisementID` = "+advertisementID
									+" AND `companyID` = "+companyID+";");
	}
}
