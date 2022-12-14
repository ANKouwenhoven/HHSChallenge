package com.codebind.databaseConnection;

import java.sql.SQLException;

public class Melding {
    public String meldingcode;
    public String bericht;
    public String tijdstipMelding;
    public String tijdstipMeetwaarde;
    public String sensorID;

    Melding() {}

    Melding(Meetwaarde meetwaarde) {
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            Melding melding = connectionMetDataBase.GetMeldingInfo(meetwaarde);
            meldingcode = melding.meldingcode;
            bericht = melding.bericht;
            tijdstipMelding = melding.tijdstipMelding;
            tijdstipMeetwaarde = melding.tijdstipMeetwaarde;
            sensorID = melding.sensorID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}