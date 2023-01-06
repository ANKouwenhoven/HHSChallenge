package com.codebind;

import com.codebind.databaseConnection.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ContentManager implements ActionListener {

    private JLabel titleLabel;
    private JFrame frame;
    PageInterface page;
    private JPanel body = new JPanel(new BorderLayout());

    private DataBaseConnection dataBaseConnection;

    // ??? pageHistory;

    // {{ init
    public ContentManager() {
        this.frame = getBasicFrame();
        this.frame.setVisible(true);
        this.frame.setSize(1600,960);

        try {
            this.dataBaseConnection = new DataBaseConnection();
        } catch (SQLException e) {
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
    }

    public DataBaseConnection getDataBaseConnection() {
        return dataBaseConnection;
    }
}
