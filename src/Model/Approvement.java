package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Approvement {
	protected static Connect connection = new Connect();
	
	public int approvementID=0;
	public int applicationID=0;

	public Approvement(int applicationID, int approvementID) {
		this.applicationID=applicationID;
		this.approvementID=approvementID;

	}
	public static Vector<Approvement> getAll() {
		ResultSet data = connection.execQuery("SELECT * FROM `approvement`");
		Vector<Approvement> dataset = new Vector<>();
		try {
			while(data.next()) {
				Approvement n = new Approvement(	data.getInt("applicationID"), 
													data.getInt("approvementID"));

				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	
	public static boolean insert(int applicationID) {
		return connection.execUpdate("INSERT INTO `approvement` "
									+ "(`approvementID`,`applicationID`) VALUES "
									+ "(NULL, '"+applicationID+"');");
	}
	
	public static boolean delete(int approvementID) {
		return connection.execUpdate("DELETE FROM `approvement` "
									+ "WHERE `approvementID` = "+approvementID);
	}
}
