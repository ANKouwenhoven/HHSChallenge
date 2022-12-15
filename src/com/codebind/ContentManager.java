package com.codebind;

import com.codebind.databaseConnection.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.awt.Color.blue;
import static java.awt.Color.white;
import static java.awt.SystemColor.text;
import static java.awt.SystemColor.windowBorder;

public class ContentManager implements ActionListener {

    JLabel titleLabel;
    JFrame frame;
    PageInterface page;
    JPanel body = new JPanel(new BorderLayout());

    DataBaseConnection dataBaseConnection;

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

    /**
     *
     * menu uitzoeken/aanpassen
     */

    /**
     *
     * einde menu uitzoeken
     */
    private JPanel getBody() {
        JPanel panel = new JPanel();
        //panel.setBackground(new Color(0xDAF7A6));
        panel.setName("MAIN_BODY");
        this.titleLabel = new JLabel();
        panel.add(this.titleLabel);


        JButton menu = new JButton("menu");
        //panel.setBackground(new Color(0xABBEC3A));
        panel.setName("menu_dropdown");
        menu.setPreferredSize(new Dimension(100, 60));
        this.titleLabel = new JLabel();
        panel.add(this.titleLabel);
        panel.add(menu);


        JButton graph_over_time = new JButton("Graph over time");
        //panel.setBackground(new Color(0XABBEC3B));
        panel.setName("GRAPH_OVER_TIME");
        graph_over_time.setPreferredSize(new Dimension(200,60));
        this.titleLabel = new JLabel();
        panel.add(this.titleLabel);
        panel.add(graph_over_time);



        JButton login = new JButton("login");
        panel.setBackground(new Color(0X783FFFE8));
        panel.setName("LOGIN");
        login.setPreferredSize(new Dimension(180,60));
        this.titleLabel = new JLabel();
        panel.add(this.titleLabel);
        panel.add(login);



        JLabel aboutUs = new JLabel();
        panel.setName("ABOUT_US");
        aboutUs.setPreferredSize(new Dimension(1600,400));
        this.titleLabel = new JLabel();
        panel.add(this.titleLabel);
        panel.add(aboutUs);


        JLabel about_us = new JLabel();
        this.titleLabel = new JLabel("Over ons: About us: test.test.test.test.test.test.test.test.test.");
        panel.add(this.titleLabel);
        panel.add(about_us);




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
}
