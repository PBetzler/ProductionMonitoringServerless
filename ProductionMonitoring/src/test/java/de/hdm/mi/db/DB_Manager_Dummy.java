package de.hdm.mi.db;

import de.hdm.mi.helper.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DB_Manager_Dummy implements DB_Manager_Interface {
    @Override
    public Product_Monitoring_ResultSet_Integer insertColumn(String tableName, List<DB_Entry> list) {
        if (tableName.contains("negative")) {
            return new Product_Monitoring_ResultSet_Integer(-1, false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        } else if (tableName.contains("null")) {
            return null;
        }
        return new Product_Monitoring_ResultSet_Integer(5, true,Product_Monitoring_ErrorCode.OK);
    }

    @Override
    public ProductionMonitoring_ResultSet_Integer_Lists getDeviationAndUsedPartsOfPartWithID(int partID) {
        if (partID == 0) {
            return null;
        } else if (partID < 0) {
            return new ProductionMonitoring_ResultSet_Integer_Lists(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        } else if (partID == 25) {
            return new ProductionMonitoring_ResultSet_Integer_Lists(Arrays.asList( 20,25,30,40), new LinkedList<Integer>(), true, Product_Monitoring_ErrorCode.OK);
        } else if (partID > 20) {
            return new ProductionMonitoring_ResultSet_Integer_Lists(Arrays.asList( 10,15,12,9), Arrays.asList(25), true, Product_Monitoring_ErrorCode.OK);
        }

        return new ProductionMonitoring_ResultSet_Integer_Lists(Arrays.asList( 10,15,12,9), new LinkedList<Integer>(), true, Product_Monitoring_ErrorCode.OK);
    }

    @Override
    public Product_Monitoring_ResultSet_Boolean getIfPartIsListedAsOK(int partID) {

        if (partID < 0) {
            return new Product_Monitoring_ResultSet_Boolean(true,false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }
        return new Product_Monitoring_ResultSet_Boolean(true, true, Product_Monitoring_ErrorCode.OK);
    }

    @Override
    public Product_Monitoring_ResultSet_General markPartAs(boolean ok, int partID) {
        return new Product_Monitoring_ResultSet_General(true, Product_Monitoring_ErrorCode.OK);
    }
}
