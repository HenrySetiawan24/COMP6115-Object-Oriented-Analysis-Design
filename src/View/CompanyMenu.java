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

import Controller.AdvertisementHandler;
import Controller.ApplicationHandler;
import Controller.CompanyHandler;
import Controller.InternshipHandler;
import Controller.JobHandler;
import Controller.UserHandler;

@SuppressWarnings("serial")
public class CompanyMenu extends JFrame{
	JLabel Title;
	JButton JobsBtn, InternshipBtn, AdvertisementBtm, LogoutBtn, Applications;
	JPanel mainFrame, contentFrame;
	public CompanyMenu(int companyID) {
		init();
		mainFrame = new JPanel(new BorderLayout());
		contentFrame = new JPanel(new GridLayout(5, 1, 1, 10));
		
		Title = new JLabel("Hello "+CompanyHandler.getCompany(companyID).name+" Representative! What business will you attend to today?");

		JobsBtn = new JButton("Manage Jobs");
		InternshipBtn = new JButton("Manage Internships");
		AdvertisementBtm = new JButton("Manage Ads");
		Applications = new JButton("Applications");
		LogoutBtn = new JButton("Logout");
		JobsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				AdvertisementHandler.viewAdvertisementMenu(companyID);
			}
		});
		InternshipBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				InternshipHandler.ViewInternshipMenu(companyID);
			}
		});
		AdvertisementBtm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				JobHandler.ViewJobMenu(companyID);
			}
		});
		Applications.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				ApplicationHandler.editApplications(companyID);
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
		
		contentFrame.add(JobsBtn);
		contentFrame.add(InternshipBtn);
		contentFrame.add(AdvertisementBtm);
		contentFrame.add(Applications);
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
