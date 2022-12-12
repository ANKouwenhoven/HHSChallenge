package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * dit is de body code wat in ContentManager stond
 * written by merlijn
 */
public class idk implements PageInterface, ActionListener {

    JPanel panel = new JPanel();

    JLabel titleLabel;

    ContentManager contentManager;

    public idk() {
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


        this.panel.add(panel);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void registerActionEvent() {

    }

    public void afterSetup() {

    }

    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    public int handleEvent(String event, Object data) {
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
