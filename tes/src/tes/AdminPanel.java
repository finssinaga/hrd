package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Container;
import java.awt.CardLayout;

public class AdminPanel extends JFrame {

	private Container contentPane;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textGrouplvl;
	private JTextField textSearch;
	private JTextField removedUser;
	private JTextField removedPass;
	private JTextField removedGrp;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {
		JPanel removeuser = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUserManage = new JMenu("user");
		menuBar.add(mnUserManage);
		
		JMenuItem mntmAddUser = new JMenuItem("add user");
		
		mnUserManage.add(mntmAddUser);
		
		JMenuItem mntmRemoveUser = new JMenuItem("remove user");
		mntmRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeuser.setVisible(true);
			}
		});
		mnUserManage.add(mntmRemoveUser);
		
		JMenuItem mntmManageUserGroup = new JMenuItem("manage user group");
		mnUserManage.add(mntmManageUserGroup);
		
		JMenu mnDatabase = new JMenu("database");
		menuBar.add(mnDatabase);
		
		JMenuItem mntmSelectMainDatabase = new JMenuItem("main database");
		mnDatabase.add(mntmSelectMainDatabase);
		
		JMenuItem mntmLoginDatabase = new JMenuItem("login database");
		mnDatabase.add(mntmLoginDatabase);
		
		JMenu mnUpdate = new JMenu("update");
		menuBar.add(mnUpdate);
		contentPane = new Container();
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel adduserr = new JPanel();
		contentPane.add(adduserr, "name_318087578862523");
		
		
		JLabel lblAddUser = new JLabel("add user");
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		
		JLabel lblUserPassword = new JLabel("password");
		
		textGrouplvl = new JTextField();
		textGrouplvl.setColumns(10);
		
		JLabel lblGroupLevel = new JLabel("group level");
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.userpass(),SqlUrl.userpass());
					String query = "insert into user(`userId`, `pass`,`grouplvl`) values (?,?,?)";
					PreparedStatement prep = con.prepareStatement(query);
					prep.setString(1, textUsername.getText());
					prep.setString(2, textPassword.getText());
					prep.setString(3, textGrouplvl.getText());
					prep.execute();
					prep.close();
					
				} catch (SQLException | ClassNotFoundException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(adduserr, e2);
				}
			}
		});
		
		JButton btnClose = new JButton("close");
		
		GroupLayout gl_adduserr = new GroupLayout(adduserr);
		gl_adduserr.setHorizontalGroup(
			gl_adduserr.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_adduserr.createSequentialGroup()
					.addGroup(gl_adduserr.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_adduserr.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAddUser))
						.addGroup(gl_adduserr.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_adduserr.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsername)
								.addComponent(lblUserPassword)
								.addComponent(lblGroupLevel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_adduserr.createParallelGroup(Alignment.LEADING)
								.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textGrouplvl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_adduserr.createSequentialGroup()
									.addComponent(btnSave)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnClose)))))
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_adduserr.setVerticalGroup(
			gl_adduserr.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_adduserr.createSequentialGroup()
					.addComponent(lblAddUser)
					.addGap(18)
					.addGroup(gl_adduserr.createParallelGroup(Alignment.BASELINE)
						.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_adduserr.createParallelGroup(Alignment.BASELINE)
						.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserPassword))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_adduserr.createParallelGroup(Alignment.BASELINE)
						.addComponent(textGrouplvl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGroupLevel))
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addGroup(gl_adduserr.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		
		adduserr.setLayout(gl_adduserr);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adduserr.setVisible(false);
				menuBar.setVisible(true);
			}
		});
		mntmAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.add(removeuser, "name_318087596830067");
		
		JLabel lblRemoveUser = new JLabel("remove user");
		
		textSearch = new JTextField();
		textSearch.setColumns(10);
		table = new JTable();
		
		JLabel lblSearch = new JLabel("search");
		
		JButton btnByName = new JButton("by name");
		JButton btnByGroup = new JButton("by group");
		
		JButton btnSelect = new JButton("select");
		
		removedUser = new JTextField();
		removedUser.setColumns(10);
		
		removedPass = new JTextField();
		removedPass.setColumns(10);
		
		removedGrp = new JTextField();
		removedGrp.setColumns(10);
		
		JButton btnRemove = new JButton("remove");
		
		JButton btnCancel = new JButton("cancel");
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnClose_1 = new JButton("close");
//-----------------------------------------keyevent action event section for remove user panel---------------------
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cls1) {
				removeuser.setVisible(false);
			}
		});
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
					ResultSetMetaData resmd = rest.getMetaData();
							int col=resmd.getColumnCount();
					String[] colname=new String[col];
					
					for (int i=0;i<col;i++) {
						colname[i]=resmd.getColumnName(i+1);
						rmv.setColumnIdentifiers(colname);
					}
					String userid,passid,group;
					
					while(rest.next()) {
						userid=rest.getString(1);
						passid=rest.getString(2);
						group=rest.getString(3);
						String[] rdata = {userid,passid,group};
						rmv.addRow(rdata);
					}
					
				} catch (SQLException rm) {
					JOptionPane.showMessageDialog(removeuser, rm);
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
					ResultSetMetaData resmd = rest.getMetaData();
							int col=resmd.getColumnCount();
					String[] colname=new String[col];
					
					for (int i=0;i<col;i++) {
						colname[i]=resmd.getColumnName(i+1);
						rmv.setColumnIdentifiers(colname);
					}
					String userid,passid,group;
					
					while(rest.next()) {
						userid=rest.getString(1);
						passid=rest.getString(2);
						group=rest.getString(3);
						String[] rdata = {userid,passid,group};
						rmv.addRow(rdata);
					}
					
				} catch (SQLException rm) {
					JOptionPane.showMessageDialog(removeuser, rm);
					rm.printStackTrace();
					// TODO: handle exception
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		GroupLayout gl_removeuser = new GroupLayout(removeuser);
		gl_removeuser.setHorizontalGroup(
			gl_removeuser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeuser.createSequentialGroup()
					.addGroup(gl_removeuser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_removeuser.createSequentialGroup()
							.addGroup(gl_removeuser.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_removeuser.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblRemoveUser))
								.addGroup(gl_removeuser.createSequentialGroup()
									.addGap(12)
									.addComponent(lblSearch)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnByName))
								.addGroup(gl_removeuser.createSequentialGroup()
									.addContainerGap()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_removeuser.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSelect)
								.addGroup(gl_removeuser.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnClose_1)
									.addComponent(btnByGroup))))
						.addGroup(gl_removeuser.createSequentialGroup()
							.addContainerGap()
							.addComponent(removedUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removedPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removedGrp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_removeuser.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRemove)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancel)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		gl_removeuser.setVerticalGroup(
			gl_removeuser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeuser.createSequentialGroup()
					.addComponent(lblRemoveUser)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_removeuser.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearch)
						.addComponent(btnByName)
						.addComponent(btnByGroup))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_removeuser.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelect))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_removeuser.createParallelGroup(Alignment.BASELINE)
						.addComponent(removedUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removedPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removedGrp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_removeuser.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemove)
						.addComponent(btnCancel)
						.addComponent(btnClose_1))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		
		scrollPane.setViewportView(table);
		removeuser.setLayout(gl_removeuser);
		
	}
}
