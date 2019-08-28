package de.hdm.mi.db;

import de.hdm.mi.db.DB_Connection_Interface;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public abstract class DB_Standard_Connection_Abstract implements DB_Connection_Interface {

    private static final Logger logger = LogManager.getLogger(DB_Standard_Connection_Abstract.class);

    protected String url;
    protected String username;
    protected String password;

    protected Connection conn = null;

    public DB_Standard_Connection_Abstract(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public boolean setPassword(String password) {
        if (password != null) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean setUrl(String url) {
        if (url != null) {
            this.url = url;
            return true;
        }
        return false;
    }

    public boolean setUsername(String username) {
        if (username != null) {
            this.username = username;
            return true;
        }
        return false;
    }


    public boolean disconnect() {
        if (conn == null) {
            logger.log(Level.INFO, "Error, tried to close non existing db connection");
            return true;
        }

        try {
            if (!conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Got an SQL exception! couldnt check if connection is closed! " + ex.getMessage());
            return false;
        }

        return true;
    }

    public boolean isConnected() {
        if (conn != null) {
            try {
                return !conn.isClosed();
            } catch (SQLException ex) {
                logger.log(Level.ERROR, "Got an SQL exception! couldnt check if connection is closed! " + ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public ResultSet executeQuery(String sqlCommand) throws SQLException {
        if (sqlCommand.isEmpty() || sqlCommand == " ") {
            return null;
        }

        if (!isConnected()) {
            connect();
        }

        try (Statement statement = conn.createStatement()) {


            ResultSet result = statement.executeQuery(sqlCommand);
            statement.close();

            return result;

        } catch (SQLTimeoutException ex) {
            logger.log(Level.ERROR, "Got an SQL timeout! " + ex.getMessage());
            throw ex;

        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Got an SQL exception! couldnt check if connection is closed! " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public int executeUpdate(String sqlCommand) throws SQLException {
        if (sqlCommand.isEmpty() || sqlCommand == " ") {
            return -1;
        }

        if (!isConnected()) {
            connect();
        }

        try (PreparedStatement statement = conn.prepareStatement(sqlCommand,
                Statement.RETURN_GENERATED_KEYS)) {


            statement.executeUpdate();

            ResultSet resultID = statement.getGeneratedKeys();

            if (!resultID.next()) {
                logger.warn( "got no result key!");
                statement.close();
                return -1;
            } else {
                statement.close();
                return resultID.getInt("ID");
            }

        } catch (SQLTimeoutException ex) {
            logger.log(Level.ERROR, "Got an SQL timeout! " + ex.getMessage());
            throw ex;

        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Got an SQL exception! could not check if connection is closed! " + ex.getMessage());
            throw ex;
        }
    }
}
