package tes;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MasterKendaraan extends JPanel {
	private JTable table;
	private MainMenu mn;
	private JButton btnX;

	/**
	 * Create the panel.
	 */
	public MasterKendaraan(MainMenu mn) {
		this.mn=mn;
		this.btnX=mn.btX();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblMasterKendaraan = new JLabel("Master Armada");
		lblMasterKendaraan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JComboBox comboBox = new JComboBox();
		
		
		JButton btnBuka = new JButton("Buka");
		
		JLabel lblUrutBerdasarkan = new JLabel("Urut Berdasarkan : ");
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.btX().doClick();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMasterKendaraan)
							.addPreferredGap(ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
							.addComponent(btnX))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUrutBerdasarkan)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuka))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblMasterKendaraan))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(btnX)))
					.addGap(39)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUrutBerdasarkan)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBuka))
						.addGap(0, 0, Short.MAX_VALUE))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
		String quer = "select `tanggal_input`,`jenis_armada`,`no_pol`,`merk`,`driver` from master_armada";
		String[] md = SqlUrl.sqlGetColumn(quer);
		comboBox.setModel(new DefaultComboBoxModel<>(md));
		DefaultTableModel l = (DefaultTableModel)table.getModel();
		l.setColumnIdentifiers(md);
		btnBuka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tb = (DefaultTableModel)table.getModel();
				tb.setRowCount(0);
				String sort = comboBox.getSelectedItem().toString();
				String query = "select * from master_armada order by '"+sort+"'";
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					Statement stat = con.createStatement();
					ResultSet res = stat.executeQuery(query);
					String tanggal = null,jenis = null,nopol = null,merk = null,driver = null;
					while (res.next()) {
						tanggal=res.getString(1);
						jenis=res.getString(3);
						nopol=res.getString(4);
						merk=res.getString(5);
						driver=res.getString(6);
					}
					Object[] rdt = {tanggal,jenis,nopol,merk,driver};
					tb.addRow(rdt);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
