package de.hdm.mi.helper;

public class SQL_Statement_Creation_Exception extends Exception {
    private Product_Monitoring_ErrorCode errorCode = Product_Monitoring_ErrorCode.OK;

    public SQL_Statement_Creation_Exception(String message, Product_Monitoring_ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Product_Monitoring_ErrorCode getErrorCode() {
        return errorCode;
    }
}
