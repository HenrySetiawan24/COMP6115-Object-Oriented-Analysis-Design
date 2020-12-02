package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ApplicationHandler;
import Controller.InternshipHandler;
import Controller.JobHandler;
import Controller.UserHandler;
import Controller.WishlistHandler;
import Model.Internship;
import Model.Job;
import Model.Wishlist;

public class ViewWishlist extends JFrame{
	JLabel title;
	JScrollPane scroll;
	JTable dataTable;
	JButton applyBtn, deleteBtn, Back;
	JPanel top, mid, bot, desc;
	JLabel wishlistID, nameLbl, CVDescLbl, TranscriptDescLbl, jobIDLbl;
	JTextField userIDTxt, jobIDTxt, nameTxt, CVDescTxt, TranscriptDescTxt;
	
	Vector<String> header, detail;
	Vector<Vector<String>> Data;
	Internship intern;
	Job job;
	public ViewWishlist(int userID) {
		init(userID);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		
	}

	public void init(int userID) {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3,1));
		bot = new JPanel();
		desc = new JPanel(new GridLayout(4,2));
		
		wishlistID = new JLabel("");
		jobIDLbl = new JLabel("Job Id: ");
		nameLbl = new JLabel("Job Name:");
		CVDescLbl = new JLabel("CV Description");
		TranscriptDescLbl = new JLabel("Transcript Description");
		
		jobIDTxt = new JTextField();
		jobIDTxt.setEditable(false);
		nameTxt = new JTextField();
		nameTxt.setEditable(false);
		CVDescTxt = new JTextField();
		TranscriptDescTxt = new JTextField();
		
		dataTable = new JTable();
		dataTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int row = dataTable.getSelectedRow();
				 wishlistID.setText(dataTable.getValueAt(row, 0).toString());
				 jobIDTxt.setText(dataTable.getValueAt(row, 1).toString());
				 nameTxt.setText(dataTable.getValueAt(row, 2).toString());
			}
		});
		loadData(userID);
		title = new JLabel("View Wishlist");
		
		applyBtn = new JButton("Apply");
		deleteBtn = new JButton("Delete");
		Back = new JButton("Back");
		applyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int jobID, wishID;
				try {
					jobID=Integer.parseInt(jobIDTxt.getText());
					wishID = Integer.parseInt(wishlistID.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select Job!!");
					return;
				}
				
				String Name=nameTxt.getText();
				if(Name.length()<1) {
					JOptionPane.showMessageDialog(null, "Select Job!!");
					return;
				}
				
				String CVDesc=CVDescTxt.getText();
				if(CVDesc.length()<1) {
					JOptionPane.showMessageDialog(null, "CV Description Must be Filled!");
					return;
				}
				
				String TranscriptDesc=TranscriptDescTxt.getText();
				if(TranscriptDesc.length()<1) {
					JOptionPane.showMessageDialog(null, "Transcript Description Must be Filled!");
					return;
				}
				

				if(UserHandler.getUser(userID).role.compareTo("Employee")==0) {
					if(ApplicationHandler.insert(userID, jobID, Name, CVDesc, TranscriptDesc, "Job")) {
						JOptionPane.showMessageDialog(null, "Applied Successfully!");
						WishlistHandler.delete(wishID);
					}else {
						JOptionPane.showMessageDialog(null, "ApplicationFailed");
					}
				}
				else if(UserHandler.getUser(userID).role.compareTo("Student")==0) {
					if(ApplicationHandler.insert(userID, jobID, Name, CVDesc, TranscriptDesc, "Intern")) {
						JOptionPane.showMessageDialog(null, "Applied Successfully!");
						WishlistHandler.delete(wishID);
					}else {
						JOptionPane.showMessageDialog(null, "ApplicationFailed");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Login First");
				}
				loadData(userID);
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int wishID=0;
				try {
					wishID = Integer.parseInt(wishlistID.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Select a wishlist to Delete!");					
					return;
				}
				WishlistHandler.delete(wishID);
				loadData(userID);
			}
		});
		Back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				UserHandler.viewUserMenu(userID);
			}
		});
		scroll = new JScrollPane(dataTable);
		scroll.setPreferredSize(new Dimension(850, 300));
		
		top.add(title);
		
		desc.add(jobIDLbl);
		desc.add(jobIDTxt);
		desc.add(nameLbl);
		desc.add(nameTxt);
		desc.add(CVDescLbl);
		desc.add(CVDescTxt);
		desc.add(TranscriptDescLbl);
		desc.add(TranscriptDescTxt);

		mid.add(scroll);
		mid.add(desc);
		
		bot.add(applyBtn);
		bot.add(deleteBtn);
		bot.add(Back);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
	}
	
	public void loadData(int userID) {
		if(header == null) {
			header = new Vector<>();
			header.add("WishlistID");
			header.add("JobID");
			header.add("Name");
			header.add("Description");
			if(UserHandler.getUser(userID).role.compareTo("Employee")==0)
				header.add("Salary");
		}
		if(Data == null)
			Data = new Vector<>();
		else Data.clear();
		
		for (Wishlist w : WishlistHandler.getAll(userID)) {
			detail = new Vector<>();
			if(UserHandler.getUser(userID).role.compareTo("Employee")==0) {
				job = JobHandler.getJob(w.jobID);
				detail.add(w.wishlistID+"");
				detail.add(w.jobID+"");
				detail.add(job.name+"");
				detail.add(job.description+"");
				detail.add(job.salary+"");	
			}else if(UserHandler.getUser(userID).role.compareTo("Student")==0) {
				intern = InternshipHandler.getJob(w.jobID);
				detail.add(w.wishlistID+"");
				detail.add(w.jobID+"");
				detail.add(intern.name+"");
				detail.add(intern.description+"");
			}
			Data.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(Data, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dataTable.setModel(dtm);
	}

}
