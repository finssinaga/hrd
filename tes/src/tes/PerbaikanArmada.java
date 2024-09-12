package tes;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import com.github.lgooddatepicker.components.DatePickerSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import java.awt.Dimension;
import javafx.geometry.Rectangle2D;

public class PerbaikanArmada extends JPanel {
	private JTextField merkPart;
	private JTextField jumlahPart;
	private JTextField keterangan;
	private JTextField namaBengkel;
	private JTable table;
	private JComboBox jenisArmada;
	private JComboBox nomorPol;
	private JTextField kilometer;
	private DatePicker tanggalPerbaikan;
	private JComboBox jenisPart;
	
	private JComboBox merkArmada;
	private MainMenu mn;
	private JLabel usx;
	private JComboBox merkpartOld;
	private JTextField noseriOld;
	private JTextField tahunpembuatanOld;
	private JTextField tanggalpembelianOld;
	
	/**
	 * @wbp.nonvisual location=448,79
	 */
	private final DatePickerSettings datePickerSettings = new DatePickerSettings();
	private JComboBox driver;

	/**
	 * Create the panel.
	 */
	public PerbaikanArmada(MainMenu mnx) {
		this.mn=mnx;
		this.usx=mn.uslog();
		datePickerSettings.setLocale(new Locale("in", "ID"));
		datePickerSettings.setVisibleNextMonthButton(false);
		datePickerSettings.setVisibleNextYearButton(false);
		datePickerSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Jenis Armada", "Merk Armada", "No. Pol", "Driver", "Kilometer", "Tanggal Perbaikan", "Sparepart yang diganti", "Merk Sparepart", "Jumlah Sparepart", "Nama Bengkel", "Keterangan"
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
		
		jenisPart = new JComboBox();
		
		JLabel lblMerkSparepart = new JLabel("Sparepart Pengganti");
		
		merkPart = new JTextField();
		merkPart.setColumns(10);
		
		jumlahPart = new JTextField();
		jumlahPart.setColumns(10);
		
		keterangan = new JTextField();
		keterangan.setColumns(10);
		
		namaBengkel = new JTextField();
		namaBengkel.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnX = new JButton("X");
		
		JButton btnSimpan = new JButton("Simpan");
		
		JButton btnTambah = new JButton("Tambah");
		
		merkArmada = new JComboBox();
		
		JLabel lblMerkArmada = new JLabel("Merk Armada");
		
		driver = new JComboBox();
		
		JLabel lblDriver = new JLabel("Driver");
		
		merkpartOld = new JComboBox();
		
		noseriOld = new JTextField();
		noseriOld.setEditable(false);
		
		tahunpembuatanOld = new JTextField();
		tahunpembuatanOld.setEditable(false);
		
		tanggalpembelianOld = new JTextField();
		tanggalpembelianOld.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Jenis Part");
		
		JLabel lblMerkPart = new JLabel("Merk Part");
		
		JLabel lblNewLabel_1 = new JLabel("Nomor Seri");
		
		JLabel lblTahunPembuatan = new JLabel("Tahun Pembuatan");
		
		JLabel lblNewLabel_2 = new JLabel("Tanggal Pembelian");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPerbaikanArmada)
							.addPreferredGap(ComponentPlacement.RELATED, 577, Short.MAX_VALUE)
							.addComponent(btnTambah)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSimpan)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnX))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNoPol)
								.addComponent(lblJenisArmada)
								.addComponent(lblKilometer)
								.addComponent(lblTanggalPerbaikan)
								.addComponent(lblSparepartYangDiganti)
								.addComponent(lblMerkSparepart)
								.addComponent(lblJumlahSparepart)
								.addComponent(lblMerkArmada)
								.addComponent(lblKeterangan)
								.addComponent(lblNamaBengkel)
								.addComponent(lblDriver))
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(tanggalPerbaikan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(kilometer, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jenisPart, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(merkPart, Alignment.LEADING)
								.addComponent(jumlahPart, Alignment.LEADING)
								.addComponent(namaBengkel, Alignment.LEADING)
								.addComponent(nomorPol, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(merkArmada, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(keterangan, Alignment.LEADING)
								.addComponent(driver, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jenisArmada, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(merkpartOld, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMerkPart))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(noseriOld, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tahunpembuatanOld, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTahunPembuatan))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(tanggalpembelianOld, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
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
						.addComponent(merkArmada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMerkArmada))
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
						.addComponent(lblNewLabel)
						.addComponent(lblMerkPart)
						.addComponent(lblNewLabel_1)
						.addComponent(lblTahunPembuatan)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSparepartYangDiganti)
						.addComponent(jenisPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(merkpartOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(noseriOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tahunpembuatanOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tanggalpembelianOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(driver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDriver))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(namaBengkel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNamaBengkel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(keterangan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKeterangan))
					.addGap(118)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
		
		
		
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent po) {
				tambah();
			}
		});
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pps) {
				simpan();
			}
		});
		
		jenisPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				String olprt = jenisPart.getSelectedItem().toString();
				merkpartOld.setModel(new DefaultComboBoxModel<>(getold("select nama_barang from master_part where jenis_barang = '"+olprt+"'")));
				
			}
		});
		merkpartOld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mk) {
				String olprt = merkpartOld.getSelectedItem().toString();
				noseriOld.setText(String.join(",",getold("select nomor_seri from master_part where nama_barang = '"+olprt+"'")));
				tahunpembuatanOld.setText(String.join(",",getold("select tahun_pembuatan from master_part where nama_barang = '"+olprt+"'")));
				tanggalpembelianOld.setText(String.join(",",(getold("select tanggal_pembelian from master_part where nama_barang = '"+olprt+"'"))));
			}
		});
		
		jenisArmada.setModel(new DefaultComboBoxModel(armada()));
		jenisArmada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				clrc();
				merkArmada.setModel(new DefaultComboBoxModel(getmerkarmada()));
				
				
			}
		});
		
		merkArmada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ma) {
				nomorPol.setModel(new DefaultComboBoxModel(getnopol()));
			}
		});
		nomorPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent npl) {
				jenisPart.setModel(new DefaultComboBoxModel(getold("select jenis_barang from master_part where c_nopol = '"+nomorPol.getSelectedItem().toString()+"'")));
				driver.setModel(new DefaultComboBoxModel(getdriver()));
			}
		});
		
		

	}
	public String[] getold(String qwery) {
		String[] mode=null;
		String query = qwery;
		mode=SqlUrl.sqlGetSingleRow(query);
		return mode;
	}
	public String[] armada() {
		String[] mode=null;
		String query = "select jenis_armada from master_armada group by jenis_armada";
		mode=SqlUrl.sqlGetSingleRow(query);
		return mode;
	}
	public String[] getnopol() {
		String[] mdl = null;
		String wh = merkArmada.getSelectedItem().toString();
		String query = "select no_pol from master_armada where merk = '"+wh+"'";
		mdl = SqlUrl.sqlGetSingleRow(query);
		return mdl;
	}
	public String[] getmerkarmada() {
		String[] mrk = null;
		String wh = jenisArmada.getSelectedItem().toString();
		String query = "select merk from master_armada where jenis_armada = '"+wh+"' group by merk";
		mrk=SqlUrl.sqlGetSingleRow(query);
		return mrk;
	}
	public String[] getdriver() {
		String[] mrk = null;
		String wh = nomorPol.getSelectedItem().toString();
		String query = "select driver from master_armada where no_pol = '"+wh+"'";
		mrk=SqlUrl.sqlGetSingleRow(query);
		return mrk;
	}
	public void tambah() {
		DefaultTableModel tbh = (DefaultTableModel)table.getModel();
		String merk,merkarm,jumlah,bengkel,ket,jenisarm,nopol,kilo,tgperbaikan,jenspart,drivee;
		merk=merkPart.getText();
		merkarm=merkArmada.getSelectedItem().toString();
		jumlah=jumlahPart.getText();
		bengkel=keterangan.getText();
		ket=namaBengkel.getText();
		jenisarm=jenisArmada.getSelectedItem().toString();
		nopol=nomorPol.getSelectedItem().toString();
		kilo=kilometer.getText();
		tgperbaikan=tanggalPerbaikan.getText();
		jenspart=jenisPart.getSelectedItem().toString();
		drivee=driver.getSelectedItem().toString();
		String[] rdt = {jenisarm,merkarm,nopol,drivee,kilo,tgperbaikan,jenspart,merk,jumlah,bengkel,ket};
		tbh.addRow(rdt);
	}
	public void simpan() {
		DefaultTableModel smp = (DefaultTableModel)table.getModel();
		try {
			Class.forName(SqlUrl.Driver());
			Connection con = DriverManager.getConnection(SqlUrl.url(), SqlUrl.user(), SqlUrl.pass());
			String query = "INSERT INTO `history_perbaikan` (`jenis_armada`, `merk_armada`, `no_pol`, `kilometer`, `tanggal_perbaikan`, `jenis_part`, `merk_part`, `nama_bengkel`, `driver`, `keterangan`,`user`,`jumlah`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = con.prepareStatement(query);
			Object merk,merkarm, jumlah, bengkel, ket, jenisarm, nopol, kilo, tgperbaikan, jenspart,user,drivee;
			for (int i = 0; i < smp.getRowCount(); i++) {
				jenisarm = smp.getValueAt(i, 0);
				merkarm = smp.getValueAt(i, 1);
				nopol = smp.getValueAt(i, 2);
				drivee=smp.getValueAt(i, 3);
				kilo = smp.getValueAt(i, 4);
				tgperbaikan = smp.getValueAt(i, 5);
				jenspart = smp.getValueAt(i, 6);
				merk = smp.getValueAt(i, 7);
				jumlah = smp.getValueAt(i, 8);
				bengkel = smp.getValueAt(i, 9);
				ket = smp.getValueAt(i, 10);
				user=usx.getText();
				prep.setObject(1, jenisarm);
				prep.setObject(2, merkarm);
				prep.setObject(3, nopol);
				prep.setObject(4, kilo);
				prep.setObject(5, tgperbaikan);
				prep.setObject(6, jenspart);
				prep.setObject(7, merk);
				prep.setObject(12, jumlah);
				prep.setObject(8, bengkel);
				prep.setObject(9, drivee);
				prep.setObject(10, ket);
				prep.setObject(11, user);
				prep.execute();
			} 
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public String jam() {
		
		DateTimeFormatter fx = DateTimeFormatter.ofPattern("HH:mm:ss");
		String jam = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(fx);
		
		return jam;
	}
	public void clrc() {
		Component[] pp = this.getComponents();
		for (int i=0;i<pp.length;i++) {
		if(pp[i] instanceof JComboBox) {
			if(((JComboBox)pp[i])!=jenisArmada) {
			((JComboBox)pp[i]).setModel(new DefaultComboBoxModel<>());
		}
			}else if(pp[i] instanceof JTextField) {
				((JTextField)pp[i]).setText("");
			}
		
		}
	}
}
