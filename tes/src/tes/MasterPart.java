package tes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MasterPart extends JPanel {
	private JTextField thnbuat;
	private JTextField noseri;
	private JTextField merk;
	private JTextField jenis;
	private JTextField tglbeli;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public MasterPart() {
		table = new JTable();
		JLabel lblMasterSparepart = new JLabel("Master SparePart");
		lblMasterSparepart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		thnbuat = new JTextField();
		thnbuat.setColumns(10);
		
		JLabel lblTahunPembuatan = new JLabel("Tahun Pembuatan");
		
		noseri = new JTextField();
		noseri.setColumns(10);
		
		JLabel lblNamaPart = new JLabel("Nomor Seri");
		
		merk = new JTextField();
		merk.setColumns(10);
		
		JLabel lblMerkPart = new JLabel("Merk Part");
		
		jenis = new JTextField();
		jenis.setColumns(10);
		
		JLabel lblJenisPart = new JLabel("Jenis Part");
		
		tglbeli = new JTextField();
		tglbeli.setColumns(10);
		
		JLabel lblTanggalPembelian = new JLabel("Tanggal Pembelian");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnX = new JButton("X");
		
		JButton btnSimpan = new JButton("Simpan");
		DefaultTableModel coln = (DefaultTableModel)table.getModel();
		coln.setColumnIdentifiers(SqlUrl.sqlGetColumn("select * from master_part"));
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel ip = (DefaultTableModel)table.getModel();
				String jens,mrk,no,thn,tgl;
				jens=jenis.getText();
				mrk=merk.getText();
				no=noseri.getText();
				thn=thnbuat.getText();
				tgl=tglbeli.getText();
				String[] rdt = {jens,mrk,no,thn,tgl};
				ip.addRow(rdt);
			}
		});
		
		JLabel lblJumlah = new JLabel("Jumlah");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblHargaSatuan = new JLabel("Harga Satuan");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMasterSparepart)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTahunPembuatan, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTanggalPembelian, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNamaPart, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMerkPart, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblJenisPart, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHargaSatuan)
										.addComponent(lblJumlah))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(tglbeli)
										.addComponent(thnbuat)
										.addComponent(noseri)
										.addComponent(merk)
										.addComponent(textField)
										.addComponent(textField_1)
										.addComponent(jenis, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
							.addComponent(btnX)
							.addGap(8))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(400)
							.addComponent(btnTambah)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSimpan)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMasterSparepart)
						.addComponent(btnX))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jenis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJenisPart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(merk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMerkPart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(noseri, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNamaPart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTahunPembuatan)
						.addComponent(thnbuat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbeli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTanggalPembelian))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTambah)
								.addComponent(btnSimpan)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJumlah))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHargaSatuan))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addGap(12))
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

}
