package tes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class OpenData extends JPanel {

	private JTable table;
	private JButton cls;
	private MainMenu m;
	/**
	 * Create the panel.
	 */
	public OpenData(MainMenu m){
		this.m = m;
		this.cls = m.btX();
		initComponent();
	}
	
	private void initComponent() {
		
		table = new JTable();
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		JButton loadSQL = new JButton("Buka Data");
		
		
		loadSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel del = (DefaultTableModel) table.getModel();
					del.setRowCount(0);
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
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
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSQL.setEnabled(true);
				DefaultTableModel cls = (DefaultTableModel) table.getModel();
				cls.setRowCount(0);
				btnCancel.setEnabled(false);
			};
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
		
		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cls.doClick();
			}
		});
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(loadSQL)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPrint)
							.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnX)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(loadSQL)
						.addComponent(btnCancel)
						.addComponent(btnPrint)
						.addComponent(btnX))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
					.addGap(36))
		);
		this.setLayout(gl_panel);
	}
}		// TODO Auto-generated method stub