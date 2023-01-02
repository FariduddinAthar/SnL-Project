package Leaderboard;

import helper.dbKoneksi;
import entity.Player;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Outcome {
    private String url, uname, pass, query;
    private Statement stmt;
    private Connection con;
    
    public Outcome(){
        url = "jdbc:mysql://localhost/Leaderboard";
        uname ="root";
        pass = "";
        this.setConnectionAndStatement();
    }
    
    private void setConnectionAndStatement(){
        try {
            con = DriverManager.getConnection(url, url, pass);
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.err.print("Error inserting score:" + ex.getMessage());
            System.exit(1);
        }
    }
    
        public void SaveData(Player player){{
        try {
            query = "INSERT INTO Leaderboard VALUES (NULL, '%s', '%s', '%s')";
            query = String.format(
                query,
                Player.getId(),
                Player.getNama(),
                Player.getRank(),
                Player.getDurasi());
            stmt.executeUpdate(query);
        System.out.println("Succesfully added data");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
            System.exit(1);
        }
    }
    
    public ArrayList<Outcome> getAll(){
        ArrayList<Outcome> allOutcome = new ArrayList<>();
        try {
            query = "SELECT * FROM Leaderboard";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                allOutcome.add(new Outcome(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4))
                );
            }
        } catch (SQLException ex){
            System.err.print("Error getting the score: " + ex.getMessage());
            System.exit(1);
        }
        return allOutcome;
    }
    
}
