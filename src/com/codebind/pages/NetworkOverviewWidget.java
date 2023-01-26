package com.codebind.pages;

import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.Meetwaarde;
import com.codebind.databaseConnection.NetworkOverview;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NetworkOverviewWidget implements PageInterface {

    static EmptyBorder margin = new EmptyBorder(1,10,1,0);

    JPanel panel = new JPanel(new BorderLayout());
    JPanel contentPane = new JPanel(new GridLayout(-1, 1, 0, 8));
    ContentManager contentManager;

    ArrayList<JPanel> sensorArray = new ArrayList<>();


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
            sensorPanel.setName(String.valueOf(sensorInfo.sensorID));

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

            sensorArray.add(sensorPanel);
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

    public void update() {
        //sorry for all the nesting
        for (JPanel sensorPanel :
                sensorArray) {

            int sensorID = Integer.parseInt( sensorPanel.getName() );

            try {
                //System.out.print("checking " + sensorID );
                Meetwaarde newData = new Meetwaarde(contentManager.getDataBaseConnection(), sensorID);

                if (newData.grootheid == null) {
                    //System.out.println(" no data");
                    continue;
                }
                System.out.println(" found");


                for (Component unknownComponent :
                        sensorPanel.getComponents()) {

                    String name = unknownComponent.getName() != null?
                            unknownComponent.getName():
                            "";

                    switch (name) {
                        case "measurementLabel":
                            JLabel measurementLabel = (JLabel) unknownComponent;
                            measurementLabel.setText(String.format("%.2f", newData.waarde));
                            break;

                        case "timeLabel":
                            JLabel timeLabel = (JLabel) unknownComponent;
                            timeLabel.setText(newData.tijdstipMeetwaarde.toLocalDateTime().format(
                                            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
                                    )
                            );
                            break;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}