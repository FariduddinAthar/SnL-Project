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
        url = "jdbc:mysql://localhost/University";
        uname ="root";
        pass = "";
        this.setConnection();
    }
    
    private void setConnection(){
        try {
            con = DriverManager.getConnection(url, url, pass);
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
            System.exit(1);
        }
    }
    
        public void SaveData(Player player){{
        try {
            query = "INSERT INTO Student VALUES (NULL, '%s', '%s')";
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
    
    public ArrayList<Player> getAll(){
        ArrayList<Player> allPlayer = new ArrayList<>();
        try {
            query = "SELECT * FROM Leaderboard";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                allPlayer.add(new Player(
                        rs.getString(2),
                        rs.getString(3))
                );
            }
        } catch (SQLException ex){
            System.err.print("Error getting data: " + ex.getMessage());
            System.exit(1);
        }
        return allPlayer;
    }
    
}
