package Model;

import java.sql.SQLException;
import java.util.Vector;


public class User {
	public int userID;
	public String name;
	public String password;
	public String address;
	public String email;
	public String phoneNumber;
	public String role;

	private static Connect con = new Connect();
	private static Vector<User> userList = null;
	
	public User() {
		
	}

	public User(int userID, String name, String password, String address, String email, String phoneNumber,
			String role) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
	
	public boolean insert(String name, String email, String password, String address, String phoneNumber, String role) {
		con.ps = con.prepareStatement("INSERT INTO user (name, email, password, address, phoneNum, role) VALUES (?, ?, ?, ?, ?, ?)");
		try {
			if(con.ps != null) {
				con.ps.setString(1, name);
				con.ps.setString(2, email);
				con.ps.setString(3, password);
				con.ps.setString(4, address);
				con.ps.setString(5, phoneNumber);
				con.ps.setString(6, role);
				con.ps.execute();
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<User> getAll(){
		con.rs = con.execQuery("SELECT * FROM user");
		initUserList();
		try {
			while(con.rs.next()) {
				User user = new User(con.rs.getInt("userID"), con.rs.getString("name"), con.rs.getString("password"), 
				con.rs.getString("address"), con.rs.getString("email"), con.rs.getString("phoneNum"), con.rs.getString("role"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	public User getOne(String email, String password) {
		con.ps = con.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
		try {
			con.ps.setString(1, email);
			con.ps.setString(2, password);
			con.rs = con.ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(con.rs.first()) {
				this.userID = con.rs.getInt("userID");
				this.name = con.rs.getString("name");
				this.password = con.rs.getString("password");
				this.address = con.rs.getString("address");
				this.email = con.rs.getString("email");
				this.phoneNumber = con.rs.getString("phoneNum");
				this.role = con.rs.getString("role");
				return this;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static boolean checkEmail(String email) {
		con.ps = con.prepareStatement("SELECT * FROM user WHERE email=?");
		try {
			con.ps.setString(1, email);
			con.rs = con.ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(con.rs.first()) {
				if(email == con.rs.getString("email")) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private static void initUserList() {
		if(userList == null) userList = new Vector<>();
		else userList.clear();
	}
}
