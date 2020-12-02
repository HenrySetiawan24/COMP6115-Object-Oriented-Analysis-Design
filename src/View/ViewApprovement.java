package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ApplicationHandler;
import Controller.ApprovementHandler;
import Controller.CompanyHandler;
import Controller.InternshipHandler;
import Controller.JobHandler;
import Controller.UserHandler;
import Model.Approvement;

public class ViewApprovement extends JFrame{
	JPanel top, mid, bot;
	JTable dataTable;
	JScrollPane scrollPane;
	JButton Back;
	
	Vector<String> Header, Detail;
	Vector<Vector<String>> Data;
	
	public ViewApprovement(int UserID) {
		init(UserID);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void init(int UserID) {
		top = new JPanel();
		mid = new JPanel();
		bot = new JPanel(); 
		
		dataTable = new JTable();
		
		loadData(UserID);
		
		scrollPane = new JScrollPane(dataTable);
		scrollPane.setPreferredSize(new Dimension(850, 300));
		
		Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserHandler.viewUserMenu(UserID);
			}
		});
		
		top.add(scrollPane);
		
		bot.add(Back);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void loadData(int UserID) {
		if(Header==null) {
			Header = new Vector<>();
			Header.add("Job Name");
			Header.add("Job Description");
			if(UserHandler.getRole(UserID).compareTo("Student")!=0) {
				Header.add("Job Salary");
			}
		}
		if(Data==null)Data = new Vector<>();
		else Data.clear();
		
		if(UserHandler.getRole(UserID).compareTo("Employee")==0)
			for(Approvement a : ApprovementHandler.getAll(UserID)) {
				Detail=new Vector<>();
				
				int jobID=ApplicationHandler.GetApplication(a.applicationID).jobID;
				
				Detail.add(JobHandler.getJob(jobID).name+"");
				Detail.add(JobHandler.getJob(jobID).description+"");
				Detail.add(JobHandler.getJob(jobID).salary+"");
				
				Data.add(Detail);
			}
		else if(UserHandler.getRole(UserID).compareTo("Student")==0)
			for(Approvement a : ApprovementHandler.getAll(UserID)) {
				Detail=new Vector<>();
				
				int jobID=ApplicationHandler.GetApplication(a.applicationID).jobID;
				
				Detail.add(InternshipHandler.getJob(jobID).name+"");
				Detail.add(InternshipHandler.getJob(jobID).description+"");
				
				Data.add(Detail);
			}
		else if(UserHandler.getRole(UserID).compareTo("Staff")==0)
			for(Approvement a : ApprovementHandler.getAll()) {
				Detail=new Vector<>();
				
				int jobID=ApplicationHandler.GetApplication(a.applicationID).jobID;
				
				if(JobHandler.getJob(jobID)!=null) {
					Detail.add(JobHandler.getJob(jobID).name+"");
					Detail.add(JobHandler.getJob(jobID).description+"");
					Detail.add(JobHandler.getJob(jobID).salary+"");
				}else if(InternshipHandler.getJob(jobID)!=null) {
					Detail.add(InternshipHandler.getJob(jobID).name+"");
					Detail.add(InternshipHandler.getJob(jobID).description+"");
					Detail.add("-");
				}
								
				Data.add(Detail);
			}
		DefaultTableModel dtm = new DefaultTableModel(Data, Header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dataTable.setModel(dtm);
	}
}
