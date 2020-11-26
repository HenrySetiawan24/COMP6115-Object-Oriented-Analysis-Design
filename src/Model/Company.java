package Model;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Company {
	
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
	
	private static void initCompanyList() {
		if(companyList == null) companyList = new Vector<>();
		else companyList.clear();
	}

}
