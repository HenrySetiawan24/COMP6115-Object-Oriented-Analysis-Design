package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.UserHandler;

@SuppressWarnings("serial")
public class MainMenu extends JFrame{//Menu yang paling pertama dibuka setelah program dijalankan.
	JLabel Title;
	JButton LoginBtn, RegisterBtn;
	JPanel mainFrame, contentFrame;
	public MainMenu() {
		init();
		mainFrame = new JPanel(new BorderLayout());
		contentFrame = new JPanel(new GridLayout(2, 1, 1, 50));
		
		Title = new JLabel("DeluXe Main Menu");

		LoginBtn = new JButton("Login");
		RegisterBtn = new JButton("Register");
		LoginBtn.addActionListener(new ActionListener() {//Pengguna bisa memilih login atau register
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				UserHandler.viewLogin();
			}
		});
		RegisterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				UserHandler.viewRegistration();
			}
		});
		
		contentFrame.add(LoginBtn);
		contentFrame.add(RegisterBtn);
		contentFrame.setBorder(new EmptyBorder(200, 300, 200, 300));
		
		mainFrame.add(Title, BorderLayout.NORTH);
		mainFrame.add(contentFrame, BorderLayout.CENTER);
		
		add(mainFrame);
	}
	
	public void init() {
		setTitle("MainMenu");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(900,600));
		setLocationRelativeTo(null);
	}
}
