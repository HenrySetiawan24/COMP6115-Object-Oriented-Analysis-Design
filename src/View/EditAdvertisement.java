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

import Controller.AdvertisementHandler;
import Controller.CompanyHandler;
import Model.Advertisement;

@SuppressWarnings("serial")
public class EditAdvertisement extends JFrame{
	JPanel top, mid, bot;
	JTable dataTable;
	JScrollPane scrollPane;
	JLabel TitleLabel, DescriptionLabel, advertisementIDTxt, advertisementIDLabel, CompanyIDTxt, CompanyIDLabel;
	JTextField TitleTxt, DescriptionTxt;
	JButton Insert, Update, Delete, Back;
	
	Vector<String> Header, Detail;
	Vector<Vector<String>> Data;
	
	public EditAdvertisement(int companyID) {
		init(companyID);
		setTitle("Manage Advertisements");
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
				advertisementIDTxt.setText(dataTable.getValueAt(row, 0).toString()+"");
				CompanyIDTxt.setText(dataTable.getValueAt(row, 1).toString()+"");
				TitleTxt.setText(dataTable.getValueAt(row, 2).toString()+"");
				DescriptionTxt.setText(dataTable.getValueAt(row, 3).toString()+"");
			}
		});
		loadData(companyID);
		
		scrollPane = new JScrollPane(dataTable);
		scrollPane.setPreferredSize(new Dimension(850, 300));
		
		advertisementIDLabel = new JLabel("advertisementID: ");
		CompanyIDLabel = new JLabel("CompanyID: ");
		TitleLabel = new JLabel("Title: ");
		DescriptionLabel = new JLabel("Description: ");
		
		advertisementIDTxt = new JLabel("");
		CompanyIDTxt=  new JLabel(companyID+"");
		TitleTxt = new JTextField();
		DescriptionTxt = new JTextField();
		
		Insert = new JButton("Insert");
		Update = new JButton("Update");
		Delete = new JButton("Delete");
		Back = new JButton("Back");
		Insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String title=TitleTxt.getText();
				if(title.length()<1) {
					JOptionPane.showMessageDialog(null, "Title Must be Filled!");
					return;
				}
				String description=DescriptionTxt.getText();
				if(description.length()<1) {
					JOptionPane.showMessageDialog(null, "Description Must be Filled!");
					return;
				}
				AdvertisementHandler.insert(1, title, description);
				loadData(companyID);
			}
		});
		Update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int advertisementID;
				try {
					advertisementID=Integer.parseInt(advertisementIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select a Job to Edit!");
					return;
				}
				
				String title=TitleTxt.getText();
				if(title.length()<1) {
					JOptionPane.showMessageDialog(null, "Title Must be Filled!");
					return;
				}
				String description=DescriptionTxt.getText();
				if(description.length()<1) {
					JOptionPane.showMessageDialog(null, "Description Must be Filled!");
					return;
				}
				AdvertisementHandler.update(advertisementID, companyID, title, description);
				loadData(companyID);
			}
		});
		Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int advertisementID=0;
				try {
					advertisementID=Integer.parseInt(advertisementIDTxt.getText());
				}catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Select a Job to Edit!");
					return;
				}
				AdvertisementHandler.delete(advertisementID, companyID);
				loadData(companyID);
			}
		});
		Back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				CompanyHandler.viewCompanyMenu(companyID);
			}
		});
		
		top.add(scrollPane);
		
		mid.add(advertisementIDLabel);
		mid.add(advertisementIDTxt);
		mid.add(CompanyIDLabel);
		mid.add(CompanyIDTxt);
		mid.add(TitleLabel);
		mid.add(TitleTxt);
		mid.add(DescriptionLabel);
		mid.add(DescriptionTxt);
		
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
			Header.add("advertisementID");
			Header.add("CompanyID");
			Header.add("Title");
			Header.add("Description");
		}
		if(Data==null)Data = new Vector<>();
		else Data.clear();
		for(Advertisement j : AdvertisementHandler.GetAll(companyID)) {
			Detail=new Vector<>();
			
			Detail.add(j.advertisementID+"");
			Detail.add(j.companyID+"");
			Detail.add(j.title+"");
			Detail.add(j.description+"");
			
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
