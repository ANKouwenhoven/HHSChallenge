package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.DataBaseConnection;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TabelInformatie implements PageInterface, ActionListener {

    JPanel panel = new JPanel(new BorderLayout());

    JPanel tabelPanel = new JPanel(new GridLayout(-1,2, 20, 20));

    ContentManager contentManager;

    DataBaseConnection dataBaseConnection;

    String storedID;

    public TabelInformatie(DataBaseConnection db, String userID) throws SQLException {
        dataBaseConnection = db;
        storedID = userID;


        createGui();
    }

    public TabelInformatie() {createGui();}
    public void createGui() {
        String[] columnNames = {"Meetdata",
                "dd-mm-yyyy.begin + tijd.begin",
                "dd-mm-yyyy.einde + tijd.einde"};

        Object[][] data = {
                {"Meetdata: ", "dd-mm-yyyy.begin + tijd.begin", "dd-mm-yyyy.einde + tijd.einde"},
                {"","",""},
                {"Gebruiker: ", "", "gebruiker.naam"},
                {"Aantal sensoren: ", "", "aantal sensoren"},
                {"Temperatuurstatus: ", "", "gem.temperatuur"},
                {"Vochtstatus: ", "", "status.vocht.meest recente meting"},
        };
        JTable table = new JTable(data, columnNames);
        panel.add(table);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    @Override
    public void afterSetup() { contentManager.setTitle("tabel informatie");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
