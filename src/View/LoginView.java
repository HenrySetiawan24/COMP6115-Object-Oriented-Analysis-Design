package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.CompanyHandler;
import Controller.UserHandler;
import Model.Company;
import Model.User;

public class LoginView extends JFrame{

	JPanel top, mid, bot;
	JLabel titleLbl, emailLbl, passwordLbl, roleLbl;
	JTextField emailTxt, passwordTxt;
	JComboBox<String> roleBox;
	JButton loginBtn;
	
	Vector<String> roleList;
	
	UserHandler userHandler = new UserHandler();
	CompanyHandler companyHandler = new CompanyHandler();
	
	public User temp;
	public Company comTemp; 
	
	public LoginView() {
		setTitle("Login Page");
		
		top = new JPanel(new FlowLayout());
		GridLayout gl = new GridLayout(3, 2);
		mid = new JPanel(gl);
		bot = new JPanel(new FlowLayout());
		
		titleLbl = new JLabel("Login Page");
		emailLbl = new JLabel("Email");
		passwordLbl = new JLabel("Password");
		roleLbl = new JLabel("Role");
		
		emailTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		roleList = new Vector<>();
		roleList.add("Employee");
		roleList.add("Student");
		roleList.add("Staff");
		roleBox = new JComboBox<>(roleList);
		
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(userHandler.getOne(emailTxt.getText(), passwordTxt.getText()) != null) {
					temp = userHandler.getOne(emailTxt.getText(), passwordTxt.getText());
					JOptionPane.showMessageDialog(null, "Login Sukses");
					System.out.println(temp.userID); // Return ke HomeView
					System.out.println(temp.name);
					System.out.println(temp.email);
					System.out.println(temp.password);
					System.out.println(temp.address);
					System.out.println(temp.phoneNumber);
					System.out.println(temp.role);
				}
				else if(companyHandler.getOne(emailTxt.getText(), passwordTxt.getText()) != null) {
					comTemp = companyHandler.getOne(emailTxt.getText(), passwordTxt.getText());
					JOptionPane.showMessageDialog(null, "Login Sukses");
					System.out.println(comTemp.companyID); // Return ke HomeView
					System.out.println(comTemp.name);
					System.out.println(comTemp.email);
					System.out.println(comTemp.password);
					System.out.println(comTemp.address);
					System.out.println(comTemp.phoneNumber);
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Gagal");
				}
			}
		});
		
		top.add(titleLbl);
		
		mid.add(emailLbl);
		mid.add(emailTxt);
		mid.add(passwordLbl);
		mid.add(passwordTxt);
		
		mid.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		bot.add(loginBtn);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		init();
	}
	
	public void init() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(900,600));
		setLocationRelativeTo(null);
	}

}
