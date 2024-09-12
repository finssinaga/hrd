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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainMenu extends JFrame {
	
	private JMenuItem mntmInputData;
	private JMenuBar menuBar;
	private JButton btnX;
	private JMenuItem mntmMasterBarang;                                                                                                                                                    
	private String logs;
	private JMenuItem mntmCekStok;
	private JMenu mnLaporan;
	private JMenuItem mntmLaporanStokBarang;
	private JMenuItem mntmLaporanHistoryPerbaikan;
	private JMenuItem mntmMasterKendaraan;
	private JMenuItem menuItem;
	private JMenuItem mntmInputMaster;
	private JMenu mnPerbaikan;
	private JMenuItem mntmInputPerbaikan;
	private JMenuItem mntmMasterSparepart;
	private JMenuItem mntmTest;
	private JLabel usrlx;
	private JLabel perms;
	private String levels;

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
	 */
	public MainMenu() throws ClassNotFoundException, SQLException {
		setTitle("Program");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/icon/doc_ico.png")));
		setVisible(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(0, 30));
		menuBar.setMargin(new Insets(2, 15, 2, 0));
		setJMenuBar(menuBar);
		this.logs = Logon.getlg().getUser();
		this.levels=Logon.getlg().getLevel();
		JMenu mnMaster = new JMenu("Master");
		mnMaster.setFont(new Font("Arial", Font.BOLD, 14));
		menuBar.add(mnMaster);
		JMenuItem mntmMasterKendaraan = new JMenuItem("Buka Master Kendaraan");
		mntmMasterKendaraan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel mstr = new MasterKendaraan(MainMenu.this);
				conpanel(mstr);
			}
			});
		
		mntmInputMaster = new JMenuItem("Input Master Kendaraan");
		mntmInputMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel ipt = new InputMasterKendaraan(MainMenu.this);
				conpanel(ipt);
			}
		});
		mnMaster.add(mntmInputMaster);
		Logon.getlg().dispose();
		
		mnMaster.add(mntmMasterKendaraan);
		
		mntmInputData = new JMenuItem("input data");
		mntmInputData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel ipd = new InputData(MainMenu.this);
				conpanel(ipd);
			}
		});
		
		menuItem = new JMenuItem("---------------------");
		mnMaster.add(menuItem);
		
		mntmMasterSparepart = new JMenuItem("Master SparePart");
		mntmMasterSparepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel p = new MasterPart(MainMenu.this);
				conpanel(p);
			}
		});
		mnMaster.add(mntmMasterSparepart);
		mnMaster.add(mntmInputData);
		mntmMasterBarang = new JMenuItem("master barang");
		mntmMasterBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MasterStok mstr = new MasterStok(MainMenu.this);
				conpanel(mstr);
			}
		});
		mnMaster.add(mntmMasterBarang);
		
		mntmCekStok = new JMenuItem("cek stok");
		mntmCekStok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CekStok ck = new CekStok(MainMenu.this);
				conpanel(ck);
			}
		});
		mnMaster.add(mntmCekStok);
		
		mntmTest = new JMenuItem("test");
		mntmTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel pp = new OpenData(MainMenu.this);
				conpanel(pp);
			}
		});
		mnMaster.add(mntmTest);
		
		mnPerbaikan = new JMenu("Perbaikan");
		mnPerbaikan.setFont(new Font("Arial", Font.BOLD, 14));
		menuBar.add(mnPerbaikan);
		
		mntmInputPerbaikan = new JMenuItem("Perbaikan Part");
		mntmInputPerbaikan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel p = new PerbaikanArmada(MainMenu.this);
				conpanel(p);
			}
		});
		mnPerbaikan.add(mntmInputPerbaikan);
		
		mnLaporan = new JMenu("Laporan");
		mnLaporan.setFont(new Font("Arial", Font.BOLD, 14));
		menuBar.add(mnLaporan);
		mntmLaporanStokBarang = new JMenuItem("laporan stok barang");
		mntmLaporanStokBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrintPrev pt = new PrintPrev();
				conpanel(pt);
			}
		});
		mnLaporan.add(mntmLaporanStokBarang);
		
		mntmLaporanHistoryPerbaikan = new JMenuItem("Laporan History Perbaikan");
		mnLaporan.add(mntmLaporanHistoryPerbaikan);
		usrlx=new JLabel();
		usrlx.setVisible(false);
		
		perms = new JLabel("");
		perms.setVisible(false);
		
		perms.setText(levels);
		menuBar.add(perms);
		usrlx.setText(logs);
		menuBar.add(usrlx);
		
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
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(385, Short.MAX_VALUE)
					.addComponent(btnX)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnX)
					.addContainerGap(198, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
			
			mntmLaporanHistoryPerbaikan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LapHistory lp = new LapHistory(MainMenu.this);
					conpanel(lp);
				}
			});
	
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
	public JLabel uslog() {
		return usrlx;
	}
	public JLabel usrlevel() {
		return perms;
	}
	public void conpanel(JPanel panel) {
		cls();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );
        getContentPane().add(panel);
        getContentPane().setLayout(groupLayout);
        getContentPane().validate();
        getContentPane().repaint();
	}
	public void cls() {
		getContentPane().removeAll();
		getContentPane().repaint();
	}
	public void permission() {
		File chk = new File(SqlUrl.dir());
		String[] cnk = chk.list();
		String nm = "administrator";
		boolean ada = SqlUrl.admin(cnk, nm);
		if (ada == true) {
			mntmMasterKendaraan.setVisible(false);
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
}


