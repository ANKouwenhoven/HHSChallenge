package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.NetworkOverview;
import com.codebind.databaseConnection.NetworkOverviewRefreshStatement;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class NetworkOverviewWidget implements PageInterface {

    static EmptyBorder margin = new EmptyBorder(1,10,1,0);

    JPanel panel = new JPanel(new BorderLayout());
    JPanel contentPane = new JPanel(new GridLayout(-1, 1, 0, 8));

    HashMap<String, JPanel> sensorHashMap = new HashMap<>();

    ContentManager contentManager;

    public NetworkOverviewWidget(NetworkOverview networkOverview) {
        panel.setMaximumSize(new Dimension(80, 80));
        panel.setBackground(new Color(0xAAAAAAAA, true));

        JTabbedPane pane = new JTabbedPane();
        pane.setMaximumSize(new Dimension(80, 80));

        contentPane.setName(networkOverview.networkLocation);
        fillSensorData(contentPane, networkOverview);

        pane.add(contentPane);
        panel.add(pane, BorderLayout.NORTH);
    }

    public void fillSensorData(JPanel networkPanel, NetworkOverview network) {
        for (NetworkOverview.sensorInformation sensorInfo:
             network.sensorsInformation) {

            JPanel sensorPanel = new JPanel(new GridLayout(-1,1,2,-1));
            sensorPanel.setName("sensor: "+sensorInfo.sensorID);

            JLabel type = new JLabel(String.format(
                    "type: %s",
                    sensorInfo.sensorType
            ));
            type.setBorder(new CompoundBorder(type.getBorder(), margin));

            String measurementText = "nothing yet recorded.";
            String timeText = "";

            boolean valueFound = sensorInfo.unitOfMeasurement != null;
            if (valueFound) {
                measurementText = String.format(
                        "value: %.2f%s",
                        sensorInfo.newestValue,
                        sensorInfo.unitOfMeasurement
                );

                timeText = sensorInfo.timestampMeasurement.toLocalDateTime().format(
                        DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
                );

            }

            JLabel measurementLabel = new JLabel(measurementText);

            measurementLabel.setName("measurementLabel");
            measurementLabel.setBorder(new CompoundBorder(measurementLabel.getBorder(), margin));

            JLabel timeLabel = new JLabel(timeText);

            timeLabel.setName("timeLabel");
            timeLabel.setBorder(new CompoundBorder(timeLabel.getBorder(), margin));

            sensorPanel.add(type);
            sensorPanel.add(measurementLabel);
            sensorPanel.add(timeLabel);

            sensorPanel.setBorder(new TitledBorder(new LineBorder(Color.black), sensorInfo.location));

            sensorHashMap.put(sensorInfo.sensorID, sensorPanel);
            networkPanel.add(sensorPanel);
        }

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


    }

    public void update(NetworkOverviewRefreshStatement data) {
        if (data.eenheid == null) {
            return;
        }

        JPanel panel = sensorHashMap.get(data.sensorID);

        if (panel == null) {
            return;
        }

        for (Component unknownComponent :
                panel.getComponents()) {

            String name = unknownComponent.getName() != null?
                    unknownComponent.getName():
                    "";

            switch (name) {

                case "measurementLabel":
                    JLabel measurementLabel = (JLabel) unknownComponent;
                    measurementLabel.setText(String.format("%.2f", data.nieuwe_waarde));
                    break;

                case "timeLabel":
                    JLabel timeLabel = (JLabel) unknownComponent;
                    timeLabel.setText(data.tijdstip_meetwaarde.toLocalDateTime().format(
                            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
                            )
                    );
                    break;
            }
        }

        //contentPane.getComponents();
    }
}