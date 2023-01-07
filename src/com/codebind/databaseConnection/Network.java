package com.codebind.databaseConnection;

import java.sql.SQLException;

public class Network {
    public String gebruikercode;
    public String[] networkIds;

    public String networkID;
    public String networkadress;
    public String locationNetwork;

    String getGebruikercode() {
        return gebruikercode;
    }

    String[] getNetworkIds() {
        return networkIds;
    }

    String getNetworkID() {
        return networkID;
    }

    String getNetworkadress() {
        return networkadress;
    }

    String getLocationNetwork() {
        return locationNetwork;
    }

    Network() {}

    Network(String gebruikerCode, int aantal) {
        gebruikercode = gebruikerCode;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            networkIds = connectionMetDataBase.GetNetworkIDs(gebruikerCode, aantal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Network(String networkId) {
        networkID = networkId;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            Network network = connectionMetDataBase.GetNetworkInfo(networkId);
            gebruikercode = network.gebruikercode;
            networkadress = network.networkadress;
            locationNetwork = network.locationNetwork;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}