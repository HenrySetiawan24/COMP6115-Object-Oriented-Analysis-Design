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

import Controller.UserHandler;

@SuppressWarnings("serial")
public class UserMenu extends JFrame{
	JLabel Title;
	JButton ViewJobsBtn, WishListBtn;
	JPanel mainFrame, contentFrame;
	
	public UserMenu(int userID) {
		init();
		mainFrame = new JPanel(new BorderLayout());
		contentFrame = new JPanel(new GridLayout(2, 1, 1, 50));
		
		Title = new JLabel("Welcome "+UserHandler.getUser(userID).name+"! What would you like to do?");

		ViewJobsBtn = new JButton("View Jobs");
		WishListBtn = new JButton("Your Wishlist");
		ViewJobsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				JOptionPane.showMessageDialog(null, "NotYetImplemented");
//				UserHandler.viewWishList(userID);
			}
		});
		WishListBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				JOptionPane.showMessageDialog(null, "NotYetImplemented");
//				UserHandler.viewJobs(userID);
			}
		});
		
		contentFrame.add(ViewJobsBtn);
		contentFrame.add(WishListBtn);
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
