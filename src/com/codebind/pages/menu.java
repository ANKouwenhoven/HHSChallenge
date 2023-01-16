package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu implements PageInterface, ActionListener {
    JPanel panel = new JPanel();
    ContentManager contentManager;
    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    @Override
    public void afterSetup() {

            //JFrame frame = new JFrame("Menu");
            //frame.setVisible(true);
            //frame.setSize(200,80);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JMenuBar menubar = new JMenuBar();
            panel.add(menubar);

            JMenu file = new JMenu("File");
            menubar.add(file);
            JMenuItem exit = new JMenuItem("Exit");
            file.add(exit);


            JMenu help = new JMenu("Help");
            menubar.add(help);
            JMenuItem about = new JMenu("About");
            JMenuItem aa = new JMenuItem("aa");
            aa.addActionListener(this);
            about.add(aa);
            help.add(about);


            exit.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(9);
                break;
            case "aa":
                //contentManager.setPage(); //page waarnaar die moet gaan (moet pageInterface implementer)
                break;
        }
    }
}
