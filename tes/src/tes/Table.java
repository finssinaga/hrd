package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.sql.*;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.ScrollPaneConstants;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Table extends JFrame {

	private Panel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table frame = new Table();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public Table() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new Panel();
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		table.setColumnSelectionAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JButton btnShowData = new JButton("show data");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://100.90.166.57:3306/hrd","hrdjerapah","hrdjerapah");
					Statement st=con.createStatement();
					String query="select * from hrds";
					ResultSet res=st.executeQuery(query);
					ResultSetMetaData rsmd=res.getMetaData();
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					
					int col=rsmd.getColumnCount();
					String[] colname=new String[col];
					
					for (int i=0;i<col;i++) {
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
					}
															
					String tanggale,jeniskend,namapart,merkpart,user,keterangan;
					while(res.next()) {
						tanggale=res.getObject(1).toString();
						jeniskend=res.getString(2);
						namapart=res.getString(3);
						merkpart=res.getString(4);
						user=res.getString(5);
						keterangan=res.getString(6);
						String[] row= {tanggale,jeniskend,namapart,merkpart,user,keterangan};
						model.addRow(row);
					}
					st.close();
					con.close();}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				}
			}
		);
		
		JButton btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel ne = (DefaultTableModel) table.getModel();
				ne.setRowCount(0);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnShowData, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnShowData)
						.addComponent(btnClear)))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
