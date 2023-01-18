package com.codebind.databaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Meetwaarde {

    public int meetwaardeCode;
    public int sensorId;

    public Timestamp tijdstipMeetwaarde;
    public double waarde;

    public String grootheid;
    public String eenheid;

    public String toString() {
        return String.format(
                """
                meetwaardeCode     = %d
                sensorId           = %d
                tijdstipMeetwaarde = %s
                double waarde      = %.2f

                grootheid          = %s
                eenheid            = %s
                """,
                meetwaardeCode,
                sensorId,
                tijdstipMeetwaarde.toLocalDateTime().format(
                        DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
                ),
                waarde,
                grootheid,
                eenheid
        );
    }

    Meetwaarde() {}

    /**
     * getLatestFromSensorID
     */
    public Meetwaarde(DataBaseConnection db, int sensorID) throws SQLException {
        PreparedStatement statement = db.getPreparedStatement(
                "SELECT * FROM meetwaarde " +
                        "where sensorID = ? " +
                        "ORDER BY tijdstip_meetwaarde " +
                        "LIMIT 1;"
        );

        statement.setInt(1, sensorID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            meetwaardeCode = resultSet.getInt("meetwaardeCode");
            sensorId       = resultSet.getInt("sensorID");

            waarde         = resultSet.getDouble("waarde");
            grootheid      = resultSet.getString("grootheid");
            eenheid        = resultSet.getString("eenheid");
            tijdstipMeetwaarde = resultSet.getTimestamp("tijdstip_meetwaarde");
        }

    }

   /* Meetwaarde(String sensorID, String tijdstip) {
        sensorId = sensorID;
        tijdstipMeetwaarde = tijdstip;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            Meetwaarde meetwaarde = connectionMetDataBase.GetMeetwaardeInfo(sensorID, tijdstip);
            grootheid = meetwaarde.grootheid;
            eenheid = meetwaarde.eenheid;
            waarde = meetwaarde.waarde;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } */
}
