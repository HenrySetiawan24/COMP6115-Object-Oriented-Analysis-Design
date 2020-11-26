package Controller;

import Model.User;
import View.LoginView;

public class UserHandler {
	
	LoginView loginView;
	User user = new User();

	public UserHandler() {
		
	}

	public LoginView viewLogin() {
		return loginView = new LoginView();
	}
	
	public User getOne(String email, String password) {
		return user.getOne(email, password);
	}
	
	public User getUser(int userID) {
		for (User x : user.getAll()) {
			if(x.userID == userID) {
				return x;
			}
		}
		return null;
	}
	
}
