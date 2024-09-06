package tes;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class MasterStok extends JPanel {
	private JTable table;
	private MainMenu m;
	private JButton cls;
	private Logon lgn;
	private JTextField users;
	private JTextField txtJenis;
	private JTextField txtNama;
	private JTextField txtJumlah;
	private JTextField txtHarga;
	private JTextField txtUser;

	/**
	 * Create the panel.
	 */
	public MasterStok(MainMenu m) {
		this.m = m;
		this.cls = m.btX();
		
		
		
		JLabel lblTambahStokBarang = new JLabel("STOK BARANG");
		lblTambahStokBarang.setBackground(SystemColor.textHighlight);
		
		JButton btnBaru = new JButton("Baru");
		
		btnBaru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usx = Logon.getlg().getUser();
				DefaultTableModel baru = (DefaultTableModel)table.getModel();
				String query = "select * from `stok_barang`";
				baru.setColumnCount(SqlUrl.sqlGetColumn(query).length);
				baru.setColumnIdentifiers(SqlUrl.sqlGetColumn(query));
				baru.setRowCount(1);
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnSimpan = new JButton("Simpan");
		
		JButton btnBatal = new JButton("Batal");
		
		JButton btnX = new JButton("x");
		Logon ls = Logon.getlg();
		
		String message = ls.getUser();
		
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cls.doClick();
			}
		});
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel cnsl = (DefaultTableModel)table.getModel();
				cnsl.setRowCount(0);
				cnsl.setColumnCount(0);
			}
		});
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel inp = (DefaultTableModel)table.getModel();
				String jenis_barang,nama_barang,nama_user;
				int jumlah,harga;
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					String query = "insert into stok_barang (`jenis_barang`, `nama_barang`, `nama_user`, `jumlah`, `harga`)values (?,?,?,?,?)";
					for (int i=0;i<inp.getRowCount();i++) {
						jenis_barang=inp.getValueAt(i, 0).toString();
						nama_barang=inp.getValueAt(i, 1).toString();
						nama_user=Logon.getlg().getUser().toString();
						jumlah=Integer.parseInt(inp.getValueAt(i, 3).toString());
						harga=Integer.parseInt(inp.getValueAt(i, 4).toString());
						
						PreparedStatement prep = con.prepareStatement(query);
						prep.setString(1, jenis_barang);
						prep.setString(2, nama_barang);
						prep.setString(3, nama_user);
						prep.setInt(4, jumlah);
						prep.setInt(5, harga);
						prep.execute();
					}
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(btnSimpan, e);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		
		txtJenis = new JTextField();
		txtJenis.setColumns(10);
		
		txtNama = new JTextField();
		txtNama.setText("Nama");
		txtNama.setColumns(10);
		
		txtJumlah = new JTextField();
		txtJumlah.setText("Jumlah");
		txtJumlah.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setText("Harga");
		txtHarga.setColumns(10);
		
		JLabel lblJenisBarang = new JLabel("Jenis Barang");
		
		JLabel lblNamaBarang = new JLabel("Nama Barang");
		
		JLabel lblKuantitas = new JLabel("Kuantitas");
		
		JLabel lblHarga = new JLabel("Harga");
		
		txtUser = new JTextField();
		txtUser.setText("User");
		txtUser.setColumns(10);
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTambahStokBarang, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBaru)
								.addComponent(lblJenisBarang)
								.addComponent(lblNamaBarang)
								.addComponent(lblKuantitas)
								.addComponent(lblHarga))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtJenis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(83)
									.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEdit)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSimpan)
									.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
									.addComponent(btnBatal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnX))
								.addComponent(txtNama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtJumlah, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTambahStokBarang)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBaru)
						.addComponent(btnEdit)
						.addComponent(btnSimpan)
						.addComponent(btnX)
						.addComponent(btnBatal))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtJenis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJenisBarang)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNamaBarang))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtJumlah, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKuantitas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHarga))
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
}
