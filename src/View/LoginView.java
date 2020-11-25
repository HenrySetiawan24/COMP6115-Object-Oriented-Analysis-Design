package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame{

	JPanel top, mid, bot;
	JLabel titleLbl, emailLbl, passwordLbl;
	JTextField emailTxt, passwordTxt;
	JButton loginBtn;
	
	public LoginView() {
		setTitle("Login Page");
		
		top = new JPanel(new FlowLayout());
		GridLayout gl = new GridLayout(2, 2);
		mid = new JPanel(gl);
		bot = new JPanel(new FlowLayout());
		
		titleLbl = new JLabel("Login Page");
		emailLbl = new JLabel("Email");
		passwordLbl = new JLabel("Password");
		
		emailTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		loginBtn = new JButton("Login");
//		loginBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				con.loginUser(emailTxt.getText(), passwordTxt.getText());
//				
//				try {
//					if(con.rs.first()) {
//						JOptionPane.showMessageDialog(null, "Login Sukses");
//					}
//					else {
//						JOptionPane.showMessageDialog(null, "Login Gagal");
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
		
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
		setSize(new Dimension(400,900));
		setLocationRelativeTo(null);
	}

}
