package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	ResultSet rs;
	ResultSetMetaData rsm;
	Connection con;
	Statement state;
	PreparedStatement ps;

	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// Bikin Koneksinya
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deluxe", "root", "");
			state = con.createStatement();
			System.out.println("Koneksi Berhasil");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Koneksi Gagal");
			e.printStackTrace();
		}
	}
	
	public ResultSet execQuery(String query) {
		
		try {
			rs = state.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public PreparedStatement prepareStatement(String querry) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(querry);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public void execUpdate(String query) {
		try {
			state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void loginUser(String email, String password, String role) {
//		try {
//			ps = con.prepareStatement("SELECT * FROM user WHERE email=? AND password=? AND role=?");
//			ps.setString(1, email);
//			ps.setString(2, password);
//			ps.setString(3, role);
//			rs = ps.executeQuery();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
