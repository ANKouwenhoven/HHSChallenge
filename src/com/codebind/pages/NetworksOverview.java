package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.NetworkOverview;

import javax.swing.*;
import java.awt.*;

public class NetworksOverview implements PageInterface {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel networksPanel = new JPanel(new GridLayout(-1,2, 20, 20));
    ContentManager contentManager;

    public NetworksOverview(NetworkOverview[] overviews) {
        panel.add(
                new JLabel(String.format("%d overview(s)", overviews.length)),
                BorderLayout.NORTH
        );

        for (NetworkOverview o :
                overviews) {
            NetworkOverviewWidget networkOverview = new NetworkOverviewWidget(o);
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
}