package com.codebind.databaseConnection;

import java.sql.SQLException;

public class Gebruiker {
    public String gebruikerCode;
    public String naam;
    public String telefoonnummer;
    public String email_adres;
    public String password;

    Gebruiker() {}

    Gebruiker(String emailAdres, String passWord) {
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            gebruikerCode = connectionMetDataBase.GetGebruikerCode(emailAdres, passWord);
            Gebruiker gebruiker = new Gebruiker(gebruikerCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    Gebruiker(String gebruikercode) {
        gebruikerCode = gebruikercode;
        try {
            DataBaseConnection connectionMetDataBase = new DataBaseConnection();
            Gebruiker gebuiker = connectionMetDataBase.GetGebruikrInfo(gebruikercode);
            naam = gebuiker.naam;
            telefoonnummer = gebuiker.telefoonnummer;
            email_adres = gebuiker.email_adres;
            password = gebuiker.password;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}