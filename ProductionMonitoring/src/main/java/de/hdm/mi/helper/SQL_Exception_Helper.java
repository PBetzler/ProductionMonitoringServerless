package de.hdm.mi.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQL_Exception_Helper {

    private static final Logger logger = LogManager.getLogger(SQL_Exception_Helper.class);
    public static Product_Monitoring_ErrorCode translateSQLErrorToProductionMonitoringErrorCode(String sqlError) {
        switch(sqlError.toLowerCase()) {

            case("42000"):
                return Product_Monitoring_ErrorCode.WRONG_SYNTAX;

            case("01002"):
            case("08001"):
            case("08003"):
            case("08004"):
            case("08006"):
                return Product_Monitoring_ErrorCode.DB_NOT_AVAILABLE;

            case ("HY000"):
            case ("23000"):
            case ("0700C"):
            case ("0700D"):
            case ("22003"):
            case ("22004"):
            case ("22005"):
            case ("22007"):

                return Product_Monitoring_ErrorCode.CANNOT_ADD_ENTRY;

            default:
                logger.error("Error, could not parse SQLState: " +sqlError +"! Returning an internal Error.");
                return Product_Monitoring_ErrorCode.INTERNAL_ERROR;
        }
    }
}
