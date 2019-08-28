package de.hdm.mi.helper;

import de.hdm.mi.db.DB_Entry;
import de.hdm.mi.db.DB_Entry_Valuetypes;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class SQL_Creator_Helper_Tester {

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createInsertStatementNegativeTest1() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createInsertStatement("", null);
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createInsertStatementNegativeTest2() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createInsertStatement("random", null);
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createInsertStatementNegativeTest3() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createInsertStatement("random", new LinkedList<>());
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createInsertStatementNegativeTest4() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createInsertStatement("random", new LinkedList<DB_Entry>());
        Assert.fail(); //should not have come here
    }

    @Test
    public void createInsertStatementPositiveTest1() throws SQL_Statement_Creation_Exception {
        DB_Entry entry = new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer);
        String tableName = "random";


        String expectedResult = "INSERT INTO " + tableName + " (" + entry.getName() + ")" + " VALUES (" + entry.getValue() + ")";
        String result = SQL_Creator_Helper.createInsertStatement(tableName, Arrays.asList(entry));

        Assert.assertEquals(expectedResult, result);
    }




    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createSelectStatementNegativeTest1() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createSelectStatement("", null, null);
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createSelectStatementNegativeTest2() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createSelectStatement("random", null);
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createSelectStatementNegativeTest3() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createSelectStatement("random", new LinkedList<>());
        Assert.fail(); //should not have come here
    }

    @Test
    public void createSelectStatementPositiveTest1() throws SQL_Statement_Creation_Exception {
        String row = "row";
        String tableName = "random";


        String expectedResult = "SELECT " + row + " FROM " + tableName;
        String result = SQL_Creator_Helper.createSelectStatement(tableName, Arrays.asList(row));

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void createSelectStatementPositiveTest2() throws SQL_Statement_Creation_Exception {
        String row = "row";
        String row2 = "row2";
        String tableName = "random";


        String expectedResult = "SELECT " + row +", " +row2 + " FROM " + tableName;
        String result = SQL_Creator_Helper.createSelectStatement(tableName, Arrays.asList(row, row2));

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void createSelectStatementPositiveTest3() throws SQL_Statement_Creation_Exception {
        String row = "row";
        String row2 = "row2";
        String tableName = "random";


        String result1 = SQL_Creator_Helper.createSelectStatement(tableName, Arrays.asList(row, row2), null);
        String result = SQL_Creator_Helper.createSelectStatement(tableName, Arrays.asList(row, row2));

        Assert.assertEquals(result1, result);
    }

    @Test
    public void createSelectStatementPositiveTest4() throws SQL_Statement_Creation_Exception {
        String row = "row";
        String row2 = "row2";
        String tableName = "random";
        DB_Entry condition = new DB_Entry("condition", "10", DB_Entry_Valuetypes.Integer);

        String expectedResult = "SELECT " + row +", " +row2 + " FROM " + tableName + " WHERE " + condition.getName() + " = " + condition.getValue();;
        String result = SQL_Creator_Helper.createSelectStatement(tableName, Arrays.asList(row, row2), condition);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void createSelectStatementPositiveTest5() throws SQL_Statement_Creation_Exception {
        String row = "row";
        String row2 = "row2";
        String tableName = "random";
        DB_Entry condition = new DB_Entry("condition", "hallo", DB_Entry_Valuetypes.String);

        String expectedResult = "SELECT " + row +", " +row2 + " FROM " + tableName + " WHERE " + condition.getName() + " = \'" + condition.getValue()+"\'";
        String result = SQL_Creator_Helper.createSelectStatement(tableName, Arrays.asList(row, row2), condition);

        Assert.assertEquals(expectedResult, result);
    }





    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createUpdateStatementNegativeTest1() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createUpdateStatement("", null);
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createUpdateStatementNegativeTest2() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createUpdateStatement("random", null);
        Assert.fail(); //should not have come here
    }

    @Test(expected = SQL_Statement_Creation_Exception.class)
    public void createUpdateStatementNegativeTest3() throws SQL_Statement_Creation_Exception {
        SQL_Creator_Helper.createUpdateStatement("random", new LinkedList<>());
        Assert.fail(); //should not have come here
    }

    @Test
    public void createUpdateStatementPositiveTest1() throws SQL_Statement_Creation_Exception {
        DB_Entry entry = new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer);
        String tableName = "random";


        String expectedResult = "UPDATE " + tableName + " SET " +entry.getName() +"=" +entry.getValue();
        String result = SQL_Creator_Helper.createUpdateStatement(tableName, Arrays.asList(entry));

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void createUpdateStatementPositiveTest2() throws SQL_Statement_Creation_Exception {
        DB_Entry entry = new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer);
        String tableName = "random";
        String expectedResult = "UPDATE " + tableName + " SET " +entry.getName() +"=" +entry.getValue();
        String result = SQL_Creator_Helper.createUpdateStatement(tableName, Arrays.asList(entry));

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void createUpdateStatementPositiveTest3() throws SQL_Statement_Creation_Exception {
        DB_Entry entry = new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer);
        String tableName = "random";
        DB_Entry condition = new DB_Entry("condition", "hallo", DB_Entry_Valuetypes.String);


        String expectedResult = "UPDATE " + tableName + " SET " +entry.getName() +"=" +entry.getValue() + " WHERE " + condition.getName() + " = \'" + condition.getValue()+"\'";;
        String result = SQL_Creator_Helper.createUpdateStatement(tableName, Arrays.asList(entry), condition);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void createUpdateStatementPositiveTest4() throws SQL_Statement_Creation_Exception {
        DB_Entry entry = new DB_Entry("name", "10", DB_Entry_Valuetypes.Integer);
        String tableName = "random";
        DB_Entry condition = new DB_Entry("condition", "hallo", DB_Entry_Valuetypes.String);


        String result1 = SQL_Creator_Helper.createUpdateStatement(tableName, Arrays.asList(entry));
        String result = SQL_Creator_Helper.createUpdateStatement(tableName, Arrays.asList(entry), null);

        Assert.assertEquals(result1, result);
    }
}
