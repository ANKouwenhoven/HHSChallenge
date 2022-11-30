CREATE SCHEMA Greenite;

USE Greenite;

CREATE TABLE Gebruiker (
gebruikercode char(10) not null,  
voornaam varchar(75) not null,  
achternaam varchar(75) not null,
telefoonnummer varchar(12),  
email_adres varchar(100) not null, 
wachtwoord varchar(100) not null,
primary key (gebruikercode));

CREATE TABLE Netwerk (
netwerkID char(10) not null,  
netwerkadres varchar(100) not null,  
locatie_adres varchar(250) not null,
gebruikercode char(10) not null,
primary key (netwerkID),  
foreign key (gebruikercode) references Gebruiker (gebruikercode)
on delete no action
on update cascade);

CREATE TABLE Sensor (
sensorID char(20) not null, 
sensortype varchar(75) not null, 
locatie varchar(250), 
netwerkID char(10) not null,
primary key (sensorID),
foreign key (netwerkID) references Netwerk (netwerkID)
on delete cascade
on update cascade);

CREATE TABLE Meetwaarde (
sensorID char(20) not null,
tijdstip_meetwaarde TIMESTAMP not null,
grootheid varchar(40) not null, 
eenheid varchar(15) not null, 
waarde DOUBLE not null, 
primary key (sensorID, tijdstip_meetwaarde),
foreign key (sensorID) references Sensor (sensorID)
on delete no action
on update cascade);

CREATE TABLE Melding (
meldingcode char(20) not null,
bericht varchar(5535) not null,
tijdstip_meetwaarde TIMESTAMP not null,
sensorID char(20) not null,
tijdstip_melding TIMESTAMP not null,
primary key (meldingcode),
foreign key (sensorID, tijdstip_meetwaarde) references Meetwaarde (sensorID, tijdstip_meetwaarde)
on delete cascade
on update cascade); 
