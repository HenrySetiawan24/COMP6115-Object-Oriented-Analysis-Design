package Controller;

import java.util.Vector;

import Model.Advertisment;
import View.EditAdvertisment;

public class AdvertismentController {
	public static Vector<Advertisment> getAll(){
		return Advertisment.getAll();
	}
	
	public static boolean insert(int companyID, String title, String description) {
		if(Advertisment.insert(companyID, title, description)) 
			return true;
		return false;
	}
	
	public static Advertisment search(String tittle) {
		for(Advertisment a : Advertisment.getAll()) {
			if(a.title==tittle)
				return a;
		}
		return null;
	}
	
	public static void viewAdvertismentMenu() {
		new EditAdvertisment();
	}
}
