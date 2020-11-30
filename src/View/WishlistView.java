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
import javax.swing.table.DefaultTableModel;

import Controller.WishlistHandler;
import Model.Job;
import Model.Wishlist;

public class WishlistView extends JFrame{

	JLabel title;
	JScrollPane scroll;
	JTable dataTable;
	JButton applyBtn, deleteBtn;
	JPanel top, mid, bot;
	JLabel wishlistID;
	
	Vector<String> header, detail;
	Vector<Vector<String>> Data;
	Job jobs;
	public WishlistView(int userID) {
		init(userID);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		
	}

	public void init(int userID) {
		top = new JPanel();
		mid = new JPanel(new BorderLayout());
		bot = new JPanel();
		wishlistID = new JLabel("");
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
				 wishlistID.setText(dataTable.getValueAt(row, 0).toString()+"");
			}
		});
		loadData(userID);
		title = new JLabel("View Wishlist");
		
		applyBtn = new JButton("Apply");
		deleteBtn = new JButton("Delete");
		
		applyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
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
		scroll = new JScrollPane(dataTable);
		scroll.setPreferredSize(new Dimension(850, 300));
		top.add(title);
		mid.add(scroll);
		bot.add(applyBtn);
		bot.add(deleteBtn);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
	}
	
	public void loadData(int userID) {
		if(header == null) {
			header = new Vector<>();
			header.add("WishlistID");
			header.add("JobID");
			header.add("CompanyID");
			header.add("Name");
			header.add("Description");
			header.add("Salary");
		}
		if(Data == null)
			Data = new Vector<>();
		else Data.clear();
		
		for (Wishlist w : WishlistHandler.getAll(userID)) {
			Job jobs = w.find(w.jobID);
			detail = new Vector<>();
			detail.add(w.wishlistID+"");
			detail.add(w.jobID+"");
			detail.add(jobs.companyID+"");
			detail.add(jobs.name+"");
			detail.add(jobs.description+"");
			detail.add(jobs.salary+"");	
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
