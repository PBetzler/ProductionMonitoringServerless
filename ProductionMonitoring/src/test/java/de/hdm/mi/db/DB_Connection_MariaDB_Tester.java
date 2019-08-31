package de.hdm.mi.db;
import org.junit.Assert;
import org.junit.Test;

public class DB_Connection_MariaDB_Tester {

    @Test
    public void constructorTest1() {
        DB_Connection_MariaDB db = new DB_Connection_MariaDB(null, null, null);

        Assert.assertEquals("url", db.getUrl());
        Assert.assertEquals("nutzer", db.getUsername());
        Assert.assertEquals("passwort", db.getPassword());
    }

    @Test
    public void constructorTest2() {
        DB_Connection_MariaDB db = new DB_Connection_MariaDB("", "", "");

        Assert.assertEquals("url", db.getUrl());
        Assert.assertEquals("nutzer", db.getUsername());
        Assert.assertEquals("", db.getPassword());
    }

    @Test
    public void constructorTest3() {
        DB_Connection_MariaDB db = new DB_Connection_MariaDB("a", "b", "c");

        Assert.assertEquals("a", db.getUrl());
        Assert.assertEquals("b", db.getUsername());
        Assert.assertEquals("c", db.getPassword());
    }
}
