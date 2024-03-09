package de.maxi.teamsraked.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQL {
    static Connection con;

    static String host = "vpn.maximilianvonbeck.de";
    static String port = "3306";
    static String database = "ranks_teams";
    static String username = "ranks_teams";
    static String password = "pJ7.8M034(RtqH)0";

    public static void connect() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        if(!isConected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host+ ":" + port + "/" + database + "?autoReconnect=true",username, password);
                System.out.println("MySQL aufgebaut");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void disconnect() {
        if(isConected()) {
            try {
                con.close();
                System.out.println("MySQL geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean isConected() {
        return con != null;
    }

    public static Connection getConnection() {
        if(isConected()) {
            return con;
        }
        return null;
    }


}


