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
import java.awt.Font;

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

	/**
	 * Create the panel.
	 */
	public MasterStok(MainMenu m) {
		this.m = m;
		this.cls = m.btX();
		table = new JTable();
		
		
		JLabel lblTambahStokBarang = new JLabel("STOK BARANG");
		lblTambahStokBarang.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTambahStokBarang.setBackground(SystemColor.textHighlight);
		
		JButton btnBaru = new JButton("+");
		
		JButton btnEdit = new JButton("Hapus");
		
		JButton btnSimpan = new JButton("Simpan");
		
		JButton btnBatal = new JButton("Batal");
		
		JButton btnX = new JButton("x");
		
		
		
		
		
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
				
				if (cnsl.getRowCount()<1) {
					btnSimpan.setEnabled(false);
					btnEdit.setEnabled(false);
				}
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
					inp.setRowCount(0);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(btnSimpan, e);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(btnSimpan, "Success");
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		
		txtJenis = new JTextField();
		txtJenis.setColumns(10);
		
		txtNama = new JTextField();
		txtNama.setColumns(10);
		
		txtJumlah = new JTextField();
		txtJumlah.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		
		JLabel lblJenisBarang = new JLabel("Jenis Barang");
		
		JLabel lblNamaBarang = new JLabel("Nama Barang");
		
		JLabel lblKuantitas = new JLabel("Kuantitas");
		
		JLabel lblHarga = new JLabel("Harga");
		
		
		
		
		btnBaru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usl = Logon.getlg().getUser();
				DefaultTableModel baru = (DefaultTableModel)table.getModel();
				String query = "select `jenis_barang`,`nama_barang`, `nama_user`, `jumlah`,`harga` from `stok_barang`";
				baru.setColumnIdentifiers(SqlUrl.sqlGetColumn(query));
				String jenis,namabrg;
				int jumlah,harga;
				jenis=txtJenis.getText();
				namabrg=txtNama.getText();
				jumlah=Integer.parseInt(txtJumlah.getText());
				harga=Integer.parseInt(txtHarga.getText());
				Object[] dta = {jenis,namabrg,usl,jumlah,harga};
				
				baru.insertRow(0, dta);
				txtHarga.setText(null);
				txtJenis.setText(null);
				txtJumlah.setText(null);
				txtNama.setText(null);
				
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel del= (DefaultTableModel)table.getModel();
				int rdel = table.getSelectedRow();
				del.removeRow(rdel);
			}
			
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBaru)
								.addComponent(lblJenisBarang)
								.addComponent(lblNamaBarang)
								.addComponent(lblKuantitas)
								.addComponent(lblHarga))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtHarga)
								.addComponent(txtJumlah)
								.addComponent(txtNama)
								.addComponent(txtJenis)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEdit)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSimpan)))
							.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
							.addComponent(btnBatal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnX))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTambahStokBarang, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(lblJenisBarang))
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
}
