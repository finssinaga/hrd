package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.Window.Type;
import java.awt.Button;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class MainMenu extends JFrame {
	private JTable table;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		
		table = new JTable();
		JButton editSQL = new JButton("Input Data");
		editSQL.setVisible(false);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		JButton btnClose = new JButton("Close");
		JButton loadSQL = new JButton("Buka Data");
		JButton btnSave = new JButton("Save");
		btnSave.setVisible(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaster = new JMenu("master");
		menuBar.add(mnMaster);
		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		
		JMenuItem mntmTable = new JMenuItem("table");
		mntmTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				getContentPane().add(panel);
				getContentPane().repaint();
				btnCancel.setEnabled(false);
				btnSave.setEnabled(false);
				loadSQL.setEnabled(true);
				editSQL.setEnabled(true);
			}
		});
		
		
		mnMaster.add(mntmTable);
		
		JMenuItem mntmInputData = new JMenuItem("input data");
		mntmInputData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tes.InputData.main(null);
			}
		});
		mnMaster.add(mntmInputData);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		loadSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel del = (DefaultTableModel) table.getModel();
					del.setRowCount(0);
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://100.90.166.57:3306/hrd", "hrdjerapah", "hrdjerapah");
					Statement stat = con.createStatement();
					String query = "select * from hrds";
					ResultSet res = stat.executeQuery(query);
					ResultSetMetaData resmd = res.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int col=resmd.getColumnCount();
					String[] colname=new String[col];
					
					for (int i=0;i<col;i++) {
						colname[i]=resmd.getColumnName(i+1);
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
					stat.close();
					con.close();
					loadSQL.setEnabled(false);
					btnCancel.setEnabled(true);
					editSQL.setEnabled(true);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel del = (DefaultTableModel) table.getModel();
				del.setRowCount(0);
				getContentPane().remove(panel);
				getContentPane().repaint();
			}
		});
		
		
		editSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel edit = (DefaultTableModel)table.getModel();
				edit.setRowCount(0);
				int rocoun = edit.getRowCount()+1;
				edit.setRowCount(rocoun);
				loadSQL.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSQL.setEnabled(true);
				DefaultTableModel cls = (DefaultTableModel) table.getModel();
				cls.setRowCount(0);
				btnCancel.setEnabled(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSQL.setEnabled(false);
				btnCancel.setEnabled(true);
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(loadSQL)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editSQL)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnClose)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(editSQL)
						.addComponent(loadSQL)
						.addComponent(btnClose)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addGap(36))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
	}

}


