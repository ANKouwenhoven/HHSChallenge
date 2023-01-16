package com.codebind.databaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class NetworkOverviewRefreshStatement {
    public String netwerkID;
    public String sensorID;
    public double nieuwe_waarde;
    public String eenheid;
    public Timestamp tijdstip_meetwaarde;

    public String toString() {
        return String.format(
                """
                networkID = %s
                sensorID = %s
                nieuwe_waarde = %.2f
                eenheid = %s
                tijdstip_meetwaarde = %s
                """,

                netwerkID == null?
                        "":
                        netwerkID,

                sensorID == null?
                    "":
                    sensorID,
                nieuwe_waarde,

                eenheid == null?
                        "":
                        eenheid,

                tijdstip_meetwaarde == null?
                    "":
                    tijdstip_meetwaarde);
    }

    public NetworkOverviewRefreshStatement() {};

    public static ArrayList<NetworkOverviewRefreshStatement> getFromUserID(DataBaseConnection db, String userID) throws SQLException {
        ArrayList<NetworkOverviewRefreshStatement> res = new ArrayList<>();

        PreparedStatement preparedStatement = db.getPreparedStatement(
                "SELECT Netwerk.netwerkID, Sensor.sensorID, lw.nieuwe_waarde," +
                        "lw.eenheid, lw.tijdstip_meetwaarde " +
                        "FROM Netwerk " +

                        "LEFT JOIN Sensor ON Netwerk.netwerkID = Sensor.netwerkID " +

                        "LEFT JOIN (" +
                        "SELECT waarde as nieuwe_waarde, eenheid, tijdstip_meetwaarde, sensorID " +
                        "FROM Meetwaarde " +
                        "ORDER BY tijdstip_meetwaarde DESC " +
                        "LIMIT 1) lw " +
                        "ON Sensor.sensorID = lw.sensorID " +

                        "WHERE Netwerk.gebruikercode = ? "+
                        "ORDER BY Netwerk.netwerkID, Sensor.sensorID;");

        preparedStatement.setString(1, userID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            NetworkOverviewRefreshStatement nw = new NetworkOverviewRefreshStatement();

            nw.eenheid = resultSet.getString("eenheid");
            nw.nieuwe_waarde = resultSet.getDouble("nieuwe_waarde");
            nw.tijdstip_meetwaarde = resultSet.getTimestamp("tijdstip_meetwaarde");
            nw.sensorID = resultSet.getString("sensorID");
            nw.netwerkID = resultSet.getString("netwerkID");

            res.add(nw);
        }

        return res;
    }
}
