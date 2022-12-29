package com.codebind.databaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class NetworkOverview {
    public String networkID;
    public String networkLocation;
    public String ownerID;
    public sensorInformation[] sensorsInformation;


    public static class sensorInformation {
        public String sensorID;
        public String sensorType;
        public String location;
        public double newestValue;
        public String unitOfMeasurement;
        public Timestamp timestampMeasurement;

        public sensorInformation() {}
        public sensorInformation(ResultSet resultSet) throws SQLException {
            this.sensorID = resultSet.getString("sensorID");
            this.location = resultSet.getString("locatie");
            this.sensorType = resultSet.getString("sensortype");
            this.unitOfMeasurement = resultSet.getString("eenheid");
            this.timestampMeasurement = resultSet.getTimestamp("tijdstip_meetwaarde");
            this.newestValue = resultSet.getDouble("nieuwe_waarde");
        }
    }

    public NetworkOverview() {}

    public NetworkOverview(DataBaseConnection dataBaseConnection, String netwerkID) throws SQLException {

        PreparedStatement statement = dataBaseConnection.getPreparedStatement(
                "SELECT Netwerk.netwerkID, Netwerk.locatie_adres, Netwerk.gebruikercode, " +
                        "Sensor.sensorID, Sensor.sensortype, Sensor.locatie, lw.nieuwe_waarde," +
                        "lw.eenheid, lw.tijdstip_meetwaarde " +
                        "FROM Netwerk " +

                        "LEFT JOIN Sensor ON Netwerk.netwerkID = Sensor.netwerkID " +

                        "LEFT JOIN (" +
                        "SELECT waarde as nieuwe_waarde, eenheid, tijdstip_meetwaarde, sensorID " +
                        "FROM Meetwaarde " +
                        "ORDER BY tijdstip_meetwaarde DESC " +
                        "LIMIT 1) lw " +
                        "ON Sensor.sensorID = lw.sensorID " +

                        "ORDER BY Netwerk.netwerkID, Sensor.sensorID;"
                        );

        statement.setString(1, netwerkID);
        ResultSet resultSet = statement.executeQuery();
    }

    public static NetworkOverview[] getAllNetworkOverview(DataBaseConnection db) throws SQLException {

        PreparedStatement preparedStatement = db.getPreparedStatement(
                "SELECT Netwerk.netwerkID, Netwerk.locatie_adres, Netwerk.gebruikercode, " +
                        "Sensor.sensorID, Sensor.sensortype, Sensor.locatie, lw.nieuwe_waarde," +
                        "lw.eenheid, lw.tijdstip_meetwaarde " +
                        "FROM Netwerk " +

                        "LEFT JOIN Sensor ON Netwerk.netwerkID = Sensor.netwerkID " +

                        "LEFT JOIN (" +
                        "SELECT waarde as nieuwe_waarde, eenheid, tijdstip_meetwaarde, sensorID " +
                        "FROM Meetwaarde " +
                        "ORDER BY tijdstip_meetwaarde DESC " +
                        "LIMIT 1) lw " +
                        "ON Sensor.sensorID = lw.sensorID " +

                        "ORDER BY Netwerk.netwerkID, Sensor.sensorID;");

        ResultSet resultSet = preparedStatement.executeQuery();


        // setup
        boolean hasFirstRow = resultSet.next();
        if (!hasFirstRow) {
            return null;
        }

        ArrayList<NetworkOverview> networks = new ArrayList<>();
        NetworkOverview currentNetwork = new NetworkOverview();

        currentNetwork.networkID = resultSet.getString("netwerkID");
        currentNetwork.networkLocation = resultSet.getString("locatie_adres");
        currentNetwork.ownerID =resultSet.getString("gebruikercode");

        ArrayList<sensorInformation> sensorsInfo = new ArrayList<>();
        if (resultSet.getString("sensorID") != null) {
            sensorsInfo.add(new sensorInformation(resultSet));
        }

        while (resultSet.next()) {
            String newID = resultSet.getString("netwerkID");

            if (!currentNetwork.networkID.equals(newID)) {

                sensorInformation[] sensorInformationArray = new sensorInformation[sensorsInfo.size()];
                for (int i = 0; i < sensorsInfo.size(); i++) {
                     sensorInformationArray[i] = sensorsInfo.get(i);
                }
                currentNetwork.sensorsInformation = sensorInformationArray;
                networks.add(currentNetwork);

                currentNetwork = new NetworkOverview();
                sensorsInfo.clear();

                currentNetwork.networkID = resultSet.getString("netwerkID");
                currentNetwork.networkLocation = resultSet.getString("locatie_adres");
                currentNetwork.ownerID = resultSet.getString("gebruikercode");
            }

            if (resultSet.getString("sensorID") != null) {
                sensorsInfo.add(new sensorInformation(resultSet));
            }

        }

        sensorInformation[] sensorInformationArray = new sensorInformation[sensorsInfo.size()];
        for (int i = 0; i < sensorsInfo.size(); i++) {
            sensorInformationArray[i] = sensorsInfo.get(i);
        }

        currentNetwork.sensorsInformation = sensorInformationArray;
        sensorsInfo.clear();

        networks.add(currentNetwork);

        NetworkOverview[] networkOverviews = new NetworkOverview[networks.size()];
        for (int i = 0; i < networks.size(); i++) {
            networkOverviews[i] = networks.get(i);
        }

        return networkOverviews;
    }

    public static NetworkOverview[] getAllNetworkOverviewFromOwner (DataBaseConnection db, String ownerID) throws SQLException {

        PreparedStatement preparedStatement = db.getPreparedStatement(
                "SELECT Netwerk.netwerkID, Netwerk.locatie_adres, Netwerk.gebruikercode, " +
                        "Sensor.sensorID, Sensor.sensortype, Sensor.locatie, lw.nieuwe_waarde," +
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

        preparedStatement.setString(1, ownerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean hasFirstRow = resultSet.next();
        if (!hasFirstRow) {
            return new NetworkOverview[0];
        }

        ArrayList<NetworkOverview> networks = new ArrayList<>();
        NetworkOverview currentNetwork = new NetworkOverview();

        currentNetwork.networkID = resultSet.getString("netwerkID");
        currentNetwork.networkLocation = resultSet.getString("locatie_adres");
        currentNetwork.ownerID =resultSet.getString("gebruikercode");

        ArrayList<sensorInformation> sensorsInfo = new ArrayList<>();
        if (resultSet.getString("sensorID") != null) {
            sensorsInfo.add(new sensorInformation(resultSet));
        }

        while (resultSet.next()) {
            String newID = resultSet.getString("netwerkID");

            if (!currentNetwork.networkID.equals(newID)) {

                sensorInformation[] sensorInformationArray = new sensorInformation[sensorsInfo.size()];
                for (int i = 0; i < sensorsInfo.size(); i++) {
                    sensorInformationArray[i] = sensorsInfo.get(i);
                }
                currentNetwork.sensorsInformation = sensorInformationArray;
                networks.add(currentNetwork);

                currentNetwork = new NetworkOverview();
                sensorsInfo.clear();

                currentNetwork.networkID = resultSet.getString("netwerkID");
                currentNetwork.networkLocation = resultSet.getString("locatie_adres");
                currentNetwork.ownerID = resultSet.getString("gebruikercode");
            }

            if (resultSet.getString("sensorID") != null) {
                sensorsInfo.add(new sensorInformation(resultSet));
            }

        }

        sensorInformation[] sensorInformationArray = new sensorInformation[sensorsInfo.size()];
        for (int i = 0; i < sensorsInfo.size(); i++) {
            sensorInformationArray[i] = sensorsInfo.get(i);
        }

        currentNetwork.sensorsInformation = sensorInformationArray;
        sensorsInfo.clear();

        networks.add(currentNetwork);

        NetworkOverview[] networkOverviews = new NetworkOverview[networks.size()];
        for (int i = 0; i < networks.size(); i++) {
            networkOverviews[i] = networks.get(i);
        }

        return networkOverviews;
    }

    /**
     * debug code
     */
    public String toString() {

        String sensorString = "";
        for (sensorInformation s :
                sensorsInformation) {
            sensorString = sensorString.concat(String.format(
                    """
                    +---sensorID:     %s
                    |   location:     %s
                    |   type:         %s
                    |   newest value: %.2f
                    |   unit:         %s
                    |   timestamp:    %s
                    |
                    """,
                    s.sensorID,
                    s.location,
                    s.sensorType,
                    s.newestValue,
                    s.unitOfMeasurement,
                    s.timestampMeasurement
            ));
        }

        return String.format(
                """
                networkOverview object
                networkID: %s
                network location: %s
                ownerID: %s
                sensorInformation[%d]
                %s""",
                networkID,
                networkLocation,
                ownerID,
                sensorsInformation.length,
                sensorString
                );
    }
}