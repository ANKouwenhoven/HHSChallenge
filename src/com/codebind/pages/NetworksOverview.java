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

public class NetworksOverview implements PageInterface, ActionListener {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel networksPanel = new JPanel(new GridLayout(-1,2, 20, 20));
    ContentManager contentManager;
    DataBaseConnection dataBaseConnection;
    ArrayList<NetworkOverviewWidget> networkOverviewArray = new ArrayList<>();

    public NetworksOverview(DataBaseConnection db, int userID) throws SQLException {
        dataBaseConnection = db;

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
            networkOverviewArray.add(networkOverview);
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

        for (NetworkOverviewWidget widget :
                networkOverviewArray) {
            widget.setContentManager(contentManager);
        }
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
        for (NetworkOverviewWidget networkOverview:
                networkOverviewArray) {
            networkOverview.update();
        }
    }
}