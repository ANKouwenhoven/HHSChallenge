package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.DataBaseConnection;
import com.codebind.databaseConnection.NetworkOverview;
import com.codebind.databaseConnection.NetworkOverviewRefreshStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class NetworksOverview implements PageInterface, ActionListener {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel networksPanel = new JPanel(new GridLayout(-1,2, 20, 20));
    ContentManager contentManager;
    DataBaseConnection dataBaseConnection;
    String storedID;
    HashMap<String, NetworkOverviewWidget> networkOverviewWidgetHashMap = new HashMap<>();

    public NetworksOverview(DataBaseConnection db, String userID) throws SQLException {
        dataBaseConnection = db;
        storedID = userID;

        NetworkOverview[] networksOverview = NetworkOverview.getAllNetworkOverviewFromOwner(db, userID);
        Timer timer = new Timer(1500, this);
        timer.setActionCommand("redrawUser");
        timer.start();

        createGui(networksOverview);
    }

    public NetworksOverview(NetworkOverview[] overviews) {
        createGui(overviews);
    }

    public void createGui(NetworkOverview[] overviews) {
        panel.add(
                new JLabel(String.format("%d overview(s)", overviews.length)),
                BorderLayout.NORTH
        );

        for (NetworkOverview o :
                overviews) {
            NetworkOverviewWidget networkOverview = new NetworkOverviewWidget(o);
            networkOverviewWidgetHashMap.put(o.networkID, networkOverview);
            networksPanel.add(networkOverview.getPanel());

        }
        panel.add(networksPanel, BorderLayout.CENTER);
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
    public void afterSetup() {
        contentManager.setTitle("network overview");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "redrawUser":
                redrawUser();
        }
    }

    private void redrawUser() {
        try {
            ArrayList<NetworkOverviewRefreshStatement> nd = NetworkOverviewRefreshStatement.getFromUserID(dataBaseConnection,storedID);

            for (NetworkOverviewRefreshStatement data:
                 nd) {
                networkOverviewWidgetHashMap.get(data.netwerkID).update(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}