package com.codebind.databaseConnection;

import java.sql.SQLException;

public class Sensor {
    public String sensorId;
    public String netwerkId;
    public String[] sensorIds;
    public String sensorType;
    public String sensorLocation;

    Sensor() {}

     Sensor(String netwerkID, int aantal) {
        netwerkId = netwerkID;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            sensorIds = connectionMetDataBase.GetSensorIDs(netwerkID, aantal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Sensor(String sensorID) {
        sensorId = sensorID;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            Sensor sensor = connectionMetDataBase.GetSensorInfo(sensorID);
            sensorType = sensor.sensorType;
            sensorLocation = sensor.sensorLocation;
            netwerkId = sensor.netwerkId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}