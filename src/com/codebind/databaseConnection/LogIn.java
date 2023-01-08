package com.codebind.databaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn extends JFrame implements ActionListener {
    JLabel interEmail = new JLabel("E-mail");
    JTextField email = new JTextField();
    JLabel interPassword = new JLabel("Password");
    JPasswordField password = new JPasswordField();
    JButton logInButton = new JButton("log in");
    JFrame overzicht = new JFrame();
    LogIn() {
        interEmail.setBounds(125,60,250,40);
        email.setBounds(125,100,250,40);
        interPassword.setBounds(125,160,250,40);
        password.setBounds(125,200,250,40);
        logInButton.setBounds(250,270,125,40);
        logInButton.addActionListener(this);

        overzicht.setTitle("Log in");
        overzicht.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        overzicht.setSize(500,400);
        overzicht.setResizable(false);
        overzicht.setLayout(null);
        overzicht.setVisible(true);
        overzicht.getContentPane().setBackground(new Color(90,150,230));
        overzicht.add(logInButton);
        overzicht.add(email);
        overzicht.add(password);
        overzicht.add(interEmail);
        overzicht.add(interPassword);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
           if (comparePasswords()) {
               overzicht.dispose();
               System.out.println("Het juiste wachtwoord!");
               //MyFrame melding = new MyFrame();
           }
           else {
               System.out.println("Verkeerde wachtwoord!");
           }
       }

    public boolean comparePasswords() {
        String gebruikersEmail = email.getText();
        String gebruikersPassword = String.valueOf(password.getPassword());
        String gebnruikercode;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            gebnruikercode =  connectionMetDataBase.login(gebruikersEmail, gebruikersPassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (gebnruikercode != null) {
            return true;
        }
        else {
            return false;
        }
    }
}