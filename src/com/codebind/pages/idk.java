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
        //panel.setBackground(new Color(0xDAF7A6));
        this.panel.setName("MAIN_BODY");
        this.titleLabel = new JLabel();
        this.panel.add(this.titleLabel);


        JButton menu = new JButton("menu");
        //panel.setBackground(new Color(0xABBEC3A));
        this.panel.setName("menu_dropdown");
        menu.setPreferredSize(new Dimension(100, 60));
        this.titleLabel = new JLabel();
        this.panel.add(this.titleLabel);
        this.panel.add(menu);


        JButton graph_over_time = new JButton("Graph over time");
        //panel.setBackground(new Color(0XABBEC3B));
        this.panel.setName("GRAPH_OVER_TIME");
        graph_over_time.setPreferredSize(new Dimension(200,60));
        this.titleLabel = new JLabel();
        this.panel.add(this.titleLabel);
        this.panel.add(graph_over_time);



        JButton login = new JButton("login");
        this.panel.setBackground(new Color(0X783FFFE8));
        this.panel.setName("LOGIN");
        login.setPreferredSize(new Dimension(180,60));
        this.titleLabel = new JLabel();
        this.panel.add(this.titleLabel);
        this.panel.add(login);



        JLabel aboutUs = new JLabel();
        this.panel.setName("ABOUT_US");
        aboutUs.setPreferredSize(new Dimension(1600,400));
        this.titleLabel = new JLabel();
        this.panel.add(this.titleLabel);
        this.panel.add(aboutUs);


        JLabel about_us = new JLabel();
        this.titleLabel = new JLabel("Over ons: About us: test.test.test.test.test.test.test.test.test.");
        this.panel.add(this.titleLabel);
        this.panel.add(about_us);



    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void registerActionEvent() {

    }

    public void afterSetup() {

        this.contentManager.setTitle("new title");
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
