CREATE SCHEMA Greenite;

USE Greenite;

CREATE TABLE Gebruiker (
gebruikercode int(10) not null,  
voornaam varchar(75) not null,  
achternaam varchar(75) not null,
telefoonnummer varchar(12),  
email_adres varchar(100) not null, 
wachtwoord varchar(100) not null,
primary key (gebruikercode));

CREATE TABLE Netwerk (
netwerkID int(10) not null,  
netwerkadres varchar(100) not null,  
locatie_adres varchar(250) not null,
gebruikercode int(10) not null,
primary key (netwerkID),  
foreign key (gebruikercode) references Gebruiker (gebruikercode)
on delete no action
on update cascade);

CREATE TABLE Sensor (
sensorID int(11) not null, 
sensortype varchar(75) not null, 
locatie varchar(250), 
netwerkID int(10) not null,
primary key (sensorID),
foreign key (netwerkID) references Netwerk (netwerkID)
on delete cascade
on update cascade);

create table meetwaarde (
meetwaardecode int(11) NOT NULL, 
sensorID int(11) NOT NULL, 
tijdstip_meetwaarde timestamp NOT NULL, 
grootheid varchar(75) NOT NULL, 
eenheid varchar(7) NOT NULL, 
waarde double NOT NULL,
primary key (meetwaardecode),
foreign key (sensorID) references sensor(sensorID)
on update cascade
on delete no action);

create table melding (
meldingcode int(11) NOT NULL, 
meetwaardecode int(11) NOT NULL, 
bericht varchar(5550) NOT NULL, 
tijdstip_melding timestamp NOT NULL,
primary key (meldingcode),
foreign key (meetwaardecode) references meetwaarde(meetwaardecode)
on update cascade
on delete no action); 
