USE HighScore;
CREATE TABLE HighScore (
	No INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Nama VARCHAR(50) NOT NULL,
	Player VARCHAR(30) NOT NULL,
	DateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Durasi VARCHAR(30),
    Status VARCHAR(50) NOT NULL,
    Level INT);