package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
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

import Controller.AdvertisementHandler;
import Controller.ApplicationHandler;
import Controller.JobHandler;
import Controller.UserHandler;
import Model.Advertisement;
import Model.Job;

public class ViewJobs extends JFrame{
	
	JPanel top,mid,bot,left;
	JTable table;
	JTable adtable;
	JScrollPane sp, adsp;
	JLabel NameLbl, CVDescLbl , TranscriptDescLbl, jobIDTxt;
	JTextField nameTxt, CVDescTxt, TranscriptDescTxt;
	JButton apply;
	
	Vector<Vector<String>> data;
	Vector<Vector<String>> addata;

	Vector<String> detail,header;
	Vector<String> addetail,adheader;
		
	
	public ViewJobs(int UserID) {
		init(UserID);
		setSize(900,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void init(int UserID) {
		top = new JPanel();
		mid = new JPanel(new GridLayout(4, 2));
		bot = new JPanel();
		left = new JPanel();
		
		table = new JTable();
		adtable = new JTable();
		
		sp = new JScrollPane(table);
		adsp = new JScrollPane(adtable);
		sp.setPreferredSize(new Dimension(850, 300));
		
		NameLbl = new JLabel("Application Name:");
		CVDescLbl = new JLabel("CV Description");
		TranscriptDescLbl = new JLabel("Transcript Description");
		jobIDTxt = new JLabel("");
		
		nameTxt = new JTextField();
		CVDescTxt = new JTextField();
		TranscriptDescTxt = new JTextField();
		
		apply = new JButton("Apply");
		
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
				jobIDTxt.setText(table.getValueAt(row, 0).toString()+"");
				nameTxt.setText(table.getValueAt(row, 1).toString()+""); 
			}
		});
		
		//APPLY Button
		apply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int JobID;
				try {
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
				System.out.println(UserHandler.getRole(UserID));
				if(UserHandler.getUser(UserID).role.compareTo("Student")==0) {
					ApplicationHandler.insert(UserID, JobID, Name, CVDesc, TranscriptDesc, "Intern");}
				else if(UserHandler.getUser(UserID).role.compareTo("Employee")==0) {
					ApplicationHandler.insert(UserID, JobID, Name, CVDesc, TranscriptDesc, "Job");}
				
				loadData();
			}
		});
		
		//Randomize Advertisement
		Random random = new Random();
		int adID = random.nextInt(AdvertisementHandler.GetAll().size());
		
		loadadData(adID);
		
		
		top.add(sp);
		
		left.add(adsp);
		
		mid.add(NameLbl);
		mid.add(nameTxt);
		mid.add(CVDescLbl);
		mid.add(CVDescTxt);
		mid.add(TranscriptDescLbl);
		mid.add(TranscriptDescTxt);
		
		bot.add(apply);
		
		loadData();
		
		add(top,BorderLayout.NORTH);
		add(mid,BorderLayout.CENTER);
		add(bot,BorderLayout.SOUTH);
		add(left,BorderLayout.EAST);
		

	}
	
	private void loadadData(int Random) {
		adheader = new Vector<>();
		addata = new Vector<>();
		
		adheader.add("AD");
		adheader.add("AD Desc");
		
		if(addata==null)addata = new Vector<>();
		else addata.clear();
		
		Advertisement a = AdvertisementHandler.GetAll().elementAt(Random); 
			addetail = new Vector<>();
			
			addetail.add(a.title+"");
			addetail.add(a.description+"");
			
			addata.add(addetail);
					
		DefaultTableModel addtm = new DefaultTableModel(addata, adheader);
		adtable.setModel(addtm);
	}
	
	private void loadData() {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Job ID");
		header.add("Job Name");
		header.add("Job Desc");
		header.add("Job Salary");
		
		if(data==null)data = new Vector<>();
		else data.clear();
		
		for(Job j : JobHandler.GetAll()) {
			detail = new Vector<>();
			
			detail.add(j.jobID+"");
			detail.add(j.name+"");
			detail.add(j.description+"");
			detail.add(j.salary+"");
			
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
	}

}
