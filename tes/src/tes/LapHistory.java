package tes;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.awt.Font;
import java.time.format.DateTimeFormatter;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import org.eclipse.datatools.connectivity.oda.OdaException;

import com.github.lgooddatepicker.components.DatePickerSettings;
import java.sql.ResultSetMetaData;

import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.sql.ResultSet;

public class LapHistory extends JPanel {
	/**
	 * @wbp.nonvisual location=38,299
	 */
	private final DatePickerSettings datePickerSettings = new DatePickerSettings();
	/**
	 * @wbp.nonvisual location=158,299
	 */
	private final DatePickerSettings datePickerSettings_1 = new DatePickerSettings();
	private JTable table;
	private MainMenu mn;
	private JButton xt;

	/**
	 * Create the panel.
	 */
	public LapHistory(MainMenu mn) {
		this.mn=mn;
		this.xt=mn.btX();
		datePickerSettings.setLocale(new Locale("in", "ID"));
		datePickerSettings.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
		datePickerSettings.setVisibleNextMonthButton(false);
		datePickerSettings.setVisibleNextYearButton(false);
		
		datePickerSettings_1.setLocale(new Locale("in", "ID"));
		datePickerSettings_1.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
		datePickerSettings_1.setVisibleNextMonthButton(false);
		datePickerSettings_1.setVisibleNextYearButton(false);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setSettings(datePickerSettings);
		DatePicker datePicker_1 = new DatePicker();
		datePicker_1.setSettings(datePickerSettings_1);
		JLabel lblLaporanHistory = new JLabel("LAPORAN HISTORY");
		lblLaporanHistory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblDari = new JLabel("Dari :");
		
		JLabel lblSampai = new JLabel("Sampai : ");
		
		JButton btnOk = new JButton("OK");
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mn.btX().doClick();
			}
		});
		
		JButton btnPrint = new JButton("print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					SqlUrl.importToXML(table, "History");
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SqlUrl.runReport("HistoryReport");
				try {
					Desktop.getDesktop().open(new File(SqlUrl.confloader("server.outputdir")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLaporanHistory))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDari))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSampai)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(datePicker_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnOk)
									.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
									.addComponent(btnPrint)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnX))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLaporanHistory)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDari)
						.addComponent(lblSampai))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(datePicker_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOk)
						.addComponent(btnX)
						.addComponent(btnPrint))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tg = (DefaultTableModel)table.getModel();
				tg.setRowCount(0);
				String dari = datePicker.getDate().toString();
				String sampai = datePicker_1.getDate().toString();
				String query = "select * from `history` where `tanggal` between '"+dari+"' and '"+sampai+"'";
				String que = "select * from history";
				String[] col=null;
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					Statement stat = con.createStatement();
					ResultSet res = stat.executeQuery(que);
					ResultSetMetaData rsmd = res.getMetaData();
					int co=rsmd.getColumnCount()-1;
					JOptionPane.showMessageDialog(null, co);
					col = new String[co];
					for(int i=0;i<co;i++) {
						col[i]=rsmd.getColumnName(i+2);
					}
					tg.setColumnIdentifiers(col);
					while(res.next()) {
						
					}
					Class.forName(SqlUrl.Driver());
					Connection cons = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					Statement stats = cons.createStatement();
					ResultSet ress = stats.executeQuery(query);
					Object tanggal = null,jenis = null,nama = null,user = null,jumlah = null,harga = null;
					while(ress.next()) {
						tanggal=ress.getObject(2);
						jenis=ress.getObject(3);
						nama=ress.getObject(4);
						user=ress.getObject(5);
						jumlah=ress.getObject(6);
						harga=ress.getObject(7);
						
					}
					Object[] rdt= {tanggal,jenis,nama,user,jumlah,harga};
					tg.addRow(rdt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
