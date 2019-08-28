package de.hdm.mi.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DB_Connection_Interface {

    public boolean connect();
    public boolean connect(String url, String user, String password);
    public boolean disconnect();
    public boolean isConnected();

    public ResultSet executeQuery(String sqlCommand) throws SQLException;

    public int executeUpdate(String sqlCommand) throws SQLException;
}
