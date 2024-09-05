package tes;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

public class MasterStok extends JPanel {
	private JTable table;
	private MainMenu m;
	private JButton cls;

	/**
	 * Create the panel.
	 */
	public MasterStok(MainMenu m) {
		this.m = m;
		this.cls = m.btX();
		
		table = new JTable();
		
		JLabel lblTambahStokBarang = new JLabel("STOK BARANG");
		
		JButton btnBaru = new JButton("Baru");
		btnBaru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel baru = (DefaultTableModel)table.getModel();
				String query = "select * from `stok_barang`";
				baru.setColumnIdentifiers(SqlUrl.sqlGetColumn(query));
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnSimpan = new JButton("Simpan");
		
		JButton btnBatal = new JButton("Batal");
		
		JButton btnX = new JButton("x");
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTambahStokBarang, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBaru)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSimpan)
							.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
							.addComponent(btnBatal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnX))
						.addComponent(table, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTambahStokBarang)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBaru)
						.addComponent(btnEdit)
						.addComponent(btnSimpan)
						.addComponent(btnX)
						.addComponent(btnBatal))
					.addGap(18)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(153))
		);
		setLayout(groupLayout);

	}
}
