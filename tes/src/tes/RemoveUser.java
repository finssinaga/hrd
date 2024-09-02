package tes;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;

public class RemoveUser extends JPanel {

	private JTextField textSearch;
	private JTextField txtRmUser;
	private JTextField txtRmPass;
	private JTextField txtRmGroup;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public RemoveUser() {
		JLabel lblRemoveUser = new JLabel("remove user");
		
		JLabel label_1 = new JLabel("search");
		
		textSearch = new JTextField();
		textSearch.setColumns(10);
		
		JButton btnByName = new JButton("by name");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSelect = new JButton("select");
		
		JButton btnByGroup = new JButton("by group");
		
		txtRmUser = new JTextField();
		txtRmUser.setEditable(false);
		txtRmUser.setColumns(10);
		
		txtRmPass = new JTextField();
		txtRmPass.setEditable(false);
		txtRmPass.setColumns(10);
		
		txtRmGroup = new JTextField();
		txtRmGroup.setEditable(false);
		txtRmGroup.setColumns(10);
		
		JButton btnRemove = new JButton("remove");
		
		JButton btnCancel = new JButton("cancel");
		
		JLabel lblPressFTo = new JLabel("press f2 to close");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnByName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnByGroup, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRemoveUser, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblPressFTo, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(txtRmUser, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(txtRmPass, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(txtRmGroup, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
					.addGap(126))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRemoveUser)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnByName)
								.addComponent(btnByGroup)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(label_1)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(btnSelect))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtRmUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRmPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRmGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemove)
						.addComponent(btnCancel)
						.addComponent(lblPressFTo))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		btnByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e11) {
				try {
					DefaultTableModel rmv = (DefaultTableModel)table.getModel();
					rmv.setRowCount(0);
					String user = "'"+textSearch.getText()+"'";
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.userpass(),SqlUrl.userpass());
					Statement stat = con.createStatement();
					String query = SqlUrl.usrlogin()+" where userId ="+user;
					ResultSet rest = stat.executeQuery(query);
					String[] col = {"UserID","Password","Group"};
					rmv.setColumnIdentifiers(col);
					String userid,passid,group;
					
					while(rest.next()) {
						userid=rest.getString(1);
						passid=rest.getString(2);
						group=rest.getString(3);
						String[] rdata = {userid,passid,group};
						rmv.addRow(rdata);
					}
					
				} catch (SQLException rm) {
					JOptionPane.showMessageDialog(btnByName, rm);
					rm.printStackTrace();
					// TODO: handle exception
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnByGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e11) {
				try {
					DefaultTableModel rmv = (DefaultTableModel)table.getModel();
					rmv.setRowCount(0);
					String user = "'"+textSearch.getText()+"'";
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.userpass(),SqlUrl.userpass());
					Statement stat = con.createStatement();
					String query = SqlUrl.usrlogin()+" where grouplvl ="+user;
					ResultSet rest = stat.executeQuery(query);
					String[] col = {"UserId","Password","Group"};
					rmv.setColumnIdentifiers(col);
					String userid,passid,group;
					
					while(rest.next()) {
						userid=rest.getString(1);
						passid=rest.getString(2);
						group=rest.getString(3);
						String[] rdata = {userid,passid,group};
						rmv.addRow(rdata);
					}
					
				} catch (SQLException rm) {
					JOptionPane.showMessageDialog(btnByGroup, rm);
					rm.printStackTrace();
					// TODO: handle exception
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel sel = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				txtRmUser.setText(sel.getValueAt(row, 0).toString());
				txtRmPass.setText(sel.getValueAt(row, 1).toString());
				txtRmGroup.setText(sel.getValueAt(row, 2).toString());
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel sel = (DefaultTableModel)table.getModel();
				sel.setRowCount(0);
				txtRmUser.setText(null);
				txtRmPass.setText(null);
				txtRmGroup.setText(null);
			}
	});
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rmuser,rmpass,rmgroup;
				rmuser = txtRmUser.getText();
				rmpass = txtRmPass.getText();
				rmgroup = txtRmGroup.getText();
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.userpass(),SqlUrl.userpass());
					String query = "DELETE FROM `hrd`.`user` WHERE  `userId`='"+rmuser+"' AND `pass`='"+rmpass+"' AND `grouplvl`='"+rmgroup+"' LIMIT 1";
					Statement stat = con.createStatement();
					stat.executeUpdate(query);
				} catch (SQLException | ClassNotFoundException e2e) {
					JOptionPane.showMessageDialog(btnRemove, e2e);
					// TODO: handle exception
				} finally {
					JOptionPane.showMessageDialog(btnRemove, "Query succeded");
				}
			}
		});
		
}}
