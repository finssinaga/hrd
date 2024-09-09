package tes;

import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

public class PrintPrev extends JPanel{
	public PrintPrev() {
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnPrint = new JButton("print");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(295, Short.MAX_VALUE)
					.addComponent(btnPrint)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(76))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPrint)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		
		
		
		 try {
             // Load HTML file
             File rp = new File(System.getProperty("user.dir"),"\\output\\report.html");
             editorPane.setPage(rp.toURI().toURL());
             
         } catch (IOException e) {
             e.printStackTrace();
             editorPane.setText("<html>Error loading file</html>");
         }
		 btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						editorPane.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		setLayout(groupLayout);
	}
}