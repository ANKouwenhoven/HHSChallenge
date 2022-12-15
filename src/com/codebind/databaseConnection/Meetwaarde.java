package com.codebind.databaseConnection;

import java.sql.SQLException;

public class Meetwaarde {
    public String sensorId;
    public String tijdstipMeetwaarde;
    public String grootheid;
    public String eenheid;
    public String waarde;
    Meetwaarde() {}

    Meetwaarde(String sensorID, String tijdstip) {
        sensorId = sensorID;
        tijdstipMeetwaarde = tijdstip;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            Meetwaarde meetwaarde = connectionMetDataBase.GetMeetwaardeInfo(sensorID, tijdstip);
            grootheid = meetwaarde.grootheid;
            eenheid = meetwaarde.eenheid;
            waarde = meetwaarde.waarde;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
