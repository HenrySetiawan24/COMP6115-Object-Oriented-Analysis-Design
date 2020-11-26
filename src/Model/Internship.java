package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Internship {
	private static Connect connection = new Connect();
	private static Vector<Internship> dataset = null;
	
	public int jobID=0;
	public int companyID=0;
	public String name="";
	public String description="";
	public int salary=0;
	
	public Internship(int jobID, int companyID, String name, String description, int salary) {
		this.jobID=jobID;
		this.companyID=companyID;
		this.name=name;
		this.description=description;
		this.salary=salary;
	}
	
	public static Vector<Internship> GetAll() {
		ResultSet data = connection.execQuery("SELECT * FROM internship");
		initDataset();
		try {
			while(data.next()) {
				Internship n = new Internship(	data.getInt("jobID"), 
												data.getInt("companyID"), 
												data.getString("name"), 
												data.getString("description"), 
												data.getInt("salary"));
				dataset.add(n);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataset;
		
	}
	
	private static void initDataset() {
		if(dataset==null)dataset=new Vector<>();
		else {
			dataset.clear();
		}
	}
}
