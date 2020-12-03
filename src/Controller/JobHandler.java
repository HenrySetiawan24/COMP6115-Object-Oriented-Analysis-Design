package Controller;

import java.util.Vector;

import Model.Job;
import View.EditJob;
import View.ViewJobs;

public class JobHandler {
	public static Vector<Job> GetAll(){
		return Job.getAllJobs();
	}
	
	public static Vector<Job> GetAll(int companyID){
		return Job.getAllJobs(companyID);
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
	
	public static void ViewJobMenu(int companyID) {
		new EditJob(companyID);
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

	public static void viewJobs(int userID) {
		new ViewJobs(userID);
	}
	
	public static Job getJob(int JobID) {
		return Job.find(JobID);
	}
}
