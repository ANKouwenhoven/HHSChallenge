package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login implements PageInterface, ActionListener {

    JLabel emailLabel = new JLabel("E-mail");
    JTextField email = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField password = new JPasswordField();
    JButton logInButton = new JButton("log in");
    JPanel panel = new JPanel();

    ContentManager contentManager;

    public login() {
        emailLabel.setBounds(125,60,250,40);
        email.setBounds(125,100,250,40);

        passwordLabel.setBounds(125,160,250,40);
        password.setBounds(125,200,250,40);

        logInButton.setBounds(250,270,125,40);

        logInButton.addActionListener(this);

        //panel.setTitle("Log in");
        panel.setLayout(null);
        panel.setBackground(new Color(90,150,230));

        panel.add(logInButton);
        panel.add(email);
        panel.add(password);
        panel.add(emailLabel);
        panel.add(passwordLabel);
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
