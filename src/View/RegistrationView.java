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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.CompanyHandler;
import Controller.UserHandler;

@SuppressWarnings("serial")
public class RegistrationView extends JFrame{
	JPanel top, mid, bot;
	JLabel titleLbl, nameLbl, emailLbl, passwordLbl, addressLbl, phoneNumberLbl, roleLbl;
	JTextField nameTxt, emailTxt, passwordTxt, phoneNumberTxt;
	JTextArea addressTxt;
	JComboBox<String> roleBox;
	JButton registerBtn, Back;
	
	Vector<String> roleList;

	public RegistrationView() {
		setTitle("Register Page");
		
		top = new JPanel(new FlowLayout());
		GridLayout gl = new GridLayout(12, 2);
		mid = new JPanel(gl);
		bot = new JPanel(new FlowLayout());
		
		titleLbl = new JLabel("Register Page");
		nameLbl = new JLabel("Name");
		emailLbl = new JLabel("Email");
		passwordLbl = new JLabel("Password");
		addressLbl = new JLabel("Address");
		phoneNumberLbl = new JLabel("Phone Number");
		roleLbl = new JLabel("Role");
		
		nameTxt = new JTextField();
		emailTxt = new JTextField();
		passwordTxt = new JPasswordField();
		phoneNumberTxt = new JTextField();
		
		addressTxt = new JTextArea();
		
		roleList = new Vector<>();
		roleList.add("Employee");
		roleList.add("Student");
		roleList.add("Staff");
		roleList.add("Company");
		roleBox = new JComboBox<>(roleList);
		
		registerBtn = new JButton("Register");
		Back = new JButton("Back");
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!roleBox.getSelectedItem().toString().equals("Company") && UserHandler.createAccount(nameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), addressTxt.getText(), phoneNumberTxt.getText(), roleBox.getSelectedItem().toString()) == true) {
					JOptionPane.showMessageDialog(null, "Registration Sukses");
				}
				else if(roleBox.getSelectedItem().toString().equals("Company") && CompanyHandler.createAccount(nameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), addressTxt.getText(), phoneNumberTxt.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Registration Sukses");
				}
				else {
					JOptionPane.showMessageDialog(null, "Registration Gagal, Data Invalid");
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
		
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(emailLbl);
		mid.add(emailTxt);
		mid.add(passwordLbl);
		mid.add(passwordTxt);
		mid.add(addressLbl);
		mid.add(addressTxt);
		mid.add(phoneNumberLbl);
		mid.add(phoneNumberTxt);
		mid.add(roleLbl);
		mid.add(roleBox);
		
		mid.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		bot.add(registerBtn);
		bot.add(Back);
		
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
