package tes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.eclipse.birt.core.format.DateFormatter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import jxl.write.DateFormat;

import java.util.Locale;
import java.sql.Time;

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
	private final Time time = new Time(0);

	/**
	 * Create the panel.
	 */
	public MasterPart(MainMenu mn) {
		time.setHours(0);
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
		coln.setColumnIdentifiers(SqlUrl.sqlGetColumn("select `jenis_barang`,`nama_barang`,`nomor_seri`,`tahun_pembuatan`,`tanggal_pembelian`,`jumlah`,`harga` from master_part"));
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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
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
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(thnbuat, 231, 231, 231)
										.addComponent(noseri, 231, 231, 231)
										.addComponent(merk, 231, 231, 231)
										.addComponent(jmlh, 231, 231, 231)
										.addComponent(harga, 231, 231, 231)
										.addComponent(tglbeli, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
										.addComponent(jenis, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(400)
									.addComponent(btnTambah)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSimpan)
									.addGap(50)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMasterSparepart)
							.addPreferredGap(ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
							.addComponent(btnX)
							.addGap(29))))
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
						.addComponent(lblTanggalPembelian)
						.addComponent(tglbeli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTambah)
								.addComponent(btnSimpan)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jmlh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJumlah))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(harga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHargaSatuan))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addGap(12))
		);
		
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
	public void insrow() {
		DefaultTableModel ip = (DefaultTableModel)table.getModel();
		String jens,mrk,no,thn,tgl,jml,hrgsat;
		jens=jenis.getText();
		mrk=merk.getText();
		no=noseri.getText();
		thn=thnbuat.getText();
		tgl=tglbeli.getText()+" "+time;
		jml=jmlh.getText();
		hrgsat=harga.getText();
		String[] rdt = {jens,mrk,no,thn,tgl,jml,hrgsat};
		ip.addRow(rdt);
	}
	public void simpandata() {
		DefaultTableModel up=(DefaultTableModel)table.getModel();
		Object jens,mrk,no,thn,tgl,jml,hrgsat,usr;
		String query = "insert into master_part (`jenis_barang`,`nama_barang`,`nomor_seri`,`tahun_pembuatan`,`tanggal_pembelian`,`jumlah`,`harga`,`nama_user`) values (?,?,?,?,?,?,?,?)";
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
				
				prep.setObject(1, jens);
				prep.setObject(2, mrk);
				prep.setObject(3, no);
				prep.setObject(4, thn);
				prep.setObject(5, tgl);
				prep.setObject(6, jml);
				prep.setObject(7, hrgsat);
				prep.setObject(8, usr);
				prep.execute();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		} finally {
			JOptionPane.showConfirmDialog(null, "Upload Data ?");
		}
	}
}
