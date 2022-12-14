package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu implements PageInterface {
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

            JFrame frame = new JFrame("Menu");
            frame.setVisible(true);
            frame.setSize(200,80);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JMenuBar menubar = new JMenuBar();
            frame.setJMenuBar(menubar);

            JMenu file = new JMenu("File");
            menubar.add(file);
            JMenuItem exit = new JMenuItem("Exit");
            file.add(exit);


            JMenu help = new JMenu("Help");
            menubar.add(help);
            JMenuItem about = new JMenu("About");
            help.add(about);


            class exitaction implements ActionListener {
                public void actionPerformed (ActionEvent e){
                    System.exit(0);


                }
            }
            exit.addActionListener(new exitaction());



    }
}
