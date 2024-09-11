package tes;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import java.awt.event.ActionEvent;

public class InputMasterKendaraan extends JPanel {
	private JTextField jenis;
	private JTextField merk;
	private JTextField nopol;
	private JTextField driver;
	private JTable table;
	private JButton btnX;
	private MainMenu mn;
	private JLabel usr;

	/**
	 * Create the panel.
	 */
	public InputMasterKendaraan(MainMenu mn) {
		this.mn=mn;
		this.btnX=mn.btX();
		this.usr=mn.uslog();
		table = new JTable();
		JLabel lblInputMasterKendaraan = new JLabel("Input Master Armada");
		lblInputMasterKendaraan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblJenisKendaraan = new JLabel("Jenis Kendaraan");
		
		JLabel lblMerk = new JLabel("Merk");
		
		JLabel lblNewLabel = new JLabel("No. Pol.");
		
		JLabel lblDriver = new JLabel("Driver");
		
		jenis = new JTextField();
		jenis.setColumns(10);
		
		merk = new JTextField();
		merk.setColumns(10);
		
		nopol = new JTextField();
		nopol.setColumns(10);
		
		driver = new JTextField();
		driver.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		DefaultTableModel tblx = (DefaultTableModel)table.getModel();
		String query = "select `jenis_armada`,`no_pol`,`merk`,`driver` from master_armada";
		tblx.setColumnIdentifiers(SqlUrl.sqlGetColumn(query));
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tbh = (DefaultTableModel)table.getModel();
				String jens,npl,mk,drv;
				jens=jenis.getText();
				npl=nopol.getText();
				mk=merk.getText();
				drv=driver.getText();
				String[] rdt = {jens,npl,mk,drv};
				tbh.addRow(rdt);
				jenis.setText(null);
				merk.setText(null);
				nopol.setText(null);
				driver.setText(null);
			}
		});
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			upload();
			}
		});
		
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
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInputMasterKendaraan)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMerk)
										.addComponent(lblJenisKendaraan)
										.addComponent(lblNewLabel)
										.addComponent(lblDriver))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(driver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(nopol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(merk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(jenis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnTambah)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSimpan)))
									.addGap(105)))
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(btnX)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblInputMasterKendaraan)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblJenisKendaraan)
								.addComponent(jenis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTambah)
								.addComponent(btnSimpan))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMerk)
								.addComponent(merk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(nopol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDriver)
								.addComponent(driver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(btnX)))
					.addGap(108)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGap(24))
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
public void upload() {
	DefaultTableModel inp = (DefaultTableModel)table.getModel();
	try {
		Class.forName(SqlUrl.Driver());
		Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
		String query = "insert into master_armada(`jenis_armada`,`no_pol`,`merk`,`driver`,`user`) values (?,?,?,?,?)";
		PreparedStatement prep = con.prepareStatement(query);
		Object jens,npl,mrk,drivr;
		for (int i=0;i<inp.getRowCount();i++) {
			jens=inp.getValueAt(i, 0);
			npl=inp.getValueAt(i, 1);
			mrk=inp.getValueAt(i, 2);
			drivr=inp.getValueAt(i, 3);
			prep.setObject(1, jens);
			prep.setObject(2, npl);
			prep.setObject(3, mrk);
			prep.setObject(4, drivr);
			prep.setObject(5, usr.getText());
			prep.execute();
			Thread.sleep(1000);
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e);
	}finally {
		JOptionPane.showMessageDialog(null, "success");
	}
	inp.setRowCount(0);
	
}
}
