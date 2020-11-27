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
import Model.Company;
import Model.User;

@SuppressWarnings("serial")
public class LoginView extends JFrame{
	JPanel top, mid, bot;
	JLabel titleLbl, emailLbl, passwordLbl, roleLbl;
	JTextField emailTxt, passwordTxt;
	JComboBox<String> roleBox;
	JButton loginBtn;
	
	Vector<String> roleList;
	
	public User temp;
	public Company comTemp; 
	
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
				
				if(UserHandler.getOne(emailTxt.getText(), passwordTxt.getText()) != null) {
					temp = UserHandler.getOne(emailTxt.getText(), passwordTxt.getText());
					JOptionPane.showMessageDialog(null, "Login Sukses");
					dispose();
					UserHandler.viewUserMenu(temp.userID);
				}
				else if(CompanyHandler.getOne(emailTxt.getText(), passwordTxt.getText()) != null) {
					comTemp = CompanyHandler.getOne(emailTxt.getText(), passwordTxt.getText());
					JOptionPane.showMessageDialog(null, "Login Sukses");
					dispose();
					CompanyHandler.viewCompanyMenu(comTemp.companyID);
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
	}
}
