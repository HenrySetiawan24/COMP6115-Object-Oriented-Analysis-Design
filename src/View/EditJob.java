package View;

import java.util.Vector;

import Model.Internship;

public class EditJob {
	public EditJob() {
		Vector<Internship> interns=Internship.GetAll();
		for(Internship i : interns) {
			System.out.println(" JobID: "+i.jobID+" CompanyID: "+i.companyID+" Name: "+i.name+" Description: "+i.description+"S allary: "+i.salary);
		}
	}
}
