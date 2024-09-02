package tes;

import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import javafx.scene.control.ComboBox;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComboBox;

public class GroupManager extends JPanel {
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public GroupManager() {
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("read data");
		
		JCheckBox chckbxWriteData = new JCheckBox("write data");
		
		JCheckBox chckbxEditData = new JCheckBox("edit data");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new SqlUrl().level()));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxEditData)
						.addComponent(chckbxWriteData)
						.addComponent(chckbxNewCheckBox))
					.addGap(56))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(45)
							.addComponent(chckbxNewCheckBox)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxWriteData)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxEditData))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(183, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		

	}
	public String userid() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.userpass(),SqlUrl.userpass());
		Statement stat = con.createStatement();
		String user = "'"+getComboBox()+"'";
		String query = "select * from user where userid ="+user;
		ResultSet res = stat.executeQuery(query);
		res.next();
		String Suser = res.getString(1);
	return Suser;
}	
	public String getComboBox() {
		String cbbx = comboBox.getSelectedItem().toString();
		return cbbx;
	}
}
