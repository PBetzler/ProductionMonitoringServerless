package de.hdm.mi.helper;

import de.hdm.mi.db.DB_Entry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class SQL_Creator_Helper {

    private static final Logger logger = LogManager.getLogger(SQL_Creator_Helper.class);

    public static String createInsertStatement(String tableName, List<DB_Entry> list) throws SQL_Statement_Creation_Exception{
        if(tableName.isEmpty()|| tableName == " ") {
            logger.log(Level.ERROR, "Error, didnt get an useful table name!");
            throw new SQL_Statement_Creation_Exception("Error, tried to create an insert statement with an invalid table name!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        } else if (list == null || list.isEmpty()) {
            logger.log(Level.ERROR, "Error, list of values to add is null or empty!");
            throw new SQL_Statement_Creation_Exception("Error, tried to create an insert statement with an invalid contents list!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        String tableRows = "";
        String rowValues = "";
        boolean first = true;

        Iterator it = list.iterator();

       for (DB_Entry entry : list) {

            if (!first) {
                tableRows += ", ";
                rowValues += ", ";
            } else {
                first = false;
            }

            tableRows += entry.getName();

            rowValues += getValueInCorrectForm(entry);
        }

       return "INSERT INTO " + tableName + " (" + tableRows + ")" + " VALUES (" + rowValues + ")";
    }

    public static String createSelectStatement(String tableName, List<String> rows, DB_Entry condition) throws SQL_Statement_Creation_Exception {
        if(tableName.isEmpty() || tableName == " ") {
            logger.log(Level.ERROR, "Error, didnt get an useful table name!");
            throw new SQL_Statement_Creation_Exception("Error, tried to create an insert statement with an invalid table name!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        } else if (rows == null || rows.isEmpty()) {
            logger.error("Error, rows specification was null or empty!");
            throw new SQL_Statement_Creation_Exception("Error, tried to create a select statement with an invalid specification of rows to select!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        String selectedRows ="";
        boolean first = true;

        for (String row : rows) {
            if (!first) {
                selectedRows+= ", ";
            } else {
                first = false;
            }

            selectedRows += row;
        }

        String result = "SELECT " + selectedRows + " FROM " + tableName;

        if (condition != null) {
            result += " WHERE " + condition.getName() + " = " + getValueInCorrectForm(condition);
        }

        return result;
    }

    public static String createSelectStatement(String tableName, List<String> rows) throws SQL_Statement_Creation_Exception {
        return createSelectStatement(tableName, rows, null);
    }

    public static String createUpdateStatement(String tableName, List<DB_Entry> list, DB_Entry condition) throws SQL_Statement_Creation_Exception{
        if(tableName.isEmpty()|| tableName == " ") {
            logger.log(Level.ERROR, "Error, didnt get an useful table name!");
            throw new SQL_Statement_Creation_Exception("Error, tried to create an update statement with an invalid table name!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        } else if (list == null || list.isEmpty()) {
            logger.log(Level.ERROR, "Error, list of values to add is null or empty!");
            throw new SQL_Statement_Creation_Exception("Error, tried to create an update statement with an invalid contents list!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        String rowValueAssigment = "";
        boolean first = true;

        Iterator it = list.iterator();

        for (DB_Entry entry : list) {

            if (!first) {
                rowValueAssigment += ", ";
            } else {
                first = false;
            }

            rowValueAssigment += entry.getName() + "=";

            rowValueAssigment += getValueInCorrectForm(entry);
        }

        String result = "UPDATE " + tableName + " SET " +rowValueAssigment;

        if (condition != null) {
            result += " WHERE " + condition.getName() + " = " + getValueInCorrectForm(condition);
        }

        return result;
    }

    public static String createUpdateStatement(String tableName, List<DB_Entry> list) throws SQL_Statement_Creation_Exception {
        return createUpdateStatement(tableName, list, null);
    }

    private static String getValueInCorrectForm(DB_Entry entry) throws SQL_Statement_Creation_Exception {
        String result ="";

        switch (entry.getTyp()) {
            case String:
                result +="'" +entry.getValue() + "'";
                break;
            case Double:
            case Integer:
                result+=entry.getValue();
                break;
            default:
                logger.fatal("Fatal Error!, DB_Entry_Value_Typ: " + entry.getTyp().toString()
                        +" is not implemented in SQL_Creator_Helper::getValueInCorrectForm!");
                throw new SQL_Statement_Creation_Exception("Fatal Error!, DB_Entry_Value_Typ: " +entry.getTyp().toString()
                        +" is not implemented in SQL_Creator_Helper::getValueInCorrectForm!", Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        return result;
    }
}
