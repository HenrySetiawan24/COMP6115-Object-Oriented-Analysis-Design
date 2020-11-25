package Controller;

import java.util.Vector;

import Model.Job;
import View.EditJob;

public class JobHandler {
	public static Vector<Job> GetAll(){
		return Job.getAllJobs();
	}
	
	public static Vector<Job> GetAll(int companyID){
		Vector<Job> f = Job.getAllJobs();
		for(Job j : f) {
			if(j.companyID!=companyID) 
				f.remove(j);
		}
		return f;
	}
	
	public static boolean delete(int jobID, int companyID) {
		if(CheckJob(jobID, companyID)){
			Job.delete(jobID);
			return true;
		}
		return false;
	}
	
	public static boolean update(int jobID, int companyID, String name, String description, long salary) {
		if(Job.update(jobID, companyID, name, description, salary)) 
			return true;
		return false;
	}
	
	public static void ViewJobMenu() {
		new EditJob();
	}
	
	public static boolean CheckJob(int jobID, int companyID) {
		Job v=Job.find(jobID);
		if(v.companyID==companyID) 
			return true;
		return false;
	}
	
	public static boolean insert(int companyID, String name, String description, long salary) {
		if(Job.insert(companyID, name, description, salary)) 
			return true;
		return false;
	}
	
	public static int getID(int companyID, String name) {
		for(Job j : GetAll(companyID)) {
			if(j.name==name) 
				return j.jobID;
		}
		return 0;
	}
	
	public static Job getJob(int JobID) {
		for(Job j : GetAll()) {
			if(j.jobID==JobID) 
				return j;
		}
		return null;
	}
}
