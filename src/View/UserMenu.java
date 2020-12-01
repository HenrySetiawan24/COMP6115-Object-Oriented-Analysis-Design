package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ApplicationHandler;
import Controller.JobHandler;
import Controller.UserHandler;

@SuppressWarnings("serial")
public class UserMenu extends JFrame{
	JLabel Title;
	JButton ViewJobsBtn, WishListBtn, ApplicationsBtn, LogoutBtn;
	JPanel mainFrame, contentFrame;
	
	public UserMenu(int userID) {
		init();
		mainFrame = new JPanel(new BorderLayout());
		contentFrame = new JPanel(new GridLayout(4, 1, 1, 15));
		
		Title = new JLabel("Welcome "+UserHandler.getUser(userID).name+"! What would you like to do?");

		ViewJobsBtn = new JButton("View Jobs");
		WishListBtn = new JButton("Your Wishlist");
		ApplicationsBtn = new JButton("Your Applications");
		LogoutBtn = new JButton("Logout");
		
		ViewJobsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JobHandler.viewJobs(userID);
			}
		});
		WishListBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "NotYetImplemented");//remove when done
//				WishListHandler.viewWishList(userID);
			}
		});
		ApplicationsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ApplicationHandler.viewApplications(userID);
			}
		});
		LogoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				UserHandler.logOut();
			}
		});
		
		contentFrame.add(ViewJobsBtn);
		contentFrame.add(WishListBtn);
		contentFrame.add(ApplicationsBtn);
		contentFrame.add(LogoutBtn);
		contentFrame.setBorder(new EmptyBorder(200, 300, 200, 300));
		
		mainFrame.add(Title, BorderLayout.NORTH);
		mainFrame.add(contentFrame, BorderLayout.CENTER);
		
		add(mainFrame);
	}
	
	public void init() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(900,600));
		setLocationRelativeTo(null);
	}
}
