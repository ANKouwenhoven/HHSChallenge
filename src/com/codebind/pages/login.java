package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login implements PageInterface, ActionListener {

    JPanel panel = new JPanel();

    JTextField username = new JTextField();
    JTextField password = new JTextField();

    ContentManager contentManager;

    public login() {
        JButton button = new JButton("login");

        username.setColumns(50);
        password.setColumns(50);

        button.addActionListener(this);
        this.panel.add(username);
        this.panel.add(password);
        this.panel.add(button);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void registerActionEvent() {

    }

    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    public int handleEvent(String event, Object data) {
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        idk idk = new idk();
        contentManager.setPage(new idk());
    }
}
