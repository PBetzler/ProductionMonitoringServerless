package de.hdm.mi.db;

public abstract class DB_Manager_Abstract implements DB_Manager_Interface{
    protected DB_Connection_Interface dbConnection;

    public DB_Manager_Abstract(DB_Connection_Interface dbConnection) {
        this.dbConnection = dbConnection;
    }
}
