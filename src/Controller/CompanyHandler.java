package Controller;

import java.util.regex.Pattern;

import Model.Company;

public class CompanyHandler {

	private static final String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
            "[a-zA-Z0-9_+&*-]+)*@" + 
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
            "A-Z]{2,7}$";
	
	Company company = new Company();
	
	public CompanyHandler() {
		
	}
	
	public boolean createAccount(String name, String email, String password, String address, String phoneNumber) {
		if(name == null || checkEmail(email) == false ||  password == null ||  address == null ||  phoneNumber == null) {
			return false;
		}
		return company.insert(name, email, password, address, phoneNumber);
	}
	
	public boolean checkEmail(String email) {
		if(email == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(emailFormat);
		
		return pattern.matcher(email).matches();
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
