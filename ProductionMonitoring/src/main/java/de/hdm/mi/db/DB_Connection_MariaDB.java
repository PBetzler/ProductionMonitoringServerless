package de.hdm.mi.db;

import java.sql.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DB_Connection_MariaDB extends DB_Standard_Connection_Abstract {
    private static final Logger logger = LogManager.getLogger(DB_Connection_MariaDB.class);
    private static String placeholderDBUrl = "url";
    private static String placeholderDBUsername = "nutzer";
    private static String placeholderDBPassword = "passwort";

    public DB_Connection_MariaDB() {
        this(placeholderDBUrl, placeholderDBUsername, "passwort");
    }

    public DB_Connection_MariaDB(String url, String username, String password){
        super((url!=null && url != "") ? url: placeholderDBUrl, (username != null && !username.isEmpty()) ? username : placeholderDBUsername, (password != null) ? password: placeholderDBPassword);
    }

    public boolean connect() {
        return connect(url, username, password);
    }

    public boolean connect(String url, String username, String password) {
        if (url.isEmpty() || url == " ") {
            return false;
        } else if (username.isEmpty()|| username == " ") {
            return false;
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            if (isConnected()) {
                if (!disconnect()) {
                    logger.log(Level.ERROR, "Error, could not disconnect from db!");
                    return false;
                }
            }
            conn = DriverManager.getConnection(url, username, password);
            logger.trace("Connected to DB!");


            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Got an SQL exception! " + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Fatal Error, could not load DB Driver!");
        }

        return false;
    }


}
