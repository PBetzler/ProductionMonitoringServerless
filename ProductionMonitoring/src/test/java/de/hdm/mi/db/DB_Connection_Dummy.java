package de.hdm.mi.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Connection_Dummy extends DB_Standard_Connection_Abstract {
    public DB_Connection_Dummy(String url, String username, String password) {
        super(url, username, password);
    }

    public DB_Connection_Dummy() {
        super("", "", "");
    }

    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public boolean connect(String url, String user, String password) {
        return true;
    }

    @Override
    public int executeUpdate(String sqlCommand) throws SQLException {

        if (sqlCommand.contains("negative") || sqlCommand.contains("-1")) {
            return -1;
        }

        return 5;
    }

    @Override
    public ResultSet executeQuery(String sqlCommand) throws SQLException {
        return null;
    }
}
