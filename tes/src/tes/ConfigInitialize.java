package tes;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.wsdl.Output;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ConfigInitialize extends JPanel {
	private JTextField jdbcurl;
	private JTextField jdbcuser;
	private JPasswordField jdbcpass;
	private JTextField jdbcport;
	private JTextField jdbcdb;
	private JTextField outdir;
	private JTextField reportdir;
	private JTextField userdir;

	/**
	 * Create the panel.
	 */
	public ConfigInitialize() {
		
		JLabel lblJdbcUrl = new JLabel("jdbc url");
		
		jdbcurl = new JTextField();
		jdbcurl.setColumns(10);
		
		JLabel lblJdbcUser = new JLabel("jdbc user");
		
		jdbcuser = new JTextField();
		jdbcuser.setColumns(10);
		
		JLabel lblJdbcPass = new JLabel("jdbc pass");
		
		jdbcpass = new JPasswordField();
		jdbcpass.setColumns(10);
		
		jdbcport = new JTextField();
		jdbcport.setColumns(10);
		
		JLabel lblJdbcPort = new JLabel("jdbc port");
		
		jdbcdb = new JTextField();
		jdbcdb.setColumns(10);
		
		JLabel lblJdbcDatabase = new JLabel("jdbc database");
		
		outdir = new JTextField();
		outdir.setColumns(10);
		
		JLabel lblOutputDir = new JLabel("output dir");
		
		reportdir = new JTextField();
		reportdir.setColumns(10);
		
		JLabel lblReportDir = new JLabel("report dir");
		
		userdir = new JTextField();
		userdir.setColumns(10);
		
		JLabel lblWorkingDirectory = new JLabel("working directory");
		
		JButton btnApplySetting = new JButton("apply setting");
		
		JButton btnOpenSetting = new JButton("open setting");
		
		userdir.setText(System.getProperty("user.dir"));
		btnApplySetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				
				File conf = new File(System.getProperty("user.dir")+"/config/hrd.config");
				conf.delete();
				try {
					conf.getParentFile().mkdirs();
					conf.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SqlUrl.config(jdbcurl.getText(), "server.url");
				SqlUrl.config(jdbcuser.getText(), "server.user");
				SqlUrl.config(jdbcpass.getText(), "server.password");
				SqlUrl.config(jdbcport.getText(),"server.port");
				SqlUrl.config(jdbcdb.getText(), "server.database");
				SqlUrl.config("/"+outdir.getText()+"/", "server.outputdir");
				SqlUrl.config("/"+reportdir.getText()+"/", "server.reportdir");
				SqlUrl.config(System.getProperty("user.dir"),"user.dir");
				
				try {
					
					Files.createDirectory(Paths.get(SqlUrl.dir()+SqlUrl.confloader("server.outputdir")));
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
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblJdbcDatabase)
								.addComponent(lblJdbcPort)
								.addComponent(lblJdbcPass)
								.addComponent(lblOutputDir)
								.addComponent(lblReportDir)
								.addComponent(lblWorkingDirectory))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(lblJdbcUser)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblJdbcUrl)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(userdir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(reportdir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(outdir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jdbcurl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(btnApplySetting)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnOpenSetting))
						.addComponent(jdbcuser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jdbcport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jdbcpass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jdbcdb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jdbcurl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJdbcUrl)
						.addComponent(btnApplySetting)
						.addComponent(btnOpenSetting))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jdbcuser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJdbcUser))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJdbcPass)
						.addComponent(jdbcpass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jdbcport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJdbcPort))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jdbcdb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJdbcDatabase))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(outdir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOutputDir))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(reportdir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReportDir))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userdir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWorkingDirectory))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
