package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	private Connection con;
	private Statement state;
	private PreparedStatement ps;

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
	
	public void loginUser(String email, String password) {
		try {
			ps = con.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
