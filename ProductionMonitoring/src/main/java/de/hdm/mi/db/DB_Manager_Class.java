package de.hdm.mi.db;

import de.hdm.mi.helper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DB_Manager_Class extends DB_Manager_Abstract{

    private static final Logger logger = LogManager.getLogger(DB_Manager_Class.class);

    public DB_Manager_Class(DB_Connection_Interface dbConnection) {
        super(dbConnection);

        if(dbConnection == null) {
            logger.fatal("Fatal error! DB_manager has no db_connection object!");
            System.exit(1);
        }


    }

    @Override
    public Product_Monitoring_ResultSet_Integer insertColumn(String tableName, List<DB_Entry> list) {
        String sqlInsert;

        try{
            sqlInsert = SQL_Creator_Helper.createInsertStatement(tableName, list);
        } catch (SQL_Statement_Creation_Exception e) {
            logger.error(e.getMessage() + " " + e.getStackTrace());
            return new Product_Monitoring_ResultSet_Integer(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        try {
            int idOfInsertedColumn = dbConnection.executeUpdate(sqlInsert);
            if (idOfInsertedColumn == -1) {
                logger.warn("Failed to retrieve the id field of the new inserted column!");
                return new Product_Monitoring_ResultSet_Integer(idOfInsertedColumn, false, Product_Monitoring_ErrorCode.CANNOT_ADD_ENTRY);
            }

            return new Product_Monitoring_ResultSet_Integer(idOfInsertedColumn, true, Product_Monitoring_ErrorCode.OK);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new Product_Monitoring_ResultSet_Integer(false, SQL_Exception_Helper.translateSQLErrorToProductionMonitoringErrorCode(e.getSQLState()));
        }
    }

    @Override
    public ProductionMonitoring_ResultSet_Integer_Lists getDeviationAndUsedPartsOfPartWithID(int partID) {
        String sqlSelect;

        try{
            sqlSelect = SQL_Creator_Helper.createSelectStatement("FertigungsSchritt", Arrays.asList("Info","teilID"), new DB_Entry("ProduktID", Integer.toString(partID), DB_Entry_Valuetypes.Integer));
        } catch (SQL_Statement_Creation_Exception e) {
            logger.error(e.getMessage() + " " + e.getStackTrace());
            return new ProductionMonitoring_ResultSet_Integer_Lists(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        List deviation = new LinkedList();
        List partIDList = new LinkedList();

        try {
            ResultSet sqlResult = dbConnection.executeQuery(sqlSelect);

            if (sqlResult == null) {
                logger.error("Error, DB_Connection returned a null pointer on the execute query request! Request was: " +sqlSelect);
                return new ProductionMonitoring_ResultSet_Integer_Lists(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
            }

            while (sqlResult.next()) {
                deviation.add(sqlResult.getInt("Info"));
                if (sqlResult.wasNull()) {
                    logger.error("DB Error! An deviation value in the DB was NULL!");
                    return new ProductionMonitoring_ResultSet_Integer_Lists(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
                }

                int usedPartID = sqlResult.getInt("teilID");
                if(!sqlResult.wasNull()) {
                    partIDList.add(usedPartID);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new ProductionMonitoring_ResultSet_Integer_Lists(false, SQL_Exception_Helper.translateSQLErrorToProductionMonitoringErrorCode(e.getSQLState()));
        }
        return new ProductionMonitoring_ResultSet_Integer_Lists(deviation, partIDList, true, Product_Monitoring_ErrorCode.OK);
    }

    @Override
    public Product_Monitoring_ResultSet_Boolean getIfPartIsListedAsOK(int partID) {
        String sqlSelect;

        try{
            sqlSelect = SQL_Creator_Helper.createSelectStatement("ProduktOK", Arrays.asList("OK"), new DB_Entry("ProduktID", Integer.toString(partID), DB_Entry_Valuetypes.Integer));
        } catch (SQL_Statement_Creation_Exception e) {
            logger.error(e.getMessage() + " " + e.getStackTrace());
            return new Product_Monitoring_ResultSet_Boolean(false,false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        try {
            ResultSet sqlResult = dbConnection.executeQuery(sqlSelect);

            if (sqlResult == null) {
                logger.error("Error, DB_Connection returned a null pointer on the execute query request! Request was: " +sqlSelect);
                return new Product_Monitoring_ResultSet_Boolean(false, false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
            }
            int size = 0;
            boolean partIsOK = false;

            while(sqlResult.next()) {
                if (size == 0) {
                    partIsOK = sqlResult.getBoolean("OK");
                }

                size++;
            }

            if (size != 1) {
                logger.fatal("Critical DB Error! The DB table ProduktOK has non unique partID row! Got " +Integer.toString(size)
                        +" results on the select of partID: " +Integer.toString(partID) +". Program is working with the first matching result.");
            }

            if(!partIsOK) {
                logger.trace("Found a not ok part. PartID was: " +Integer.toString(partID));
            }

            return new Product_Monitoring_ResultSet_Boolean(partIsOK, true, Product_Monitoring_ErrorCode.OK);

        } catch (SQLException e) {
            logger.error(e.getMessage() + " " + e.getStackTrace());
            return new Product_Monitoring_ResultSet_Boolean(false,false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override
    public Product_Monitoring_ResultSet_General markPartAs(boolean ok, int partID) {
        String sqlUpdate;

        try{
            sqlUpdate = SQL_Creator_Helper.createUpdateStatement(
                    "ProduktOK",Arrays.asList(new DB_Entry("OK", Integer.toString(ok ? 1:0), DB_Entry_Valuetypes.Integer)),
                    new DB_Entry("ProduktID", Integer.toString(partID), DB_Entry_Valuetypes.Integer));
        } catch (SQL_Statement_Creation_Exception e) {
            logger.error(e.getMessage() + " " + e.getStackTrace());
            return new Product_Monitoring_ResultSet_Boolean(false,false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        try {
            int idOfInsertedColumn = dbConnection.executeUpdate(sqlUpdate);
            if (idOfInsertedColumn == -1) {
                logger.warn("Failed to retrieve the id field of the updated column!");
                return new Product_Monitoring_ResultSet_Integer(idOfInsertedColumn, false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
            }

            return new Product_Monitoring_ResultSet_Integer(idOfInsertedColumn, true, Product_Monitoring_ErrorCode.OK);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new Product_Monitoring_ResultSet_Integer(false, SQL_Exception_Helper.translateSQLErrorToProductionMonitoringErrorCode(e.getSQLState()));
        }
    }
}
