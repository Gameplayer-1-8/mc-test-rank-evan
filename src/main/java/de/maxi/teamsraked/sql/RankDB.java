package de.maxi.teamsraked.sql;

import de.maxi.teamsraked.rank.Rank;
import de.maxi.teamsraked.rank.RankType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankDB {
    public static void addPlayerToDB(String uuid, String playerName){
        if(!isPlayerInDB(uuid)) {
            try {
                PreparedStatement ps = MySQL.con.prepareStatement("INSERT INTO ranks (UUID,name,rank) VALUES (?,?,?)");
                ps.setString(1, uuid);
                ps.setString(2, playerName);
                ps.setString(3, RankType.PLAYER.name());
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean isPlayerInDB(String uuid) {
        try {
            PreparedStatement ps = MySQL.con.prepareStatement("SELECT * FROM ranks WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  false;
    }

    public static Rank getRankOfPlayer(String uuid){
        try {
            PreparedStatement ps = MySQL.con.prepareStatement("SELECT * FROM ranks WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Rank(RankType.valueOf(rs.getString("rank")));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void updatePlayerRank(String uuid, String playerName, RankType rt){
        try {
            PreparedStatement ps = MySQL.con.prepareStatement("UPDATE ranks SET name = ?, rank = ? WHERE UUID = ?");
            ps.setString(1, playerName);
            ps.setString(2, rt.name());
            ps.setString(3, uuid);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
