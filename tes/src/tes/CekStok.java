package tes;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.eclipse.datatools.connectivity.oda.design.CustomData;

import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class CekStok extends JPanel {
	private JTable table;
	private MainMenu mn;
	private JButton btX;

	/**
	 * Create the panel.
	 */
	public CekStok(MainMenu mn) {
		this.mn = mn;
		this.btX=mn.btX();
		JLabel lblCekStok = new JLabel("CEK STOK");
		lblCekStok.setFont(new Font("Tahoma", Font.BOLD, 20));
		table = new JTable();
		JLabel lblJenis = new JLabel("Jenis");
		JButton btnCari = new JButton("Cari");
		
		
		JComboBox comboBox = new JComboBox();
		String[] list = null;
		try {
			Class.forName(SqlUrl.Driver());
			Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
			Statement stat = con.createStatement();
			String count = "select count(`jenis_barang`) as rocoun from `stok_barang` group by `jenis_barang`";
			ResultSet cown = stat.executeQuery(count);
			int i =0 ;
			while (cown.next()) {
				i++;
			}
			list = new String[i];
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Class.forName(SqlUrl.Driver());
			Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
			Statement stat = con.createStatement();
			String quer = "select `jenis_barang` from `stok_barang` group by `jenis_barang`";
			ResultSet res = stat.executeQuery(quer);
			int ro=list.length;
			for (int i=0;i<ro;i++) {
				res.next();
				list[i]=res.getString(1);
			}
			comboBox.setModel(new DefaultComboBoxModel<>(list));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel ck = (DefaultTableModel)table.getModel();
				ck.setRowCount(0);
				String jen = comboBox.getSelectedItem().toString();
				String query = "select * from `stok_barang` where `jenis_barang` = '"+jen+"'";
				String namabrg = null;
				String jumlah = null;
				String col = "select `nama_barang`,`jumlah` from `stok_barang`";
				String[] nmcol = SqlUrl.sqlGetColumn(col);
				ck.setColumnIdentifiers(nmcol);
				try {
					Class.forName(SqlUrl.Driver());
					Connection con = DriverManager.getConnection(SqlUrl.url(),SqlUrl.user(),SqlUrl.pass());
					Statement stat = con.createStatement();
					ResultSet res = stat.executeQuery(query);
					while(res.next()) {
						namabrg=res.getString(3);
						jumlah = res.getString(5);
						Object[] data = {namabrg,jumlah};
						ck.addRow(data);
					}
				}catch (ClassNotFoundException | SQLException er) {
					er.printStackTrace();
				}
				//PrintPrev.exportTableToPDF("tessss.pdf", table);
			}
		});
		
		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mn.btX().doClick();
			}
		});
		
		JButton btnPrint = new JButton("print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
			            // Initialize BIRT Engine
			            EngineConfig config = new EngineConfig();
			            IReportEngine engine = new ReportEngine(config);

			            // Create a report task
			            IReportRunnable reportRunnable = engine.openReportDesign(System.getProperty("user.dir")+"\\src\\report\\report.rptdesign");
			            IRunAndRenderTask task = engine.createRunAndRenderTask(reportRunnable);

			            // Set parameter values
			            TableModel model = table.getModel();
			            CustomDataSource dataSrc = new CustomDataSource(SqlUrl.tblConvert(table.getModel()));
			            task.setParameterValue("datasource",dataSrc);

			            // Define render options
			            HTMLRenderOption options = new HTMLRenderOption();
			            options.setOutputFileName("output/report.html");
			            options.setOutputFormat("html");
			            task.setRenderOption(options);

			            // Run the report
			            task.run();
			            task.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			}
				
			});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblCekStok)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblJenis)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCari)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPrint)
					.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
					.addComponent(btnX)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCekStok)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJenis)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCari)
						.addComponent(btnX)
						.addComponent(btnPrint))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addGap(57))
		);
		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
	}
}
