CREATE DATABASE Leaderboard;
USE Leaderboard;
CREATE TABLE Leaderboard (
  `Id` int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  `Name` varchar(255) DEFAULT NULL,
  `Rank` varchar(255) DEFAULT NULL,
  `Durasi` varchar(255) DEFAULT NULL
); 