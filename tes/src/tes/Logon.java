package tes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class Logon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private static Logon lgn;
	private JTextField levels;
	public static synchronized Logon getlg() {
		if (lgn == null ) {
			lgn= new Logon();
		}
		return lgn;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				Logon frame = Logon.getlg();
				frame.setVisible(true);
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Logon.class.getResource("/icon/pt_pbs.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon(Logon.class.getResource("/icon/pt_pbs.png"));
		java.awt.Image img = icon.getImage();
		java.awt.Image resimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resimg);
		
		txtPass = new JPasswordField();
		
		JLabel lblUser = new JLabel("Nama User");
		JButton btnLogin = new JButton("Login");
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		
		JLabel lbIcon = new JLabel("");
		lbIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lbIcon.setForeground(new Color(0, 0, 0));
		lbIcon.setBackground(new Color(255, 255, 255));
		lbIcon.setIcon((Icon) icon);
		txtUser = new JTextField();
		txtUser.grabFocus();
		txtUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPass.grabFocus();
			}
		});
		txtUser.setColumns(10);
		JLabel lblPassword = new JLabel("Password");
		
		JLabel ComName = new JLabel("PT. PANCA");
		ComName.setBackground(new Color(255, 255, 255));
		ComName.setFont(new Font("Levenim MT", ComName.getFont().getStyle() | Font.BOLD, 14));
		ComName.setForeground(new Color(0, 0, 0));
		ComName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(165, 42, 42));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
		);
		
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setLabelFor(txtUser);
		
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setLabelFor(txtPass);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Userid = txtUser.getText();
				String Passwd = txtPass.getText();
				levels.setText(lvlAdms());
					String suclogin = "Selamat Datang "+userid();
					if (Userid.equals(userid())&&Passwd.equals(pass())) {
						JOptionPane.showMessageDialog(panel,suclogin);
						MainMenu.main(null);
						System.out.println("Working Directory = " + SqlUrl.dir());
					}
			}
			});
		
		levels = new JTextField();
		levels.setVisible(false);
		levels.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLogin)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblPassword)
									.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
									.addComponent(lblUser)
									.addComponent(txtPass))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(48)
							.addComponent(levels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(levels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(lblUser)
					.addGap(5)
					.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblPassword)
					.addGap(5)
					.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblBersoedaraanSedjati = new JLabel("BERSOEDARAAN SEDJATI");
		lblBersoedaraanSedjati.setFont(new Font("Levenim MT", lblBersoedaraanSedjati.getFont().getStyle() | Font.BOLD, 14));
		lblBersoedaraanSedjati.setHorizontalAlignment(SwingConstants.CENTER);
		lblBersoedaraanSedjati.setForeground(Color.BLACK);
		lblBersoedaraanSedjati.setBackground(Color.WHITE);
		
		JButton btnAdminpanel = new JButton("");
		btnAdminpanel.setForeground(new Color(169, 169, 169));
		btnAdminpanel.setBackground(new Color(169, 169, 169));
		btnAdminpanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = txtUser.getText();
				String pass = txtPass.getText();
				if(user.equals("itpbs") && pass.equals("itpbs")) {
					AdminPanel.main(null);
				}else {
					JOptionPane.showMessageDialog(panel_1, "UNAUTHORIZED!!");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(lblBersoedaraanSedjati, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(ComName, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(btnAdminpanel))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(38)
					.addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(ComName, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBersoedaraanSedjati, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(btnAdminpanel)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		txtPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnLogin.doClick();
			}
		});
		
		
	}
public String userid() {
		String Suser = null;
		try {
			String query = "select * from user where userid = "+"'"+txtUser.getText()+"'";
			Suser = SqlUrl.sqlGet(query, 1).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "username salah");
		}
		
	return Suser;
}
public String pass() {
	String pw = null;
	try {
		String query = "select * from user where pass = "+"'"+txtPass.getText()+"'";
		pw = SqlUrl.sqlGet(query, 2).toString();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "password salah");
	}
	return pw;
}
	public String lvlAdms() {
	String lvl = null;
	try {
		String query = "select * from user where userid = "+"'"+txtUser.getText()+"'";
		lvl = SqlUrl.sqlGet(query, 3).toString();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "maaf, anda tidak mempunyai akses");
	}
	return lvl;
}

	public String getUser() {
		return txtUser.getText();
	}
	public String getPass() {
		return txtPass.getText();
	}
	public String getLevel() {
		return levels.getText();
	}
}
