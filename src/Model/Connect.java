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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
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
	
	public boolean execUpdate(String query) {
		int re=0;
		try {
			re=state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(re==0)return false;
		else return true;
	}

}
