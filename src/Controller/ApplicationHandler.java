package Controller;

import java.util.Vector;

//import Model.Advertisement;
import Model.Application;
import Model.Job;
import View.EditAdvertisement;
import View.ViewApplication;
import View.ViewJobs;
//import View.EditAdvertisement;

public class ApplicationHandler {
	public static Vector<Application> GetAll(){
		return Application.getAll();
	}
	
	public static Vector<Application> GetAll(int userID){
		Vector<Application> list=new Vector<Application>();
		for(Application a:Application.getAll()) {
			if(a.userID==userID)
				list.add(a);
		}
		return list;
	}
	public static boolean insert(int userID, int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		if(Application.insert(userID, jobID, name, cvdescription, transcriptdescription, type)) 
			return true;
		return false;
	}
	public static boolean update(int applicationID, int userID, int jobID, String name, String cvdescription, String transcriptdescription, String type) {
		if(Application.update(applicationID, userID, jobID, name, cvdescription, transcriptdescription, type)) 
			return true;
		return false;
	}
	public static boolean delete(int applicationID) {
		if(check(applicationID)&&Application.delete(applicationID)) 
			return true;
		return false;
	}
	private static boolean check(int applicationID) {
		if(Application.find(applicationID).applicationID==applicationID)
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
	public static void viewApplications(int userID) {
		new ViewApplication(userID);
	}
}
