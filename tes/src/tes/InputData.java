package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import com.github.lgooddatepicker.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.DropMode;

public class InputData extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputData frame = new InputData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputData() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		sql();
		JButton btnSave = new JButton("Save");
		JButton btnTambah = new JButton("Tambah");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		btnTambah.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel edit =(DefaultTableModel)table.getModel();
				
				for(int i=0;i<=5;i++) {				
					edit.setRowCount(i);
				}
				
			}
		});
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tanggal,jeniskend,namapart,merkpart,user,keterangan;
				try {
					DefaultTableModel insert = (DefaultTableModel)table.getModel();
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://100.90.166.57:3306/hrd", "hrdjerapah", "hrdjerapah");
					for (int i=0; i<insert.getRowCount();i++) {
						jeniskend = insert.getValueAt(i, 1).toString();
						namapart = insert.getValueAt(i, 2).toString();
						merkpart = insert.getValueAt(i, 3).toString();
						user = insert.getValueAt(i, 4).toString();
						keterangan = insert.getValueAt(i, 5).toString();
						
						String query = "insert into hrds (`jeniskend`, `namapart`, `merkpart`, `user`, `keterangan`)values (?,?,?,?,?)";
						PreparedStatement prep = con.prepareStatement(query);
						prep.setString(1, jeniskend);
						prep.setString(2, namapart);
						prep.setString(3, merkpart);
						prep.setString(4, user);
						prep.setString(5, keterangan);
						
						prep.execute();
						}
					}
				catch (Exception ins) {
					ins.printStackTrace();
				}
				}
			}
		);
			
		
		JButton btnCancel = new JButton("Cancel");
		
		DatePicker date = new DatePicker();
		date.getComponentDateTextField().setDropMode(DropMode.INSERT);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnTambah)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
							.addComponent(btnCancel))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
					.addGap(14))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTambah)
						.addComponent(btnSave)
						.addComponent(btnCancel)
						.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addGap(80))
		);
		
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		
		
	}
private void sql() {
	try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://100.90.166.57:3306/hrd","hrdjerapah","hrdjerapah");
		Statement stats = co.createStatement();
		String query = "select * from hrds";
		ResultSet res = stats.executeQuery(query);
		ResultSetMetaData rsmd = res.getMetaData();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		int col=rsmd.getColumnCount();
		String[] colname=new String[col];
		
		for (int i=0;i<col;i++) {
			colname[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colname);
		}
	}
		catch (Exception e1) {
			e1.printStackTrace();}
		}
private void date() {

}
}

