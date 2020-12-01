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
import Controller.ApprovementHandler;
import Controller.JobHandler;
import Controller.UserHandler;
import Controller.WishlistHandler;

@SuppressWarnings("serial")
public class UserMenu extends JFrame{
	JLabel Title;
	JButton ViewJobsBtn, WishListBtn, ApplicationsBtn, LogoutBtn, ApprovedBtn;
	JPanel mainFrame, contentFrame;
	
	public UserMenu(int userID) {
		init();
		mainFrame = new JPanel(new BorderLayout());
		contentFrame = new JPanel(new GridLayout(5, 1, 1, 15));
		
		Title = new JLabel("Welcome "+UserHandler.getUser(userID).name+"! What would you like to do?");

		ViewJobsBtn = new JButton("View Jobs");
		WishListBtn = new JButton("Your Wishlist");
		ApplicationsBtn = new JButton("Your Applications");
		ApprovedBtn = new JButton("Your Approved Jobs");
		LogoutBtn = new JButton("Logout");
		ViewJobsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				JOptionPane.showMessageDialog(null, "NotYetImplemented");
//				UserHandler.viewJobs(userID);
				JobHandler.viewJobs(userID);
			}
		});
		WishListBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "NotYetImplemented");//remove when done
				WishlistHandler.ViewWishlist(userID);
			}
		});
		ApplicationsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ApplicationHandler.viewApplications(userID);
			}
		});
		ApprovedBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ApprovementHandler.viewApprovements(userID);
			}
		});
		LogoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				UserHandler.viewWishList(userID);
				UserHandler.logOut();
			}
		});
		if(UserHandler.getUser(userID).role.compareTo("Staff")==0) {
			ApprovedBtn.setText("Approvements");
			contentFrame.add(ApprovedBtn);
		}else {
			contentFrame.add(ViewJobsBtn);
			contentFrame.add(WishListBtn);
			contentFrame.add(ApplicationsBtn);
			contentFrame.add(LogoutBtn);
			contentFrame.add(ApprovedBtn);
		}
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
