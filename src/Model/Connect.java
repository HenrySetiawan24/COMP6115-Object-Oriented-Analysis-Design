package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	Connection con;
	Statement state;
	ResultSet rs;
	ResultSetMetaData rsm;
	PreparedStatement ps;
	
	public Connect() {
		// Load Class
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
	
	public void insertStaff(String nama, int umur, String role) {
		try {
			ps = con.prepareStatement("INSERT INTO staff (nama, umur, role) VALUES (?, ?, ?)");
			ps.setString(1, nama);
			ps.setInt(2, umur);
			ps.setString(3, role);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void execUpdate(String query) {
		try {
			state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loginStaff(String username, String password) {
		try {
			ps = con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
