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
import javax.swing.table.TableModel;
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
import java.util.Vector;
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
	private DatePicker tanggalpembelianNew;
	
	/**
	 * @wbp.nonvisual location=448,79
	 */
	private final DatePickerSettings datePickerSettings = new DatePickerSettings();
	private JComboBox driver;
	private JTextField merkpartNew;
	private JTextField noseriNew;
	private JTextField tahunpembuatanNew;
	private JTable tblupload;
	private JTextField hargaOld;
	private JTextField hargaNew;

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
		merkPart.setEditable(false);
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
		
		JLabel lblJenisPart = new JLabel("Jenis Part");
		
		JLabel lblMerkPart_1 = new JLabel("Merk Part");
		
		merkpartNew = new JTextField();
		merkpartNew.setColumns(10);
		
		noseriNew = new JTextField();
		noseriNew.setColumns(10);
		
		JLabel lblNomorSeri = new JLabel("Nomor Seri");
		
		tahunpembuatanNew = new JTextField();
		tahunpembuatanNew.setColumns(10);
		
		JLabel lblTahunPembuatan_1 = new JLabel("Tahun Pembuatan");
		
		JLabel lblTanggalPembelian = new JLabel("Tanggal Pembelian");
		
		tanggalpembelianNew = new DatePicker();
		
		hargaOld = new JTextField();
		hargaOld.setEditable(false);
		hargaOld.setColumns(10);
		
		JLabel lblHargaSatuan = new JLabel("Harga Satuan");
		
		hargaNew = new JTextField();
		hargaNew.setColumns(10);
		
		JLabel lblHargaSatuan_1 = new JLabel("Harga Satuan");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPerbaikanArmada)
							.addPreferredGap(ComponentPlacement.RELATED, 601, Short.MAX_VALUE)
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
								.addComponent(lblMerkArmada)
								.addComponent(lblMerkSparepart)
								.addComponent(lblJumlahSparepart)
								.addComponent(lblDriver)
								.addComponent(lblNamaBengkel)
								.addComponent(lblKeterangan))
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tanggalPerbaikan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(kilometer)
								.addComponent(nomorPol, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(merkArmada, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jenisArmada, 0, 151, Short.MAX_VALUE)
								.addComponent(lblJenisPart)
								.addComponent(namaBengkel, 151, 151, 151)
								.addComponent(jumlahPart, 151, 151, 151)
								.addComponent(merkPart, 151, 151, 151)
								.addComponent(lblNewLabel)
								.addComponent(keterangan, 151, 151, 151)
								.addComponent(driver, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(jenisPart, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(merkpartNew, Alignment.LEADING)
									.addComponent(lblMerkPart, Alignment.LEADING)
									.addComponent(merkpartOld, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMerkPart_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(noseriNew)
									.addComponent(noseriOld, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1))
								.addComponent(lblNomorSeri))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(tahunpembuatanNew)
									.addComponent(tahunpembuatanOld, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
									.addComponent(lblTahunPembuatan))
								.addComponent(lblTahunPembuatan_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblTanggalPembelian)
								.addComponent(lblNewLabel_2)
								.addComponent(tanggalpembelianOld, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(tanggalpembelianNew, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHargaSatuan_1)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblHargaSatuan)
									.addComponent(hargaOld, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
									.addComponent(hargaNew)))))
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
					.addGap(42))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSparepartYangDiganti)
								.addComponent(jenisPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblJenisPart)
							.addGap(3)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(merkPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMerkSparepart)
								.addComponent(merkpartNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(noseriNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tahunpembuatanNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tanggalpembelianNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(hargaNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jumlahPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJumlahSparepart))
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
								.addComponent(lblKeterangan)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMerkPart)
								.addComponent(lblNewLabel_1)
								.addComponent(lblTahunPembuatan)
								.addComponent(lblNewLabel_2)
								.addComponent(lblHargaSatuan))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(merkpartOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(noseriOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tahunpembuatanOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tanggalpembelianOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(hargaOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMerkPart_1)
								.addComponent(lblNomorSeri)
								.addComponent(lblTahunPembuatan_1)
								.addComponent(lblTanggalPembelian)
								.addComponent(lblHargaSatuan_1))))
					.addGap(42)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
		);
		
		tblupload = new JTable();
		scrollPane_1.setViewportView(tblupload);
		tblupload.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"namauser", "jenis part", "merk part", "no seri", "tahun pembuatan", "tgl pembelian", "jumlah", "harga satuan", "c_nopol"
			}
		));
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
		
		
		
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent po) {
				cljtext();
				tambah();
				updatetbstok();
				
				updatestoksql();
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
				merkPart.setText(jenisPart.getSelectedItem().toString());
			}
		});
		merkpartOld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mk) {
				String olprt = merkpartOld.getSelectedItem().toString();
				noseriOld.setText(String.join(",",getold("select nomor_seri from master_part where nama_barang = '"+olprt+"'")));
				tahunpembuatanOld.setText(String.join(",",getold("select tahun_pembuatan from master_part where nama_barang = '"+olprt+"'")));
				tanggalpembelianOld.setText(String.join(",",(getold("select tanggal_pembelian from master_part where nama_barang = '"+olprt+"'"))));
				hargaOld.setText(String.join(",", getold("select harga from master_part where nama_barang='"+olprt+"'")));
				merkPart.setText(jenisPart.getSelectedItem().toString());
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
		merk=merkpartNew.getText();
		merkarm=merkArmada.getSelectedItem().toString();
		jumlah=jumlahPart.getText();
		ket=keterangan.getText();
		bengkel=namaBengkel.getText();
		jenisarm=jenisArmada.getSelectedItem().toString();
		nopol=nomorPol.getSelectedItem().toString();
		kilo=kilometer.getText();
		tgperbaikan=tanggalPerbaikan.getText();
		jenspart=merkpartOld.getSelectedItem().toString();
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
		}finally {
			JOptionPane.showMessageDialog(null, "SUCCESS");
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
	public void cljtext() {
		Component[] p=this.getComponents();
		for (int i=0;i<p.length;i++) {
			if(p[i] instanceof JTextField) {
				((JTextField)p[i]).setText("aa");
			}
		}
	}
	
	public void updatetbstok() {
		DefaultTableModel tbup = (DefaultTableModel)tblupload.getModel();
		String usr,jens,merk,noseri,thnbuat,tglbeli,jml,harga,cnopol;
		usr=mn.uslog().getText();
		jens=jenisPart.getSelectedItem().toString();
		merk=merkpartNew.getText();
		noseri=noseriNew.getText();
		thnbuat=tahunpembuatanNew.getText();
		tglbeli=tanggalpembelianNew.getDateStringOrEmptyString();
		jml=jumlahPart.getText();
		harga= hargaNew.getText();
		cnopol=nomorPol.getSelectedItem().toString();
		String[] rdata= {usr,jens,merk,noseri,thnbuat,tglbeli,jml,harga,cnopol};
		tbup.addRow(rdata);
	}
	public void updatestoksql() {
		Object[] pp = getTableData(tblupload);
		JOptionPane.showMessageDialog(null, pp.length);
	}
	public Object[] getTableData (JTable tables) {
	    TableModel dtm = tables.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[] tableData = new Object[nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[j] = dtm.getValueAt(i,j);
	    return tableData;
	}
}
