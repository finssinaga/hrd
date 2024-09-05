package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InputData extends JPanel {

	
	private JTable table;
	/**
	 * @wbp.nonvisual location=328,259
	 */
	
	private final DatePickerSettings datePickerSettings = new DatePickerSettings();
	private JTextField noPlat;
	private JTextField namaPart;
	private JTextField merkPart;
	private JTextField nmUser;
	private JTextField txtKet;
	private MainMenu mn;
	private JButton cls;
	
	public InputData(MainMenu mn) {
		this.mn = mn;
		this.cls = mn.btX();
		DatePicker datePicker = new DatePicker();
		datePicker.setSettings(datePickerSettings);
		JTextField datadate = datePicker.getComponentDateTextField();
		
		datePickerSettings.setVisibleNextMonthButton(false);
		datePickerSettings.setVisibleNextYearButton(false);
		datePickerSettings.setAllowKeyboardEditing(false);
		datePickerSettings.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
		
		noPlat = new JTextField();
		noPlat.setColumns(10);		
		namaPart = new JTextField();
		namaPart.setColumns(10);		
		merkPart = new JTextField();
		merkPart.setColumns(10);		
		nmUser = new JTextField();
		nmUser.setColumns(10);		
		txtKet = new JTextField();
		txtKet.setColumns(10);
		
		JLabel lblTanggal = new JLabel("Tanggal :");		
		JLabel lblNamaPart = new JLabel("Nama Part :");		
		JLabel lblMerkPart = new JLabel("Merk Part :");		
		JLabel lblNoPlat = new JLabel("No. Plat :");		
		JLabel lblNamaUser = new JLabel("Nama User :");		
		JLabel lblKeterangan = new JLabel("Keterangan :");
		
		table = new JTable();
		sql();
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		JButton btnTambah = new JButton("Tambah");
		JButton btnCancel = new JButton("Cancel");
				
		JScrollPane scrollPane = new JScrollPane();
		
		
		btnTambah.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				String tanggal,plat,namapart,merkpart,user,ket;
				 tanggal = datadate.getText();
				 plat = noPlat.getText();
				 namapart = namaPart.getText();
				 merkpart = merkPart.getText();
				 user = nmUser.getText();
				 ket = txtKet.getText();
				 String[] rowData = {tanggal, plat, namapart, merkpart, user, ket};
				DefaultTableModel edit =(DefaultTableModel)table.getModel();
				edit.addRow(rowData);
				
				noPlat.setText(null);
				namaPart.setText(null);
				merkPart.setText(null);
				nmUser.setText(null);
				txtKet.setText(null);
				
				int rowc = edit.getRowCount();
				if (rowc<1) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
		});
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tanggal,jeniskend,namapart,merkpart,user,keterangan;
				try {
					DefaultTableModel insert = (DefaultTableModel)table.getModel();
					
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					for (int i=0; i<insert.getRowCount();i++) {
						tanggal = insert.getValueAt(i, 0).toString();
						jeniskend = insert.getValueAt(i, 1).toString();
						namapart = insert.getValueAt(i, 2).toString();
						merkpart = insert.getValueAt(i, 3).toString();
						user = insert.getValueAt(i, 4).toString();
						keterangan = insert.getValueAt(i, 5).toString();
						
						String query = "insert into hrds (`tanggal`, `jeniskend`, `namapart`, `merkpart`, `user`, `keterangan`)values (?,?,?,?,?,?)";
						PreparedStatement prep = con.prepareStatement(query);
						prep.setObject(1, tanggal);
						prep.setString(2, jeniskend);
						prep.setString(3, namapart);
						prep.setString(4, merkpart);
						prep.setString(5, user);
						prep.setString(6, keterangan);
						
						prep.execute();
						prep.close();
						}
					con.close();
					
					}
				catch (SQLException ins) {
					String errtx = ins.toString();
					JOptionPane.showMessageDialog(scrollPane, "SQL error");
					//ErrorInput(errtx);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
			}
		);
			
		

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel canc = (DefaultTableModel)table.getModel();
				canc.setRowCount(0);
				int rowc = canc.getRowCount();
				if (rowc<1) {
					btnSave.setEnabled(false);
					
				}else {
					btnSave.setEnabled(true);
				}
			}
		});
		
		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cls.doClick();
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
							.addGap(49))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNamaUser)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblTanggal)
												.addComponent(lblMerkPart)
												.addComponent(lblNamaPart)
												.addComponent(lblNoPlat))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(merkPart, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
													.addGap(11))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(namaPart, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
													.addGap(11))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(noPlat, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
													.addGap(11))
												.addComponent(datePicker, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
											.addGap(26)
											.addComponent(lblKeterangan)))
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnTambah)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSave)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancel)
									.addGap(71)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnX, Alignment.TRAILING)
								.addComponent(txtKet, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(nmUser, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
							.addGap(65)))
					.addGap(0))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTambah)
						.addComponent(btnSave)
						.addComponent(btnX)
						.addComponent(btnCancel))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(nmUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNamaUser))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtKet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblKeterangan)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTanggal))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(noPlat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNoPlat))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(namaPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNamaPart))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(merkPart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMerkPart))))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addGap(36))
		);
		
		scrollPane.setViewportView(table);
		this.setLayout(gl_panel);
		
		
		
	}
public void sql() {
	try {
		
		Class.forName(SqlUrl.Driver());
		Connection co = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
		Statement stats = co.createStatement();
		String query = SqlUrl.dbms();
		ResultSet res = stats.executeQuery(query);
		ResultSetMetaData rsmd = res.getMetaData();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		int col=rsmd.getColumnCount();
		String[] colname=new String[col];
		
		for (int i=0;i<col;i++) {
			colname[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colname);
		}
		stats.close();
		co.close();
	}
		catch (Exception e1) {
			e1.printStackTrace();}
		}

}

