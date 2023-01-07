package com.codebind.databaseConnection;

import java.sql.*;


public class DataBaseConnection {
    Connection connection;

    public DataBaseConnection() throws Exception {
        connection = createConnection();
    }

    public Connection createConnection() throws Exception {
        String connectionUrl = "jdbc:mysql://localhost/greenite";

        try {
            return DriverManager.getConnection(connectionUrl, "root", "test");
        } catch (SQLException e) {
            System.out.println("failed to connect using the password test");
        }

        try {
            return DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException e) {
            System.out.println("failed to connect using no password");
        }

        throw new Exception("failed to make an connection");
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        return this.connection.prepareStatement(sql);
    }


    public String GetGebruikerCode(String emailAdres, String passWord) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            String selectGebruikerCode = "Select gebruikercode from gebruiker " +
                    "Where email_adres = '" + emailAdres + "'" +
                    " AND Where wachtwoord = '" + passWord + "'";
            resultSet = statement.executeQuery(selectGebruikerCode);

            while (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return null;
    }

    public Gebruiker GetGebruikrInfo(String gebruikerCode) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            String selectGebruikerInfo = "Select * from gebruiker " +
                    "Where gebruikercode = '" + gebruikerCode + "'";
            resultSet = statement.executeQuery(selectGebruikerInfo);

            while (resultSet.next()) {
                Gebruiker gebruiker = new Gebruiker();
                gebruiker.email_adres = resultSet.getString(5);
                gebruiker.password = resultSet.getString(6);
                gebruiker.naam = resultSet.getString(2) + " " + resultSet.getString(3);
                gebruiker.telefoonnummer = resultSet.getString(4);
                return gebruiker;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Meetwaarde GetMeetwaardeInfo(String sensorID, String tijdstip) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            String selectGebruikerInfo = "Select * from meetwaarde " +
                    "Where sensorID = '" + sensorID + "'" +
                    "AND tijdstip_meetwaarde = '" + tijdstip + "'";
            resultSet = statement.executeQuery(selectGebruikerInfo);

            while (resultSet.next()) {
                Meetwaarde meetwaarde = new Meetwaarde();
                meetwaarde.grootheid = resultSet.getString(3);
                meetwaarde.eenheid = resultSet.getString(4);
                meetwaarde.waarde = resultSet.getString(5);
                return meetwaarde;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return null;
    }

    public Melding GetMeldingInfo(Meetwaarde meetwaarde) {
        ResultSet resultSet = null;
        String sensorId = meetwaarde.sensorId;
        String tijdstipMeetwaarde = meetwaarde.tijdstipMeetwaarde;
        try {
            Statement statement = connection.createStatement();
            String selectGebruikerInfo = "Select * from melding " +
                    "Where sensorID = '" + sensorId + "'" +
                    "AND tijdstip_meetwaarde = '" + tijdstipMeetwaarde + "'";
            resultSet = statement.executeQuery(selectGebruikerInfo);

            while (resultSet.next()) {
                Melding melding = new Melding();
                melding.meldingcode = resultSet.getString(1);
                melding.tijdstipMelding = resultSet.getString(5);
                melding.bericht = resultSet.getString(2);
                melding.sensorID = resultSet.getString(4);
                melding.tijdstipMeetwaarde = resultSet.getString(3);
                return melding;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return null;
    }

    public String[] GetNetworkIDs(String gebruikercode, int aantal)  {
        ResultSet resultSet = null;
        int i = 0;

        try {
            Statement statement = connection.createStatement();
            String selectSensors = "Select netwerkID from netwerk " +
                    "Where gebruikercode = '" + gebruikercode + "'";
            resultSet = statement.executeQuery(selectSensors);

            String[] sensorIDS = new String[aantal];
            while (resultSet.next()) {
                sensorIDS [i++] = resultSet.getString(1);
            }
            return sensorIDS;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public Network GetNetworkInfo(String networkID) {
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            String selectGebruikerInfo = "Select * from netwerk " +
                    "Where netwerkID = '" + networkID + "'";
            resultSet = statement.executeQuery(selectGebruikerInfo);

            while (resultSet.next()) {
                Network network = new Network();
                network.networkadress = resultSet.getString(2);
                network.locationNetwork = resultSet.getString(3);
                network.gebruikercode = resultSet.getString(4);
                return network;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return null;
    }


    public String[] GetSensorIDs(String netwerkID, int aantal)  {
        ResultSet resultSet = null;
        int i = 0;

        try {
            Statement statement = connection.createStatement();
            String selectSensors = "Select sensorID from sensor " +
                    "Where netwerkID = '" + netwerkID + "'";
            resultSet = statement.executeQuery(selectSensors);

            String[] sensorIDS = new String[aantal];
            while (resultSet.next()) {
                sensorIDS [i++] = resultSet.getString(1);
            }
            return sensorIDS;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public Sensor GetSensorInfo(String sensorID) {
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            String selectGebruikerInfo = "Select * from sensor " +
                    "Where sensorID = '" + sensorID + "'";
            resultSet = statement.executeQuery(selectGebruikerInfo);

            while (resultSet.next()) {
                Sensor sensor = new Sensor();
                sensor.sensorType = resultSet.getString(2);
                sensor.sensorLocation = resultSet.getString(3);
                sensor.netwerkId = resultSet.getString(4);
                return sensor;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return null;
    }


    public boolean login(String username, String pwd) {
        String rightPassord = "";
        try {
            ResultSet resultSet = null;
            Statement statement = null;
            statement = connection.createStatement();
            String selectPassword = "Select wachtwoord from gebruiker " +
                    "Where email_adres = '" + username + "'";
            resultSet = statement.executeQuery(selectPassword);
            resultSet.next();
            rightPassord = resultSet.getString(1);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pwd.equals(rightPassord);
    }

}