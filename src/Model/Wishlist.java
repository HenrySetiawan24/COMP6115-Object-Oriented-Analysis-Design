package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Wishlist {

	protected static Connect con = new Connect();
	
	public int wishlistID = 0;
	public int userID = 0;
	public int jobID = 0;
	
	public Wishlist(int wishlistID, int userID, int jobID) {
		super();
		this.wishlistID = wishlistID;
		this.userID = userID;
		this.jobID = jobID;
	}
	
	public static Vector<Wishlist> getAll(int userID){
		con.rs = con.execQuery("SELECT * FROM WISHLIST WHERE userID = '" + userID + "'");
		Vector<Wishlist> wishlist = new Vector<>();
		try {
			while(con.rs.next()) {
				Wishlist list = new Wishlist(con.rs.getInt("wishlistID"), con.rs.getInt("userID"), con.rs.getInt("jobID"));
				wishlist.add(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wishlist;
	}
	
	public static boolean insert(int userID, int jobID) {
		return con.execUpdate("INSERT INTO wishlist VALUES('"+userID+"', '"+jobID+"')");
	}
	
	public static boolean delete(int wishlistID) {
		return con.execUpdate("DELETE FROM wishlist WHERE wishlist.wishlistID = '" + wishlistID + "'");
	}

	public static Job find(int jobID) {
		ResultSet data = con.execQuery("SELECT * FROM internship WHERE jobID="+jobID+" AND salary IS NOT NULL");
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
	
}
