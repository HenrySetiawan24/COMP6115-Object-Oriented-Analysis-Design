package Main;

import Controller.UserHandler;

public class Main {
	
	UserHandler userHandler = new UserHandler();
	
	public Main() {
		userHandler.viewLogin();
	}
	public static void main(String[]Args) {
		new Main();
	}
}
