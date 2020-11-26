package Main;

import View.EditAdvertisement;
import View.EditInternship;
import View.EditJob;
import Controller.UserHandler;
import View.RegistrationView;

public class Main {
	
	UserHandler userHandler = new UserHandler();
	RegistrationView view;
	
	public Main() {
		new EditAdvertisement();
		view = userHandler.viewRegistration();
	}
	public static void main(String[]Args) {
		new Main();
	}
}
