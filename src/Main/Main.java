package Main;

import Model.User;

public class Main {
	public Main() {
		User user = new User();
		user.handler.viewLogin();
	}
	public static void main(String[]Args) {
		new Main();
	}
}
