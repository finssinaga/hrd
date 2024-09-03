package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import org.apache.commons.lang3.ArrayUtils;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Insets;
import javax.swing.table.DefaultTableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class MainMenu extends JFrame {
	private JTable table;
	private JPanel panel;
	private JMenuItem mntmInputData;
	private JMenuBar menuBar;

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
	 * @throws Exception 
	 */
	public MainMenu() {
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		
		table = new JTable();
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		JButton btnClose = new JButton("Close");
		JButton loadSQL = new JButton("Buka Data");
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(2, 15, 2, 0));
		setJMenuBar(menuBar);
		
		JMenu mnMaster = new JMenu("master");
		menuBar.add(mnMaster);
		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		
		Logon k = new Logon();
		JTextField usr = k.getPass();
		usr.setText("anjay login");
		
		JMenuItem mntmTable = new JMenuItem("table");
		mntmTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				getContentPane().add(panel);
				getContentPane().repaint();
				btnCancel.setEnabled(false);
				loadSQL.setEnabled(true);
			}
		});
		
		
		mnMaster.add(mntmTable);
		
		mntmInputData = new JMenuItem("input data");
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
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.userpass(),SqlUrl.userpass());
					Statement stat = con.createStatement();
					String query = SqlUrl.dbms();
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
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.getModel();
				try {
					table.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(loadSQL)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPrint)
							.addPreferredGap(ComponentPlacement.RELATED, 1028, Short.MAX_VALUE)
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
						.addComponent(loadSQL)
						.addComponent(btnClose)
						.addComponent(btnCancel)
						.addComponent(btnPrint))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
					.addGap(36))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel);
		
		
		
			File chk = new File(SqlUrl.dir());
			String[] cnk = chk.list();
			String nm = "administrator";
			boolean ada = SqlUrl.admin(cnk, nm);
			if (ada == true) {
				mntmTable.setVisible(false);
			}
			
			for (int i=0;i<SqlUrl.level().length;i++) {
				StringBuilder resl = new StringBuilder();
				String[] del = new String[SqlUrl.level().length];
				resl.append(del[i]=SqlUrl.level()[i]);
				
				File dlt = new File(SqlUrl.dir(),resl.toString());
				System.out.println(dlt);
				dlt.deleteOnExit();
			}
		
	
	}
	public JMenuItem InputDat() {
		return mntmInputData;
	}
	public JMenuBar getMenu() {
		return menuBar;
	}
	
}


