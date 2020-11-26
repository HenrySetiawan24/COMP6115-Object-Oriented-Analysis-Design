package Controller;

import Model.Company;

public class CompanyHandler {

	Company company = new Company();
	
	public CompanyHandler() {
		
	}

	public Company getOne(String email, String password) {
		return company.getOne(email, password);
	}
	
	public Company getCompany(int companyID) {
		for (Company x : company.getAll()) {
			if(x.companyID == companyID) {
				return x;
			}
		}
		return null;
	}
}
