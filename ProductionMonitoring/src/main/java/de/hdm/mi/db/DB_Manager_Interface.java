package de.hdm.mi.db;

import de.hdm.mi.helper.Product_Monitoring_ResultSet_Boolean;
import de.hdm.mi.helper.Product_Monitoring_ResultSet_General;
import de.hdm.mi.helper.Product_Monitoring_ResultSet_Integer;
import de.hdm.mi.helper.ProductionMonitoring_ResultSet_Integer_Lists;

import java.util.List;

public interface DB_Manager_Interface {
    public Product_Monitoring_ResultSet_Integer insertColumn(String tableName, List<DB_Entry> list);
    public ProductionMonitoring_ResultSet_Integer_Lists getDeviationAndUsedPartsOfPartWithID(int partID);
    public Product_Monitoring_ResultSet_Boolean getIfPartIsListedAsOK(int partID);
    public Product_Monitoring_ResultSet_General markPartAs(boolean ok, int partID);
}
