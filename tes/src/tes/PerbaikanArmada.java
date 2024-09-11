package tes;

import javax.swing.JPanel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.util.Locale;

public class PerbaikanArmada extends JPanel {
	private JTextField merkPart;
	private JTextField jumlahPart;
	private JTextField namaBengkel;
	private JTextField keterangan;
	private JTable table;
	private JComboBox jenisArmada;
	private JComboBox nomorPol;
	private JTextField kilometer;
	private DatePicker tanggalPerbaikan;
	/**
	 * @wbp.nonvisual location=448,79
	 */
	private final DatePickerSettings datePickerSettings = new DatePickerSettings();

	/**
	 * Create the panel.
	 */
	public PerbaikanArmada() {
		datePickerSettings.setLocale(new Locale("in", "ID"));
		datePickerSettings.setVisibleNextMonthButton(false);
		datePickerSettings.setVisibleNextYearButton(false);
		datePickerSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Jenis Armada", "No. Pol", "Kilometer", "Tanggal Perbaikan", "Sparepart yang diganti", "Merk Sparepart", "Jumlah Sparepart", "Nama Bengkel", "Keterangan"
			}
		));
		JLabel lblPerbaikanArmada = new JLabel("PERBAIKAN ARMADA");
		lblPerbaikanArmada.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblJenisArmada = new JLabel("Jenis Armada");
		
		JLabel lblNoPol = new JLabel("No. Pol");
		
		JLabel lblKilometer = new JLabel("Kilometer");
		
		JLabel lblTanggalPerbaikan = new JLabel("Tanggal Perbaikan");
		
		JLabel lblSparepartYangDiganti = new JLabel("SparePart yang Diganti");
		
		JLabel lblJumlahSparepart = new JLabel("Jumlah Sparepart");
		
		JLabel lblNamaBengkel = new JLabel("Nama Bengkel");
		
		JLabel lblKeterangan = new JLabel("Keterangan");
		
		jenisArmada = new JComboBox();
		
		nomorPol = new JComboBox();
		
		kilometer = new JTextField();
		
		tanggalPerbaikan = new DatePicker();
		tanggalPerbaikan.setSettings(datePickerSettings);
		
		JComboBox jenisPart = new JComboBox();
		
		JLabel lblMerkSparepart = new JLabel("Merk Sparepart");
		
		merkPart = new JTextField();
		merkPart.setColumns(10);
		
		jumlahPart = new JTextField();
		jumlahPart.setColumns(10);
		
		namaBengkel = new JTextField();
		namaBengkel.setColumns(10);
		
		keterangan = new JTextField();
		keterangan.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnX = new JButton("X");
		
		JButton btnSimpan = new JButton("Simpan");
		
		JButton btnTambah = new JButton("Tambah");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPerbaikanArmada)
									.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
									.addComponent(btnTambah))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNoPol)
										.addComponent(lblJenisArmada)
										.addComponent(lblKilometer)
										.addComponent(lblTanggalPerbaikan)
										.addComponent(lblSparepartYangDiganti)
										.addComponent(lblMerkSparepart)
										.addComponent(lblJumlahSparepart)
										.addComponent(lblNamaBengkel)
										.addComponent(lblKeterangan))
									.addGap(43)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(nomorPol, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(tanggalPerbaikan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(kilometer, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jenisPart, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(merkPart)
										.addComponent(jumlahPart)
										.addComponent(namaBengkel)
										.addComponent(keterangan)
										.addComponent(jenisArmada, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSimpan)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnX)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerbaikanArmada)
						.addComponent(btnX)
						.addComponent(btnSimpan)
						.addComponent(btnTambah))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJenisArmada)
						.addComponent(jenisArmada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNoPol)
						.addComponent(nomorPol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKilometer)
						.addComponent(kilometer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTanggalPerbaikan)
						.addComponent(tanggalPerbaikan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSparepartYangDiganti)
						.addComponent(jenisPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMerkSparepart)
						.addComponent(merkPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJumlahSparepart)
						.addComponent(jumlahPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNamaBengkel)
						.addComponent(namaBengkel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKeterangan)
						.addComponent(keterangan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(30))
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
		
		
		
		
		
		jenisArmada.setModel(new DefaultComboBoxModel(armada()));
		jenisArmada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				nomorPol.setModel(new DefaultComboBoxModel(getnopol()));
			}
		});
		jenisPart.setModel(new DefaultComboBoxModel(getpart()));
		
		

	}
	public String[] armada() {
		String[] mode=null;
		String query = "select jenis_armada from master_armada group by jenis_armada";
		mode=SqlUrl.sqlGetSingleRow(query);
		return mode;
	}
	public String[] getnopol() {
		String[] mdl = null;
		String wh = jenisArmada.getSelectedItem().toString();
		String query = "select no_pol from master_armada where jenis_armada = '"+wh+"'";
		mdl = SqlUrl.sqlGetSingleRow(query);
		return mdl;
	}
	public String[] getpart() {
		String[] prt = null;
		String query="select jenis_barang from master_part group by jenis_barang";
		prt=SqlUrl.sqlGetSingleRow(query);
		return prt;
	}
	public void tambah() {
		DefaultTableModel tbh = (DefaultTableModel)table.getModel();
		String merk,jumlah,bengkel,ket,jenisarm,nopol,kilo,tgperbaikan;
		merk=merkPart.getText();
		jumlah=jumlahPart.getText();
		bengkel=namaBengkel.getText();
		ket=keterangan.getText();
		jenisarm=jenisArmada.getSelectedItem().toString();
		nopol=nomorPol.getSelectedItem().toString();
		kilo=kilometer.getText();
		tgperbaikan=tanggalPerbaikan.getText();
		
		String[] rdt = {jenisarm,nopol,kilo,tgperbaikan,
	}
}
