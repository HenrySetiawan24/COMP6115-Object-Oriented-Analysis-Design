package Controller;

import java.util.Vector;

import Model.Advertisement;
import View.EditAdvertisement;

public class AdvertisementHandler {
	public static Vector<Advertisement> GetAll(){
		return Advertisement.getAll();
	}
	
	public static Vector<Advertisement> GetAll(int CompanyID){
		Vector<Advertisement> list=new Vector<Advertisement>();
		for(Advertisement a:Advertisement.getAll()) {
			if(a.companyID==CompanyID)
				list.add(a);
		}
		return list;
	}
	public static boolean insert(int companyID, String title, String description) {
		if(Advertisement.insert(companyID, title, description)) 
			return true;
		return false;
	}
	public static boolean update(int advertisementID, int companyID, String title, String description) {
		if(Advertisement.update(advertisementID, companyID, title, description)) 
			return true;
		return false;
	}
	public static boolean delete(int advertisementID, int companyID) {
		if(check(advertisementID, companyID)&&Advertisement.delete(advertisementID)) 
			return true;
		return false;
	}
	private static boolean check(int advertisementID, int companyID) {
		if(Advertisement.find(advertisementID).companyID==companyID)
			return true;
		return false;
	}
	public static Advertisement search(String tittle) {
		for(Advertisement a : Advertisement.getAll()) {
			if(a.title==tittle)
				return a;
		}
		return null;
	}
	
	public static void viewadvertisementMenu() {
		new EditAdvertisement();
	}
}
