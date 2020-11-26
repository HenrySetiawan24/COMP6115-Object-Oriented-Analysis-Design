package Controller;

import java.util.regex.Pattern;

import Model.User;
import View.LoginView;
import View.RegistrationView;
import View.UserMenu;

public class UserHandler {
	
	private static final String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
            "[a-zA-Z0-9_+&*-]+)*@" + 
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
            "A-Z]{2,7}$";
	static User user = new User();

	public UserHandler() {
		
	}

	public static boolean createAccount(String name, String email, String password, String address, String phoneNumber, String role) {
		if(name == null || checkEmail(email) == false ||  password == null ||  address == null ||  phoneNumber == null ||  role == null) {
			return false;
		}
		return user.insert(name, email, password, address, phoneNumber, role);
	}
	
	public static boolean checkEmail(String email) {
		if(email == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(emailFormat);
		
		return pattern.matcher(email).matches();
	}
	
	public static void viewRegistration() {
		new RegistrationView();
	}
	
	public static void viewLogin() {
		new LoginView();
	}
	
	public static void viewUserMenu(int userID) {
		new UserMenu(userID);
	}
	
	public static void viewJobs(int userID) {
//		new JobsView(userID);
	}
	
	public static void viewWishList(int userID) {
//		new WishlistView(userID);
	}
	
	public static User getOne(String email, String password) {
		return user.getOne(email, password);
	}
	
	public static User getUser(int userID) {
		for (User x : user.getAll()) {
			if(x.userID == userID) {
				return x;
			}
		}
		return null;
	}
	
}
