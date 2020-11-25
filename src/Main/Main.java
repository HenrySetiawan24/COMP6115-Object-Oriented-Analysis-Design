package Main;

import Controller.UserHandler;
import Model.User;

public class Main {
	
	UserHandler userHandler = new UserHandler();
	
	public Main() {
		userHandler.viewLogin();
	}
	public static void main(String[]Args) {
		new Main();
	}
}
