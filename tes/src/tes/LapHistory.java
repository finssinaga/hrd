package tes;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JLabel;
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

	/**
	 * Create the panel.
	 */
	public LapHistory() {
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
									.addComponent(btnOk))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(58, Short.MAX_VALUE))
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
						.addComponent(btnOk))
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
					int co=rsmd.getColumnCount();
					col = new String[co];
					for(int i=1;i<co;i++) {
						col[i]=rsmd.getColumnName(i);
					}
					tg.setColumnIdentifiers(col);
					while(res.next()) {
						
					}
					Class.forName(SqlUrl.Driver());
					Connection cons = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					Statement stats = con.createStatement();
					ResultSet ress = stat.executeQuery(query);
					for(int i=1;i<ress.)
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
