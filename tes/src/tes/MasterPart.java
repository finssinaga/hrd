package tes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.eclipse.birt.core.format.DateFormatter;
import org.eclipse.birt.report.model.api.elements.structures.DateTimeFormatValue;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import jxl.write.DateFormat;

import java.util.Locale;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import javax.swing.JComboBox;

public class MasterPart extends JPanel {
	private JTextField thnbuat;
	private JTextField noseri;
	private JTextField merk;
	private JTextField jenis;
	private JTable table;
	private JTextField jmlh;
	private JTextField harga;
	private JLabel us;
	private MainMenu mn;
	private JButton btnX;
	private DatePicker tglbeli;
	/**
	 * @wbp.nonvisual location=38,319
	 */
	private final DatePickerSettings datePickerSettings = new DatePickerSettings();
	/**
	 * @wbp.nonvisual location=143,329
	 */
	private LocalTime time = LocalTime.now();
	
	private JComboBox nopol;

	/**
	 * Create the panel.
	 */
	public MasterPart(MainMenu mn) {
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("HH-mm-ss");
		time.format(fm);
		datePickerSettings.setLocale(new Locale("in", "ID"));
		datePickerSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
		this.mn=mn;
		this.btnX=mn.btX();
		this.us=mn.uslog();
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
		
		JLabel lblTanggalPembelian = new JLabel("Tanggal Pembelian");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.btX().doClick();
			}
		});
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				simpandata();
			}
		});
		DefaultTableModel coln = (DefaultTableModel)table.getModel();
		coln.setColumnIdentifiers(SqlUrl.sqlGetColumn("select `jenis_barang`,`nama_barang`,`nomor_seri`,`tahun_pembuatan`,`tanggal_pembelian`,`jumlah`,`harga`,`c_nopol` from master_part"));
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insrow();
			}
		});
		
		JLabel lblJumlah = new JLabel("Jumlah");
		
		jmlh = new JTextField();
		jmlh.setColumns(10);
		
		harga = new JTextField();
		harga.setColumns(10);
		
		JLabel lblHargaSatuan = new JLabel("Harga Satuan");
		
		tglbeli=new DatePicker();
		tglbeli.setSettings(datePickerSettings);
		
		nopol = new JComboBox();
		nopol.setEditable(true);
		nopol.setModel(new DefaultComboBoxModel(cbmod()));
		
		JLabel lblNoPol = new JLabel("Lokasi Part");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMasterSparepart)
							.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
							.addComponent(btnTambah)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSimpan)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnX)
							.addGap(29))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTahunPembuatan, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTanggalPembelian, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNamaPart, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMerkPart, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJenisPart, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHargaSatuan)
								.addComponent(lblJumlah)
								.addComponent(lblNoPol))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(thnbuat, 231, 231, Short.MAX_VALUE)
								.addComponent(noseri, 231, 231, Short.MAX_VALUE)
								.addComponent(merk, 231, 231, Short.MAX_VALUE)
								.addComponent(jmlh, 231, 231, Short.MAX_VALUE)
								.addComponent(harga, 231, 231, Short.MAX_VALUE)
								.addComponent(tglbeli, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(jenis, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(nopol))
							.addContainerGap(210, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMasterSparepart)
						.addComponent(btnX)
						.addComponent(btnTambah)
						.addComponent(btnSimpan))
					.addGap(11)
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
						.addComponent(lblTanggalPembelian)
						.addComponent(tglbeli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jmlh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJumlah))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(harga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHargaSatuan))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nopol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNoPol))
					.addGap(99)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE)
					.addGap(12))
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
	public void insrow() {
		DefaultTableModel ip = (DefaultTableModel)table.getModel();
		String jens,mrk,no,thn,tgl,jml,hrgsat,npol;
		jens=jenis.getText();
		mrk=merk.getText();
		no=noseri.getText();
		thn=thnbuat.getText();
		tgl=tglbeli.getText();
		jml=jmlh.getText();
		hrgsat=harga.getText();
		npol=nopol.getSelectedItem().toString();
		String[] rdt = {jens,mrk,no,thn,tgl,jml,hrgsat,npol};
		ip.addRow(rdt);
	}
	public void simpandata() {
		DefaultTableModel up=(DefaultTableModel)table.getModel();
		Object jens,mrk,no,thn,tgl,jml,hrgsat,usr,cnop;
		String query = "insert into master_part (`jenis_barang`,`nama_barang`,`nomor_seri`,`tahun_pembuatan`,`tanggal_pembelian`,`jumlah`,`harga`,`nama_user`,`c_nopol`) values (?,?,?,?,?,?,?,?,?)";
		try {
			Class.forName(SqlUrl.Driver());

			Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
			PreparedStatement prep = con.prepareStatement(query);
			for (int i=0;i<up.getRowCount();i++) {
				jens=up.getValueAt(i, 0);
				mrk=up.getValueAt(i, 1);
				no=up.getValueAt(i, 2);
				thn=up.getValueAt(i, 3);
				tgl=up.getValueAt(i, 4);
				jml=up.getValueAt(i, 5);
				hrgsat=up.getValueAt(i, 6);
				usr=us.getText();
				cnop=up.getValueAt(i, 8);
				
				prep.setObject(1, jens);
				prep.setObject(2, mrk);
				prep.setObject(3, no);
				prep.setObject(4, thn);
				prep.setObject(5, tgl);
				prep.setObject(6, jml);
				prep.setObject(7, hrgsat);
				prep.setObject(8, usr);
				prep.setObject(9, cnop);
				prep.execute();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public String[] cbmod() {
		String cnopol = "select no_pol from master_armada";
		String[] cnp=null;
		int rowc = SqlUrl.sqlGetRowCount(cnopol);
		try {
			Class.forName(SqlUrl.Driver());
			Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(cnopol);
			int i =0;
			cnp=new String[rowc];
			while(res.next()) {
				cnp[i]=res.getString(1);
				i++;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return cnp;
	}
}
