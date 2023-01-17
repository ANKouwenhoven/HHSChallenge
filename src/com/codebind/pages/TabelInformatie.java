package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public abstract class TabelInformatie implements PageInterface, ActionListener {

    JPanel panel = new JPanel(new BorderLayout());

    JPanel tabelPanel = new JPanel(new GridLayout(-1,2, 20, 20));

    ContentManager contentManager;

    public TabelInformatie() throws SQLException {

    }


    public void createGui(TabelInformatie[] informatie) {
        panel.add(
                new JLabel(String.format("%d informatie(s)", informatie.length)),
                BorderLayout.NORTH
        );


        panel.add(tabelPanel, BorderLayout.CENTER);
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
}
