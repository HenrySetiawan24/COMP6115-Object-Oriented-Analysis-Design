package Main;

import Controller.UserHandler;
import View.RegistrationView;

public class Main {
	
	UserHandler userHandler = new UserHandler();
	RegistrationView view;
	
	public Main() {
		//userHandler.viewLogin();
		view = userHandler.viewRegistration();
	}
	public static void main(String[]Args) {
		new Main();
	}
}
