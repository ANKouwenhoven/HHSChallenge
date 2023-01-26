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

    JPanel tabelPanel = new JPanel(new GridLayout(-1,2, 20, 20));

    ContentManager contentManager;

    DataBaseConnection dataBaseConnection;
    JTable table;

    int storedID;

    public TabelInformatie(DataBaseConnection db, int userID) throws SQLException {
        dataBaseConnection = db;
        storedID = userID;

    }

    public void createGui() {


        String/*object*/[][] data = {
                {"Meetdata: ", "dd-mm-yyyy.begin + tijd.begin", "dd-mm-yyyy.einde + tijd.einde"},
                {"","",""},
                {"Gebruiker: ", "", "gebruiker naam"},
                {"Aantal sensoren: ", "", "aantal sensoren"},
                {"Temperatuurstatus: ", "", "gem.temperatuur"},
                {"Vochtstatus: ", "", "status.vocht.meest recente meting"}
        };

        String[] columnNames = {
                "Meetdata",
                "dd-mm-yyyy.begin + tijd.begin",
                "dd-mm-yyyy.einde + tijd.einde"
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table = new JTable(data, columnNames);
        panel.add(table);
        System.out.println("test");


        SetUsername(

                "naam gebruiker", 2, 2);
        //gebruiker
        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT email_adres\n" +
                            "From Gebruiker n\n"
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        SetAantalSensoren("sens aantal", 3, 2);
        //aantal sensoren "SELECT count(*)"
        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT count(*)\n" +
                            "From Netwerk n\n" +
                            "join sensor s on  n.netwerkID = s.netwerkID\n" +
                            "Where gebruikercode = ?; \n"
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        SetTemperatuurGemiddelde("temp gemiddelde", 4, 2);
        //gemiddelde temperatuur SELECT gem(array[])"
       /* try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    //hier gemiddelde temperatuur over x tijd zetten
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } */


        SetVochtGehalte("vochtgehalte %", 5, 2);
        //vochtgehalte in %
      /* try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    //hier het gemiddelde van de 3 meest recente vochtwaarden weergeven
            );
            preparedStatement.setString(1, String.valueOf(contentManager.userID));
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } */

        System.out.println("test2");
    }

    private void SetVochtGehalte(String s, int i, int i1) {
    }
    //method SetVochtGehalte (anders deed ie t niet) 11:51 1/25/23
    private void SetTemperatuurGemiddelde(String temp_gemiddelde, int i, int i1) {
    }
    //method SetTemperatuurGemiddelde (anders deed ie t niet) 11:47 1/25/23
    private void SetAantalSensoren(String sens_aantal, int i, int i1) {
    }
    //method SetAantalSensoren (anders deed ie t niet) 11:39 1/25/23
    private void SetUsername(String naam_gebruiker, int i, int i1) {
    }
    //method setUsername (anders deed ie t niet) 11:25 1/25/23
    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
        System.out.println("bb");

    }

    @Override
    public void afterSetup() {
        System.out.println("hi");

        try {

            PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement(
                    "SELECT count(*)\n" +
                            "From Netwerk n\n" +
                            "join sensor s on  n.netwerkID = s.netwerkID\n" +
                            "Where gebruikercode = ?; \n"
        );
        preparedStatement.setString(1, String.valueOf(contentManager.userID));
        ResultSet resultSet;
           resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            resultSet.getInt("ss");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contentManager.setTitle("tabel informatie");
        createGui();
    }

    public void SetData(Object obj, int row_index, int columnNames_index){
        table.getModel().setValueAt(obj,row_index,columnNames_index);
        System.out.println("Value is added");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
