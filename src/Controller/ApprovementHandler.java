package Controller;

import java.util.Vector;

import Model.Approvement;
import View.ViewApprovement;

public class ApprovementHandler {
	public static Vector<Approvement> getAll(){
		return Approvement.getAll();
	}
	
	public static Boolean Insert(int applicationID) {
		return Approvement.insert(applicationID);
	}
	
	public static Vector<Approvement> getAll(int UserID){
		Vector<Approvement> list = new Vector<Approvement>();
		for(Approvement a : getAll()) {
			if(ApplicationHandler.GetApplication(a.applicationID).userID==UserID) {
				list.add(a);
			}
		}
		return list;
	}
	
	public static void viewApprovements(int UserID) {
		new ViewApprovement(UserID);
	}
}
