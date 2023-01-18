package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.DataBaseConnection;
import com.codebind.databaseConnection.NetworkOverview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class Login implements PageInterface, ActionListener, KeyListener {

    JLabel emailLabel = new JLabel("E-mail");
    JTextField email = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField password = new JPasswordField();
    JButton logInButton = new JButton("log in");
    JPanel panel = new JPanel();

    ContentManager contentManager;

    public Login() {
        emailLabel.setBounds(125,60,250,40);
        email.setBounds(125,100,250,40);

        passwordLabel.setBounds(125,160,250,40);
        password.setBounds(125,200,250,40);

        logInButton.setBounds(250,270,125,40);

        logInButton.addActionListener(this);

        //panel.setTitle("Log in");
        panel.setLayout(null);
        panel.setBackground(new Color(90,150,230));
        password.addKeyListener(this);

        panel.add(logInButton);
        panel.add(email);
        panel.add(password);
        panel.add(emailLabel);
        panel.add(passwordLabel);

    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void afterSetup() {
        this.contentManager.setTitle("login");
    }

    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DataBaseConnection database =  this.contentManager.getDataBaseConnection();
        if (database == null) {
            System.out.println("database connection is not set.");
            return;
        }
        String email = this.email.getText();
        String password = String.valueOf(this.password.getPassword());

        int userID = database.login(email, password);

        if (userID != -1) {
            contentManager.userID = userID;
            try {
                contentManager.setPage(new NetworksOverview(database, userID));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            //contentManager.setPage(new menu());
            System.out.println("wrong email or password");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int enter = 10;
        if (e.getKeyCode() == enter) {
            actionPerformed(new ActionEvent(this, 1, ""));
        }

    }

    @Override public void keyTyped(KeyEvent e) {} @Override public void keyPressed(KeyEvent e) {}
}
