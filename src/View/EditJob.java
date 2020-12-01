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

import Controller.JobHandler;
import Model.Job;

@SuppressWarnings("serial")
public class EditJob extends JFrame{
	JPanel top, mid, bot;
	JTable dataTable;
	JScrollPane scrollPane;
	JLabel NameLabel, DescriptionLabel, SalaryLabel, JobIDLabel, CompanyIDLabel, JobIDTxt, CompanyIDTxt;
	JTextField NameTxt, DescriptionTxt, SalaryTxt;
	JButton Insert, Update, Delete, Back;
	
	Vector<String> Header, Detail;
	Vector<Vector<String>> Data;
	
	public EditJob(int companyID) {
		init(companyID);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void init(int companyID) {
		top = new JPanel();
		mid = new JPanel(new GridLayout(5, 2));
		bot = new JPanel(); 
		
		dataTable = new JTable();
		dataTable.addMouseListener(new MouseListener() {
			
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
				int row = dataTable.getSelectedRow();
				JobIDTxt.setText(dataTable.getValueAt(row, 0).toString()+"");
				CompanyIDTxt.setText(dataTable.getValueAt(row, 1).toString()+"");
				NameTxt.setText(dataTable.getValueAt(row, 2).toString()+"");
				DescriptionTxt.setText(dataTable.getValueAt(row, 3).toString()+"");
				SalaryTxt.setText(dataTable.getValueAt(row, 4).toString()+"");
			}
		});
		loadData(companyID);
		
		scrollPane = new JScrollPane(dataTable);
		scrollPane.setPreferredSize(new Dimension(850, 300));
		
		JobIDLabel = new JLabel("JobID: ");
		CompanyIDLabel = new JLabel("CompanyID: ");
		NameLabel = new JLabel("Name: ");
		DescriptionLabel = new JLabel("Description: ");
		SalaryLabel = new JLabel("Salary: ");
		
		JobIDTxt = new JLabel("");
		CompanyIDTxt=  new JLabel(companyID+"");
		NameTxt = new JTextField();
		DescriptionTxt = new JTextField();
		SalaryTxt = new JTextField();
		
		Insert = new JButton("Insert");
		Update = new JButton("Update");
		Delete = new JButton("Delete");
		Back = new JButton("Back");
		Insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name=NameTxt.getText();
				if(name.length()<1) {
					JOptionPane.showMessageDialog(null, "Name Must be Filled!");
					return;
				}
				String description=DescriptionTxt.getText();
				if(description.length()<1) {
					JOptionPane.showMessageDialog(null, "Description Must be Filled!");
					return;
				}
				long salary=0;
				try {
					salary = Long.parseLong(SalaryTxt.getText());
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Salary Must be Numeric!");
					return;
				}
				JobHandler.insert(1, name, description, salary);
				loadData(companyID);
			}
		});
		Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int jobID;
				try {
					jobID=Integer.parseInt(JobIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select a Job to Edit!");
					return;
				}
				
				String name=NameTxt.getText();
				if(name.length()<1) {
					JOptionPane.showMessageDialog(null, "Name Must be Filled!");
					return;
				}
				String description=DescriptionTxt.getText();
				if(description.length()<1) {
					JOptionPane.showMessageDialog(null, "Description Must be Filled!");
					return;
				}
				long salary=0;
				try {
					salary = Long.parseLong(SalaryTxt.getText());
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Salary Must be Numeric!");
					return;
				}
				JobHandler.update(jobID, companyID, name, description, salary);
				loadData(companyID);
			}
		});
		Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int jobID=0;
				try {
					jobID=Integer.parseInt(JobIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select a Job to Edit!");
					return;
				}
				JobHandler.delete(jobID, companyID);
				loadData(companyID);
			}
		});
		Back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		top.add(scrollPane);
		
		mid.add(JobIDLabel);
		mid.add(JobIDTxt);
		mid.add(CompanyIDLabel);
		mid.add(CompanyIDTxt);
		mid.add(NameLabel);
		mid.add(NameTxt);
		mid.add(DescriptionLabel);
		mid.add(DescriptionTxt);
		mid.add(SalaryLabel);
		mid.add(SalaryTxt);
		
		bot.add(Insert);
		bot.add(Update);
		bot.add(Delete);
		bot.add(Back);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void loadData(int companyID) {
		if(Header==null) {
			Header = new Vector<>();
			Header.add("JobID");
			Header.add("CompanyID");
			Header.add("Name");
			Header.add("Description");
			Header.add("Salary");
		}
		if(Data==null)Data = new Vector<>();
		else Data.clear();
		for(Job j : JobHandler.GetAll(companyID)) {
			Detail=new Vector<>();
			
			Detail.add(j.jobID+"");
			Detail.add(j.companyID+"");
			Detail.add(j.name+"");
			Detail.add(j.description+"");
			Detail.add(j.salary+"");
			
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
