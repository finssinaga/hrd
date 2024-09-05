package tes;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class AdminPanel extends JFrame {

    private JMenuItem mntmClearWindow;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminPanel frame = new AdminPanel();
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
    public AdminPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnUserManage = new JMenu("user");
        menuBar.add(mnUserManage);

        JMenuItem mntmAddUser = new JMenuItem("add user");
        mnUserManage.add(mntmAddUser);

        JMenuItem mntmRemoveUser = new JMenuItem("remove user");
        mntmRemoveUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and configure RemoveUser
                RemoveUser removeUser = new RemoveUser();
                configureAndShowPanel(removeUser);
            }
        });
        mnUserManage.add(mntmRemoveUser);

        JMenuItem mntmManageUserGroup = new JMenuItem("manage user group");
        mnUserManage.add(mntmManageUserGroup);

        JMenu mnDatabase = new JMenu("database");
        menuBar.add(mnDatabase);

        JMenuItem mntmConfig = new JMenuItem("configuration");
        mnDatabase.add(mntmConfig);

        JMenu mnUpdate = new JMenu("update");
        menuBar.add(mnUpdate);

        mntmClearWindow = new JMenuItem("clear window");
        mntmClearWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        mntmClearWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
        mnUpdate.add(mntmClearWindow);

        mntmAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Create and configure AddUsr
                AddUsr addUser = new AddUsr();
                configureAndShowPanel(addUser);
            }
        });
    }

    private void configureAndShowPanel(JPanel panel) {
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
        );
        getContentPane().setLayout(groupLayout);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public JMenuItem getMntmClearWindow() {
        return mntmClearWindow;
    }
}