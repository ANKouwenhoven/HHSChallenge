package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.DataBaseConnection;
import com.codebind.databaseConnection.NetworkOverview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login implements PageInterface, ActionListener {

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

        if (database.login(email, password)) {
            //contentManager.setPage(new TestScreen());

            try {
                contentManager.setPage(new NetworksOverview(
                        NetworkOverview.getAllNetworkOverviewFromOwner(database,"user1")
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("wrong email or password");
        }

    }
}
