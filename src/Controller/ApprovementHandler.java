package Controller;

import java.util.Vector;

import Model.Approvement;
import View.ViewApprovement;

public class ApprovementHandler {
	public static Vector<Approvement> getAll(){
		return Approvement.getAll();
	}
	
	public static Vector<Approvement> getAll(int UserID){
		return Approvement.getAll(UserID);
	}
	
	public static Boolean Insert(int applicationID) {
		return Approvement.insert(applicationID);
	}
	
	public static void viewApprovements(int UserID) {//rute membuka View Approvements
		new ViewApprovement(UserID);
	}
}
