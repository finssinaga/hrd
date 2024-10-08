package tes;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddUsr extends JPanel {
	private JTextField txtPassword;
	private JTextField txtUsername;
	

	/**
	 * Create the panel.
	 */
	public AddUsr() {
		JLabel lblAddUser = new JLabel("ADD USER");
		
		JLabel label_1 = new JLabel("username");
		
		JLabel label_2 = new JLabel("password");
		
		JLabel label_3 = new JLabel("group level");
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		JButton btnSave = new JButton("save");
		
		JLabel lblPressFTo = new JLabel("press f2 to close");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new SqlUrl().level()));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(54)
									.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblPressFTo, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(11)
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(5)
											.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, 0, 211, Short.MAX_VALUE)
										.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
										.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
							.addGap(129))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(lblAddUser, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(293))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddUser)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPressFTo))
					.addGap(58))
		);
		this.setLayout(groupLayout);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					String query = "insert into user(`userId`, `pass`,`grouplvl`) values (?,?,?)";
					PreparedStatement prep = con.prepareStatement(query);
					prep.setString(1, txtUsername.getText());
					prep.setString(2, txtPassword.getText());
					prep.setString(3, comboBox.getSelectedItem().toString());
					prep.execute();
					prep.close();
					
				} catch (SQLException | ClassNotFoundException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(btnSave, e2);
					
				}finally {
					JOptionPane.showMessageDialog(btnSave, "Query Executed");
				}
			}
		});

	}

	public void setPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}
}
