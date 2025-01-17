package Model;

import java.sql.SQLException;
import java.util.Vector;

public class Company {//Seperti model user tapi untuk Company, tabel di database juga dipisah.
	
	public int companyID;
	public String name;
	public String password;
	public String email;
	public String address;
	public String phoneNumber;
	
	private static Connect con = new Connect();
	private static Vector<Company> companyList = null;

	public Company() {
		
	}

	public Company(int companyID, String name, String password, String email, String address, String phoneNumber) {
		super();
		this.companyID = companyID;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public boolean insert(String name, String email, String password, String address, String phoneNumber) {
		con.ps = con.prepareStatement("INSERT INTO company (name, email, password, address, phoneNum) VALUES (?, ?, ?, ?, ?)");
		try {
			if(con.ps != null) {
				con.ps.setString(1, name);
				con.ps.setString(2, email);
				con.ps.setString(3, password);
				con.ps.setString(4, address);
				con.ps.setString(5, phoneNumber);
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
	
	public Vector<Company> getAll(){
		con.rs = con.execQuery("SELECT * FROM company");
		initCompanyList();
		try {
			while(con.rs.next()) {
				Company company = new Company(con.rs.getInt("companyID"), con.rs.getString("name"), con.rs.getString("password"), 
				con.rs.getString("address"), con.rs.getString("email"), con.rs.getString("phoneNum"));
				
				companyList.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companyList;
	}
	
	public Company getOne(String email, String password) {
		con.ps = con.prepareStatement("SELECT * FROM company WHERE email=? AND password=?");
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
				this.companyID = con.rs.getInt("companyID");
				this.name = con.rs.getString("name");
				this.password = con.rs.getString("password");
				this.address = con.rs.getString("address");
				this.email = con.rs.getString("email");
				this.phoneNumber = con.rs.getString("phoneNum");
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
		con.ps = con.prepareStatement("SELECT * FROM company WHERE email=?");
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
	
	private static void initCompanyList() {
		if(companyList == null) companyList = new Vector<>();
		else companyList.clear();
	}

}
