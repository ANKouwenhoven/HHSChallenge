package com.codebind;

import com.codebind.databaseConnection.DataBaseConnection;

import javax.swing.*;
import javax.tools.JavaFileManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.codebind.pages.*;
import java.sql.SQLException;

public class ContentManager implements ActionListener {

    private JLabel titleLabel;
    private JFrame frame;
    PageInterface page;
    public String userID;
    private JPanel body = new JPanel(new BorderLayout());

    private DataBaseConnection dataBaseConnection;

    // ??? pageHistory;

    // {{ init
    public ContentManager() {
        this.frame = getBasicFrame();
        this.frame.setVisible(true);
        this.frame.setSize(1600,960);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            this.dataBaseConnection = new DataBaseConnection();
        } catch (Exception e) {
            System.out.println("failed creating databaseConnection object.");
            e.printStackTrace();
            System.out.println("continue without databaseConnection");
        }
    }

    private JFrame getBasicFrame() {
        JFrame frame = new JFrame("greenites");
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        pane.add(this.getHeader(),"North");
        pane.add(this.body,"Center");
        return frame;
    }

    private JPanel getHeader() {
        JPanel panel = new JPanel();
        JMenuBar menubar = new JMenuBar();
        panel.add(menubar);
        panel.setPreferredSize(new Dimension(100, 60));

        JMenu file = new JMenu("Logout");
        menubar.add(file);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        file.setPreferredSize(new Dimension(100, 60));


        JMenu menu = new JMenu("Menu");
        menubar.add(menu);
        JMenuItem informatie = new JMenu("Informatie");
        JMenuItem network_overview = new JMenu("Network Overview");
        JMenuItem tabel_informatie = new JMenuItem("Tabel Informatie");
        informatie.addActionListener(this);
        //tabel_informatie.addActionListener(this);

        menu.add(informatie);
        informatie.add(network_overview);
        informatie.add(tabel_informatie);

        network_overview.addActionListener(this);

        //menu.add(network_overview);
        menu.setPreferredSize(new Dimension(100, 60));
        exit.addActionListener(this);

        return  panel;


        /*JPanel panel = new JPanel();
        panel.setBackground(new Color(0x00FFCA));
        panel.setName("MAIN_HEADER");

        JMenu menu = new JMenu("menu");
        menu.addActionListener(this);
        //panel.setBackground(new Color(0xABBEC3A));           
        panel.setName("menu_dropdown");                        
        menu.setPreferredSize(new Dimension(100, 60));         
        this.titleLabel = new JLabel();                        
        panel.add(this.titleLabel);                            
        panel.add(menu);                                       

        this.titleLabel = new JLabel("Title");
        panel.add(this.titleLabel);
        return panel;
        */

    }

    // {{ body logic

    public void setPage(PageInterface page) {
        this.body.removeAll();
        this.body.add(page.getPanel());
        this.body.revalidate();

        this.page = page;
        page.setContentManager(this);

        page.afterSetup();
    }

    public void setTitle(String newTitle) {
        this.titleLabel.setText(newTitle);
        this.frame.setTitle("Greenite: " + newTitle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e.paramString());
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(24);
                break;

            case "Network Overview":
                try {
                    setPage(new NetworksOverview(dataBaseConnection, userID));
                    System.out.println("asdasd");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }

    public DataBaseConnection getDataBaseConnection() {
        return dataBaseConnection;
    }
}
