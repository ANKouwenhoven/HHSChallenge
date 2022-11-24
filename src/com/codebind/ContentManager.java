package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Color.blue;
import static java.awt.SystemColor.text;

public class ContentManager {

    JLabel titleLabel;
    JFrame frame;



    // ??? pageHistory;

    // {{ init
    public ContentManager() {
        this.frame = getBasicFrame();
        this.frame.setVisible(true);
        this.frame.setSize(1600,960);
    }


    private JFrame getBasicFrame() {
        JFrame frame = new JFrame("greenites");
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        pane.add(this.getHeader(),"North");
        pane.add(this.getBody(),"Center");
        return frame;
    }

    private JPanel getHeader() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x00FFCA));
        panel.setName("MAIN_HEADER");

        this.titleLabel = new JLabel("Title");
        panel.add(this.titleLabel);
        return panel;
    }

    private JPanel getBody() {
        JPanel panel = new JPanel();
        //panel.setBackground(new Color(0xDAF7A6));
        panel.setName("MAIN_BODY");
        this.titleLabel = new JLabel("Text");
        panel.add(this.titleLabel);


        JButton mockups = new JButton();
        //panel.setBackground(new Color(0xABBEC3A));
        panel.setName("MOCKUP_KNOP");
        mockups.setPreferredSize(new Dimension(100, 60));
        this.titleLabel = new JLabel("Mockups");
        panel.add(this.titleLabel);
        panel.add(mockups);


        JButton graph_over_time = new JButton();
        //panel.setBackground(new Color(0XABBEC3B));
        panel.setName("GRAPH_OVER_TIME");
        graph_over_time.setPreferredSize(new Dimension(100,60));
        this.titleLabel = new JLabel("GraphOverTime");
        panel.add(this.titleLabel);
        panel.add(graph_over_time);


        JButton login = new JButton();
        panel.setBackground(new Color(0X783FFFE8));
        panel.setName("LOGIN");
        login.setPreferredSize(new Dimension(60,60));
        this.titleLabel = new JLabel("Login");
        panel.add(this.titleLabel);
        panel.add(login);


        JLabel aboutUs = new JLabel();
        panel.setName("ABOUT_US");
        aboutUs.setPreferredSize(new Dimension(1600,400));
        this.titleLabel = new JLabel("About us: test.test.test.test.test.test.test.test.test."); //positie aanpassen
        panel.add(this.titleLabel);
        panel.add(aboutUs);


        return panel;
    }
    // {{ body logic
}
