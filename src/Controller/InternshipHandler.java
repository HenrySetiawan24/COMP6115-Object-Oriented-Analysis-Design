package Controller;

import java.util.Vector;

import Model.Internship;
import View.EditInternship;

public class InternshipHandler {	
	public static Vector<Internship> GetAll(){
		return Internship.getAll();
	}
	
	public static Vector<Internship> GetAll(int companyID){
		Vector<Internship> f = Internship.getAll();
		for(Internship j : f) {
			if(j.companyID!=companyID) 
				f.remove(j);
		}
		return f;
	}
	
	public static boolean delete(int jobID, int companyID) {
		if(CheckInternship(jobID, companyID)){
			Internship.delete(jobID);
			return true;
		}
		return false;
	}
	
	public static boolean update(int jobID, int companyID, String name, String description) {
		if(CheckInternship(jobID, companyID) && Internship.update(jobID, companyID, name, description)) 
			return true;
		return false;
	}
	
	public static void ViewInternshipMenu() {
		new EditInternship();
	}
	
	public static boolean CheckInternship(int jobID, int companyID) {
		Internship v=Internship.find(jobID);
		if(v.companyID==companyID) 
			return true;
		return false;
	}
	
	public static boolean insert(int companyID, String name, String description) {
		if(Internship.insert(companyID, name, description)) 
			return true;
		return false;
	}
	
	public static int getID(int companyID, String name) {
		for(Internship j : GetAll(companyID)) {
			if(j.name==name) 
				return j.jobID;
		}
		return 0;
	}
	
	public static Internship getJob(int JobID) {
		for(Internship j : GetAll()) {
			if(j.jobID==JobID) 
				return j;
		}
		return null;
	}
}
