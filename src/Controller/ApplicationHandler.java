package Controller;

import java.util.Vector;
import Model.Application;
import View.EditApplications;
import View.ViewApplication;

public class ApplicationHandler {
	public static Vector<Application> GetAll(){
		return Application.getAll();
	}
	
	public static Vector<Application> GetAll(int userID){
		return Application.getAll(userID);
	}
	
	public static Vector<Application> GetAllCompany(int CompanyID){
		return Application.getAllCompany(CompanyID);
	}
	
	public static Application GetApplication(int ApplicationID){
		for(Application a:Application.getAll()) {
			if(a.applicationID==ApplicationID)
				return a;
		}
		return null;
	}
	
	public static boolean insert(int userID, int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		for(Application a: GetAll(userID)) {
			if(JobHandler.getJob(jobID)==JobHandler.getJob(a.jobID)){
				return false;
			}
		}
		if(Application.insert(userID, jobID, name, cvdescription, transcriptdescription, type)) 
			return true;
		return false;
	}
	public static boolean update(int applicationID, int userID, int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		if(check(applicationID, userID)&&Application.update(applicationID, userID, jobID, name, cvdescription, transcriptdescription, type)) 
			return true;
		return false;
	}
	public static boolean delete(int applicationID, int userID) {
		if(check(applicationID, userID)&&Application.delete(applicationID)) 
			return true;
		return false;
	}
	private static boolean check(int applicationID, int userID) {//memastikan apakah application yang dimasukan dimiliki oleh pengguna yg dimasukan.
		if(Application.find(applicationID).userID==userID)
			return true;
		return false;
	}
	public static Application search(String name) {
		for(Application a : Application.getAll()) {
			if(a.name==name)
				return a;
		}
		return null;
	}
	public static void viewApplications(int userID) {//rute membuka ViewApplications
		new ViewApplication(userID);
	}
	public static void editApplications(int companyID) {//rute membuka EditApplications
		new EditApplications(companyID);
	}
}
