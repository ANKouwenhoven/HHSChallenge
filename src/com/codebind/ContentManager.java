package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.blue;
import static java.awt.SystemColor.text;

public class ContentManager {

    JLabel titleLabel;
    JFrame frame;
    PageInterface page;
    JPanel body = new JPanel();



    // ??? pageHistory;

    // {{ init
    public ContentManager() {
        this.frame = getBasicFrame();
        this.frame.setVisible(true);
        this.frame.setSize(1600,960);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    }
}
