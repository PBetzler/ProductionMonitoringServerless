package de.hdm.mi.helper;

import java.util.List;

public class ProductionMonitoring_ResultSet_Integer_Lists extends Product_Monitoring_ResultSet_General {



    private List<Integer> deviationList;
    private List<Integer> partsIDList;

    public ProductionMonitoring_ResultSet_Integer_Lists(List<Integer> deviationList, List<Integer> partsIDList, boolean resultOk, Product_Monitoring_ErrorCode errorCode) {
        super(resultOk, errorCode);
        this.deviationList = deviationList;
        this.partsIDList = partsIDList;
    }
    public ProductionMonitoring_ResultSet_Integer_Lists(boolean resultOk, Product_Monitoring_ErrorCode errorCode) {
        super(resultOk, errorCode);
    }

    public List<Integer> getDeviationList() {
        return deviationList;
    }

    public List<Integer> getPartsIDList() {
        return partsIDList;
    }

}
