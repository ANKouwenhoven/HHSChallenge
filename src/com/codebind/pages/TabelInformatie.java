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

    String storedID;

    public TabelInformatie(DataBaseConnection db, String userID) throws SQLException {
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


        SetData("naam gebruiker", 2, 2);
        //gebruiker

        SetData("sens aantal", 3, 2);
        //aantal sensoren "SELECT count(*)"

        SetData("temp gemiddelde", 4, 2);
        //gemiddelde temperatuur SELECT gem(array[])"

        SetData("vochtgehalte %", 5, 2);
        //vochtgehalte in %


        System.out.println("test2");
    }

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
        preparedStatement.setString(1, contentManager.userID);
        ResultSet resultSet= null;
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
