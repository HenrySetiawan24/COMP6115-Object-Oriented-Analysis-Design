package View;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Model.Advertisement;
import Model.Application;
import Model.Connect;


public class ViewAdvertisement extends JFrame {

	Connect con = new Connect();
	JPanel top,mid,bot,left;

	JTable adtable;
	JScrollPane sp;
	JScrollPane adsp;
	JLabel adidLbl,cidLbl,adnameLbl,addescLbl;
	JButton apply;
	
	Vector<Vector<String>> addata;

	Vector<String> addetail,adheader;
		
	
	public ViewAdvertisement() {
		init();
		setSize(900,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	private void init() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(7,7));
		bot = new JPanel();
		left = new JPanel();
		
		adtable = new JTable();
		
		adsp = new JScrollPane(adtable);
		

		
		adnameLbl = new JLabel("AD:");
		addescLbl = new JLabel("Desc:");
		
//		
//		insert = new JButton("Insert");
//		update = new JButton("Update");
//		delete = new JButton("Delete");
		
		apply = new JButton("Apply");
		
		//SELECT row
		adtable.addMouseListener(new MouseListener() {
			
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
				idValue.setText(table.getValueAt(row, 0).toString()+"");
//				uidTxt.setText(table.getValueAt(row, 1).toString()+"");
//				jidTxt.setText(table.getValueAt(row, 2).toString()+"");
				nameTxt.setText(table.getValueAt(row, 1).toString()+""); 
				descTxt.setText(table.getValueAt(row, 2).toString()+"");
				reqTxt.setText(table.getValueAt(row, 3).toString()+"");
				roleTxt.setText(table.getValueAt(row, 4).toString()+"");
				
			}
		});
		
		//APPLY Button
		apply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		

		

		
		
//		sp.setPreferredSize(500,500);
		
		//Randomize Advertisement
		Random random = new Random();
		int adID = random.nextInt(AdvertisementHandler.GetAll().size());
		
		loadadData(adID);
		
		
		top.add(sp);
		top.add(adsp);
		
		left.add(adsp);
		
		mid.add(idLbl);
		mid.add(idValue);
		mid.add(uidLbl);
		mid.add(uidTxt);
		mid.add(jidLbl);
		mid.add(jidTxt);
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(descLbl);
		mid.add(descTxt);
		mid.add(reqLbl);
		mid.add(reqTxt);
		mid.add(roleLbl);
		mid.add(roleTxt);
		
		

		bot.add(apply);
		
		loadData(0);
		
		add(top,BorderLayout.NORTH);
		add(mid,BorderLayout.CENTER);
		add(bot,BorderLayout.SOUTH);
		add(left,BorderLayout.EAST);
		

	}
}
