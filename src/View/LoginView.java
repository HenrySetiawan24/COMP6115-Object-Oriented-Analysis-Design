package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

@SuppressWarnings("serial")
public class LoginView extends JFrame{
	JPanel top, mid, bot;
	JLabel titleLbl, emailLbl, passwordLbl, roleLbl;
	JTextField emailTxt, passwordTxt;
	JComboBox<String> roleBox;
	JButton loginBtn, Back;
	
	Vector<String> roleList;
	
	public LoginView() {
		init();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(900,600));
		setLocationRelativeTo(null);
	}
	
	public void init() {
		setTitle("Login Page");
		
		top = new JPanel(new FlowLayout());
		GridLayout gl = new GridLayout(12, 2);
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
		Back = new JButton("Back");
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(UserHandler.getOne(emailTxt.getText(), passwordTxt.getText()) != null) {
					JOptionPane.showMessageDialog(null, "Login Sukses");
					dispose();
					UserHandler.viewUserMenu(UserHandler.getOne(emailTxt.getText(), passwordTxt.getText()).userID);
				}
				else if(CompanyHandler.getOne(emailTxt.getText(), passwordTxt.getText()) != null) {
					JOptionPane.showMessageDialog(null, "Login Sukses");
					dispose();
					CompanyHandler.viewCompanyMenu(CompanyHandler.getOne(emailTxt.getText(), passwordTxt.getText()).companyID);
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Gagal");
				}
			}
		});
		Back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserHandler.logOut();
				dispose();
			}
		});
		
		top.add(titleLbl);
		
		mid.add(emailLbl);
		mid.add(emailTxt);
		mid.add(passwordLbl);
		mid.add(passwordTxt);
		
		mid.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		bot.add(loginBtn);
		bot.add(Back);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
}
