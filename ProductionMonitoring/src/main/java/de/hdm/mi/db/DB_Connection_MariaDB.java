package de.hdm.mi.db;

import java.sql.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DB_Connection_MariaDB extends DB_Standard_Connection_Abstract {
    private static final Logger logger = LogManager.getLogger(DB_Connection_MariaDB.class);

    public DB_Connection_MariaDB() {
        this("jdbc:mariadb://141.62.65.117:3306/ProduktionsUeberwachung", "nutzer", "passwort");
    }

    public DB_Connection_MariaDB(String url, String username, String password){
        super(url, username, password);
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
                    logger.log(Level.ERROR, "Error, couldnt disconnect from db!");
                    return false;
                }
            }
            conn = DriverManager.getConnection(url, username, password);
            //logger.log(Level.INFO,"Connected to DB!");


            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Got an SQL exception! " + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Fatal Error, couldnt load DB Driver!");
        }

        return false;
    }


}
