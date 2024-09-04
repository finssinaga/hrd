package tes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import org.apache.commons.lang3.ArrayUtils;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Insets;
import javax.swing.table.DefaultTableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class MainMenu extends JFrame {
	private JPanel panel;
	private JMenuItem mntmInputData;
	private JMenuBar menuBar;
	private JPanel opds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public MainMenu() {
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(2, 15, 2, 0));
		setJMenuBar(menuBar);
		
		JMenu mnMaster = new JMenu("master");
		menuBar.add(mnMaster);
		
		OpenData opd = new OpenData();
		JPanel opds =((OpenData) opd).getOpenData();
		
		JMenuItem mntmTable = new JMenuItem("table");
		mntmTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GroupLayout groupLayout = new GroupLayout(getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(opds, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(opds, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
				);
				getContentPane().setLayout(groupLayout);
				
				getContentPane().add(opds);
				opds.setVisible(true);
			}
		});
		
		
		mnMaster.add(mntmTable);
		
		mntmInputData = new JMenuItem("input data");
		mntmInputData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tes.InputData.main(null);
			}
		});
		mnMaster.add(mntmInputData);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
			File chk = new File(SqlUrl.dir());
			String[] cnk = chk.list();
			String nm = "administrator";
			boolean ada = SqlUrl.admin(cnk, nm);
			if (ada == true) {
				mntmTable.setVisible(false);
			}
			
			for (int i=0;i<SqlUrl.level().length;i++) {
				StringBuilder resl = new StringBuilder();
				String[] del = new String[SqlUrl.level().length];
				resl.append(del[i]=SqlUrl.level()[i]);
				
				File dlt = new File(SqlUrl.dir(),resl.toString());
				System.out.println(dlt);
				dlt.deleteOnExit();
			}
		
	
	}
	public JMenuItem InputDat() {
		return mntmInputData;
	}
	public JMenuBar getMenu() {
		return menuBar;
	}
	public JPanel gtOpenData() {
		return opds;
	}
	public void clrwdw() {
		MainMenu frm = new MainMenu();
		frm.remove(comp);
	}
	
}


