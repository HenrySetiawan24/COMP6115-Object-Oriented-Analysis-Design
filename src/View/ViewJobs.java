package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Model.Connect;

public class ViewJobs extends JFrame{
	
	Connect con = new Connect();
	JPanel top,mid,bot,left;
	JTable table;
	JTable adtable;
	JScrollPane sp;
	JScrollPane adsp;
	JLabel idLbl,idValue,nameLbl,descLbl,reqLbl,roleLbl,uidLbl,jidLbl;
	JLabel adidLbl,adnameLbl,addescLbl;
	JTextField nameTxt,descTxt,reqTxt,roleTxt,uidTxt,jidTxt;
	JButton insert,update,delete;
	Vector<Vector<String>> data;
	Vector<Vector<String>> addata;

	Vector<String> detail,header;
	Vector<String> addetail,adheader;
		
	
	public ViewJobs() {
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
		
		table = new JTable();
		adtable = new JTable();
		
		sp = new JScrollPane(table);
		adsp = new JScrollPane(adtable);
		
		idLbl = new JLabel("ID:");
		nameLbl = new JLabel("Name:");
		descLbl = new JLabel("Desc:");
		reqLbl = new JLabel("Req:");
		roleLbl = new JLabel("Role:");
		idValue = new JLabel("-");
		uidLbl = new JLabel("User ID:");
		jidLbl = new JLabel("Job ID:");
		
		adnameLbl = new JLabel("AD:");
		addescLbl = new JLabel("Desc:");
		
		nameTxt = new JTextField();
		descTxt = new JTextField();
		reqTxt = new JTextField();
		roleTxt = new JTextField();
		uidTxt = new JTextField();
		jidTxt = new JTextField();
		
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		
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
				idValue.setText(table.getValueAt(row, 0).toString()+"");
				uidTxt.setText(table.getValueAt(row, 1).toString()+"");
				jidTxt.setText(table.getValueAt(row, 2).toString()+"");
				nameTxt.setText(table.getValueAt(row, 3).toString()+""); 
				descTxt.setText(table.getValueAt(row, 4).toString()+"");
				reqTxt.setText(table.getValueAt(row, 5).toString()+"");
				roleTxt.setText(table.getValueAt(row, 6).toString()+"");
				
//				JobIDTxt.setText(dataTable.getValueAt(row, 0).toString()+"");
//				CompanyIDTxt.setText(dataTable.getValueAt(row, 1).toString()+"");
//				NameTxt.setText(dataTable.getValueAt(row, 2).toString()+"");
//				DescriptionTxt.setText(dataTable.getValueAt(row, 3).toString()+"");
//				SalaryTxt.setText(dataTable.getValueAt(row, 4).toString()+"");
			}
		});
		
		//INSERT button
		insert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = nameTxt.getText();
				String desc = descTxt.getText();
				String req = reqTxt.getText();
				String role = roleTxt.getText();
				Integer uid = 0;
				Integer jid = 0;
				
				try {
					uid = Integer.parseInt(uidTxt.getText());
					jid = Integer.parseInt(jidTxt.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "ID must be integer");
					return; 
			}
				
			ApplicationHandler.insert(uid, jid, name, desc, req, role);
			loadData(con.execQuery("SELECT * FROM application"));
			}
		});
		
		//UPDATE button
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTxt.getText();
				String desc = descTxt.getText();
				String req = reqTxt.getText();
				String role = roleTxt.getText();
				Integer aid = 0;
				Integer uid = 0;
				Integer jid = 0;
				try {
					uid = Integer.parseInt(uidTxt.getText());
					jid = Integer.parseInt(jidTxt.getText());
					aid = Integer.parseInt(idValue.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ID must be integer");
					return; 
				}
				
				ApplicationHandler.update(aid, uid, jid, name, desc, req, role);
				loadData(con.execQuery("SELECT * FROM application"));
				
			}
		});
		
		//DELETE button
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int aid = 0;
				try {
					aid = Integer.parseInt(idValue.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ID must be integer");
					return; 
				}
				
				ApplicationHandler.delete(aid);
				loadData(con.execQuery("SELECT * FROM application"));
				
			}
		});
		
		
//		sp.setPreferredSize(500,500);
		
		//Randomize Advertisement
		int rowcount = adtable.getRowCount();
		int min = 1;
		int range = rowcount - min + 1;
		int random = 0;
		for(int i = 0 ; i < rowcount; i++ )
		{
			 random = (int)(Math.random() * range ) + min; 
		}
		loadData(con.execQuery("SELECT * FROM application"));
//		
		loadadData(con.execQuery("SELECT * FROM advertisement WHERE advertisementID = "+random));
		
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
		
		
		bot.add(insert);
		bot.add(update);
		bot.add(delete);
		
		add(top,BorderLayout.NORTH);
		add(mid,BorderLayout.CENTER);
		add(bot,BorderLayout.SOUTH);
		add(left,BorderLayout.EAST);
		

	}
	
	private void loadadData(ResultSet rs) {
		adheader = new Vector<>();
		addata = new Vector<>();
		
		adheader.add("AD");
		adheader.add("AD Desc");
		
		try {
			while(rs.next()) {
				String name = rs.getString("tittle");
				String desc = rs.getString("description");
				
				addetail = new Vector<>();
				addetail.add(name);
				addetail.add(desc);
				
				addata.add(addetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultTableModel addtm = new DefaultTableModel(addata, adheader);
		adtable.setModel(addtm);
	}
	
	private void loadData(ResultSet rs) {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Job ID");
		header.add("Job Desc");
		header.add("Job Req");
		header.add("Job Role");
		header.add("Job Type");
		
		try {
			while(rs.next())
			{
				Integer id = rs.getInt("applicationID");
				String name = rs.getString("name");
				String desc = rs.getString("cvdescription");
				String req = rs.getString("transcriptdescription");
				String type = rs.getString("type");
				
				detail = new Vector<>();
				detail.add(id+"");
				detail.add(name);
				detail.add(desc);
				detail.add(req);
				detail.add(type);
				
				data.add(detail);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
