package Controller;

import java.util.regex.Pattern;

import Model.Company;
import View.CompanyMenu;

public class CompanyHandler {

	private static final String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
            "[a-zA-Z0-9_+&*-]+)*@" + 
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
            "A-Z]{2,7}$";
	
	static Company company = new Company();
	
	public CompanyHandler() {
		
	}
	
	public static boolean createAccount(String name, String email, String password, String address, String phoneNumber) {
		if(name == null || checkEmail(email) == false ||  password == null ||  address == null ||  phoneNumber == null) {
			return false;
		}
		return company.insert(name, email, password, address, phoneNumber);
	}
	
	public static boolean checkEmail(String email) {//validasi email yang dimasukan memiliki format yang benar dan unik.
		if(email == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(emailFormat);
		
		if(Company.checkEmail(email)) {
			return pattern.matcher(email).matches();
		}
		return false;
	}

	public static Company getOne(String email, String password) {
		return company.getOne(email, password);
	}
	
	public static Company getCompany(int companyID) {
		for (Company x : company.getAll()) {
			if(x.companyID == companyID) {
				return x;
			}
		}
		return null;
	}
	public static void viewCompanyMenu(int companyID) {//rute membuka CompanyMenu.
		new CompanyMenu(companyID);
	}
}
