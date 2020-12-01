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
import Controller.ApprovementHandler;
import Controller.CompanyHandler;
import Controller.UserHandler;
import Model.Application;

public class ViewApplication extends JFrame{
	
	JPanel top,mid,bot,left;
	JTable table;
	JTable adtable;
	JScrollPane sp;
	JLabel applicationIDLbl, jobIDLbl, nameLabel, CVLabel, transcriptLbl, applicationIDTxt, jobIDTxt;
	JLabel adidLbl,adnameLbl,addescLbl;
	JTextField nameTxt, CVTxt, TranscriptTxt;
	JButton update, delete, Back, Approve;
	
	Vector<Vector<String>> data;

	Vector<String> detail,header;

	public ViewApplication(int UserID) {
		init(UserID);
		setSize(900,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void init(int UserID) {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3,2));
		bot = new JPanel();
		left = new JPanel();
		
		table = new JTable();
		adtable = new JTable();
		
		sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(850, 300));
		loadData(UserID);	
		
		applicationIDLbl = new JLabel("ID:");
		jobIDLbl = new JLabel("Desc:");
		nameLabel = new JLabel("Name:");
		CVLabel = new JLabel("CV Description:");
		transcriptLbl = new JLabel("Transcript:");
		
		applicationIDTxt = new JLabel("");
		jobIDTxt = new JLabel("");
		nameTxt = new JTextField();
		CVTxt = new JTextField();
		TranscriptTxt = new JTextField();
		
		update = new JButton("Update");
		delete = new JButton("Delete");
		Back = new JButton("Back");
		Approve = new JButton("Approve");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ApplicationID, JobID;
				try {
					ApplicationID=Integer.parseInt(applicationIDTxt.getText());
					JobID=Integer.parseInt(jobIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select an application to Edit!");
					return;
				}
				
				String Name=nameTxt.getText();
				if(Name.length()<1) {
					JOptionPane.showMessageDialog(null, "Name Must be Filled!");
					return;
				}
				
				String CVDesc=CVTxt.getText();
				if(CVDesc.length()<1) {
					JOptionPane.showMessageDialog(null, "CV Description Must be Filled!");
					return;
				}
				
				String TranscriptDesc=TranscriptTxt.getText();
				if(TranscriptDesc.length()<1) {
					JOptionPane.showMessageDialog(null, "Transcript Description Must be Filled!");
					return;
				}
				if(UserHandler.getRole(UserID).compareTo("Student")==0) {
					ApplicationHandler.update(ApplicationID, UserID, JobID, Name, CVDesc, TranscriptDesc, "Intern");}
				else if(UserHandler.getRole(UserID).compareTo("Employee")==0) {
					ApplicationHandler.update(ApplicationID, UserID, JobID, Name, CVDesc, TranscriptDesc, "Job");}
				
				loadData(UserID);
			}
		});
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ApplicationID;
				try {
					ApplicationID=Integer.parseInt(applicationIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select an application to Edit!");
					return;
				}
				
				ApplicationHandler.delete(ApplicationID);
				loadData(UserID);
			}
		});
		Approve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int ApplicationID;
				try {
					ApplicationID=Integer.parseInt(applicationIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select an application to Edit!");
					return;
				}
				
				ApprovementHandler.Insert(ApplicationID);
			}
		});
		Back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		//SELECT row
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				
				applicationIDTxt.setText(table.getValueAt(row, 0).toString()+"");
				jobIDTxt.setText(table.getValueAt(row, 1).toString()+"");
				nameTxt.setText(table.getValueAt(row, 2).toString()+"");
				CVTxt.setText(table.getValueAt(row, 3).toString()+"");
				TranscriptTxt.setText(table.getValueAt(row, 4).toString()+"");
			}
		});
			
		top.add(sp);
				
		if(CompanyHandler.getCompany(UserID)!=null) {
			bot.add(Approve);
		}else {
			mid.add(nameLabel);
			mid.add(nameTxt);
			mid.add(CVLabel);
			mid.add(CVTxt);
			mid.add(transcriptLbl);
			mid.add(TranscriptTxt);
			
			bot.add(update);
			bot.add(delete);
		}
		bot.add(Back);
		
		add(top,BorderLayout.NORTH);
		add(mid,BorderLayout.CENTER);
		add(bot,BorderLayout.SOUTH);
		

	}
			
	private void loadData(Integer UserID) {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Application ID");
		header.add("Job ID");
		header.add("Name");
		header.add("CV Desc");
		header.add("Transcript");
		header.add("Job Type");
		
		if(data==null)data = new Vector<>();
		else data.clear();
		if(UserHandler.getRole(UserID).compareTo("Staff")==0)
			for(Application a : ApplicationHandler.GetAll()) {
				detail = new Vector<>();
				
				detail.add(a.applicationID+"");
				detail.add(a.jobID+"");
				detail.add(a.name+"");
				detail.add(a.cvdescription+"");
				detail.add(a.transcriptdescription+"");
				detail.add(a.type+"");
				
				data.add(detail);
			}
		else
			for(Application a : ApplicationHandler.GetAll(UserID)) {
				detail = new Vector<>();
				
				detail.add(a.applicationID+"");
				detail.add(a.jobID+"");
				detail.add(a.name+"");
				detail.add(a.cvdescription+"");
				detail.add(a.transcriptdescription+"");
				detail.add(a.type+"");
				
				data.add(detail);
			}
		
		DefaultTableModel dtm = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(dtm);
}}
