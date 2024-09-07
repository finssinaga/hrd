package tes;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class MainMenu extends JFrame {
	
	private JMenuItem mntmInputData;
	private JMenuBar menuBar;
	private JButton btnX;
	private JMenuItem mntmMasterBarang;                                                                                                                                                    
	private String logs;
	private JMenuItem mntmCekStok;

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
		setVisible(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(2, 15, 2, 0));
		setJMenuBar(menuBar);
		
		JMenu mnMaster = new JMenu("master");
		menuBar.add(mnMaster);
		JMenuItem mntmTable = new JMenuItem("table");
		mntmTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenData opds = new OpenData(MainMenu.this);
				GroupLayout groupLayout = new GroupLayout(getContentPane());
		        groupLayout.setHorizontalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(opds, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		        );
		        groupLayout.setVerticalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(opds, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
		        );
		        getContentPane().add(opds);
		        getContentPane().setLayout(groupLayout);
		        getContentPane().revalidate();
		        getContentPane().repaint();
		        
			}
			});
		
		
		mnMaster.add(mntmTable);
		
		mntmInputData = new JMenuItem("input data");
		mntmInputData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel ipd = new InputData(MainMenu.this);
				
				GroupLayout groupLayout = new GroupLayout(getContentPane());
		        groupLayout.setHorizontalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(ipd, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		        );
		        groupLayout.setVerticalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(ipd, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
		        );
		        getContentPane().add(ipd);
		        getContentPane().setLayout(groupLayout);
		        getContentPane().revalidate();
		        getContentPane().repaint();
			}
		});
		mnMaster.add(mntmInputData);
		this.logs = Logon.getlg().getUser();
		mntmMasterBarang = new JMenuItem("master barang");
		mntmMasterBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MasterStok mstr = new MasterStok(MainMenu.this);
				GroupLayout groupLayout = new GroupLayout(getContentPane());
		        groupLayout.setHorizontalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(mstr, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		        );
		        groupLayout.setVerticalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(mstr, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
		        );
		        getContentPane().add(mstr);
		        getContentPane().setLayout(groupLayout);
		        getContentPane().revalidate();
		        getContentPane().repaint();
			}
		});
		mnMaster.add(mntmMasterBarang);
		
		mntmCekStok = new JMenuItem("cek stok");
		mntmCekStok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CekStok ck = new CekStok(MainMenu.this);
				GroupLayout groupLayout = new GroupLayout(getContentPane());
		        groupLayout.setHorizontalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(ck, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		        );
		        groupLayout.setVerticalGroup(
		            groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(ck, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
		        );
		        getContentPane().add(ck);
		        getContentPane().setLayout(groupLayout);
		        getContentPane().revalidate();
		        getContentPane().repaint();
			}
		});
		mnMaster.add(mntmCekStok);
		
		btnX = new JButton("x");
		btnX.setVisible(false);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().repaint();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(385, Short.MAX_VALUE)
					.addComponent(btnX)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnX)
					.addContainerGap(207, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
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
	public JButton btX() {
		return btnX;
	}
	public void cls() {
		getContentPane().removeAll();
		getContentPane().repaint();
	}
}


