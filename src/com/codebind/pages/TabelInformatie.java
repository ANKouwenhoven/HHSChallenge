package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.DataBaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TabelInformatie implements PageInterface, ActionListener {

    JPanel panel = new JPanel(new BorderLayout());

    ContentManager contentManager;

    DataBaseConnection dataBaseConnection;
    JTable table;

    int storedID;

    public TabelInformatie(DataBaseConnection db, int userID) throws SQLException {
        dataBaseConnection = db;
        storedID = userID;

    }

    public void createGui() {

        String[][] data = {
                {"Meetdata: ", "Datum/tijdBegin", "Datum/tijdEinde"},
                {"","",""},
                {"Gebruiker: ", "", "gebruiker naam"},
                {"Aantal sensoren: ", "", "aantal sensoren"},
                {"Temperatuurstatus: ", "", "gem.temperatuur"},
                {"Vochtstatus: ", "", "status.vocht.meest recente meting"}
        };

        String[] columnNames = {
                "Meetdata",
                "Datum/tijdBegin",
                "Datum/tijdEinde"
        };

        String username = SetUsername();
        String sensoren = SetAantalSensoren();
        String temperatuur = SetTemperatuur();
        String vocht = SetVochtGehalte();
        data[2][2] = username;
        data[3][2] = sensoren;
        data[4][2] = temperatuur;
        data[5][2] = vocht;

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table = new JTable(data, columnNames);
        panel.add(table);
        System.out.println("test");

    }

    private String SetVochtGehalte() {
        int userID = contentManager.userID;
        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT waarde\n" +
                            "From Meetwaarde n\n" +
                            "Where gebruikercode = " + userID
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            String output = resultSet.getString(1);
            System.out.println(output);

            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String SetTemperatuur() {
        int userID = contentManager.userID;
        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT waarde\n" +
                            "From Meetwaarde n\n" +
                            "Where gebruikercode = " + userID
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            String output = resultSet.getString(1);
            System.out.println(output);

            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String SetAantalSensoren() {
        int userID = contentManager.userID;
        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT count(*)\n" +
                            "From Sensor n\n" +
                            "Where gebruikercode = " + userID
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            String output = resultSet.getString(1);
            System.out.println(output);

            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String SetUsername() {
        int userID = contentManager.userID;

        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT naam\n" +
                            "From Gebruiker\n" +
                    "Where gebruikerCode = " + userID
            );

            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            String output = resultSet.getString(1);
            System.out.println(output);

            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
        System.out.println("setcontentmanager");

    }

    @Override
    public void afterSetup() {
        System.out.println("aftersetup");


        contentManager.setTitle("tabel informatie");
        createGui();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
