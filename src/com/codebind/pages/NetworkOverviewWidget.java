package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.NetworkOverview;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class NetworkOverviewWidget implements PageInterface {

    static EmptyBorder margin = new EmptyBorder(1,10,1,0);

    JPanel panel = new JPanel(new BorderLayout());
    JPanel contentPane = new JPanel(new GridLayout(-1, 1, 0, 8));

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

            boolean valueFound = sensorInfo.unitOfMeasurement != null;
            if (valueFound) {
                measurementText = String.format(
                        "value: %.2f%s",
                        sensorInfo.newestValue,
                        sensorInfo.unitOfMeasurement
                );
                JLabel timestampLabel = new JLabel(String.format(
                        "recorded on: %s",
                        sensorInfo.timestampMeasurement
                ));
            }
            JLabel measurementLabel = new JLabel(measurementText);
            measurementLabel.setBorder(new CompoundBorder(measurementLabel.getBorder(), margin));

            sensorPanel.add(type);
            sensorPanel.add(measurementLabel);
            if (valueFound) {
                JLabel timestampLabel = new JLabel(String.format(
                        "recorded on: %s",
                        sensorInfo.timestampMeasurement.toLocalDateTime().format(
                                DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
                        )
                ));
                timestampLabel.setBorder(new CompoundBorder(timestampLabel.getBorder(), margin));
                sensorPanel.add(timestampLabel);
            }

            sensorPanel.setBorder(new TitledBorder(new LineBorder(Color.black), sensorInfo.location));

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
}