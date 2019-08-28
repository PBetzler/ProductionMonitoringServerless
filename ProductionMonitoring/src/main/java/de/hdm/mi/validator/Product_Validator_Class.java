package de.hdm.mi.validator;

import de.hdm.mi.db.DB_Manager_Interface;
import de.hdm.mi.helper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Product_Validator_Class extends Product_Validator_Abstract {

    private static final Logger logger = LogManager.getLogger(Product_Validator_Class.class);

    public Product_Validator_Class(DB_Manager_Interface dbManager, int threshold) {
        super(dbManager, threshold);
    }

    public Product_Validator_Class(DB_Manager_Interface dbManager) {
        super(dbManager, 15);
    }

    @Override
    public Product_Monitoring_ResultSet_Integer validateProduct(int partID) {
        Product_Monitoring_ResultSet_Boolean resultSetProductOK = dbManager.getIfPartIsListedAsOK(partID);
        if (resultSetProductOK.getIfResultOk()) {
            if(resultSetProductOK.getValue()) {
                ProductionMonitoring_ResultSet_Integer_Lists partDeviationAndUsedParts = dbManager.getDeviationAndUsedPartsOfPartWithID(partID);

                if (partDeviationAndUsedParts == null) {
                    logger.error("Error, got a null response from DB manager!");
                    return new Product_Monitoring_ResultSet_Integer(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
                }

                if(partDeviationAndUsedParts.getIfResultOk()) {
                    List deviationList = new LinkedList<Integer>();
                    deviationList.addAll(partDeviationAndUsedParts.getDeviationList());

                    for (int usedPartsID : partDeviationAndUsedParts.getPartsIDList()) {
                        Product_Monitoring_ResultSet_Integer partsResultList = this.validateProduct(usedPartsID);

                        if (partsResultList.getIfResultOk()) {
                            deviationList.add(partsResultList.getValue());
                        } else {
                            logger.warn("Used part validation of the part with ID: " +Integer.toString(usedPartsID) +" failed!");
                            return  new Product_Monitoring_ResultSet_Integer(false, partsResultList.getErrorCode());
                        }
                    }

                    int averageDeviation = (int) deviationList.stream().mapToInt(val -> (int) val).average().orElse(0);

                    if (averageDeviation > threshold) {
                        logger.trace("Part with the ID: " +Integer.toString(partID) +" failed the validator test. The deviation was: " +Integer.toString(averageDeviation));
                        Product_Monitoring_ResultSet_General markedPartAsNotOKResult =  dbManager.markPartAs(false, partID);

                        if (!markedPartAsNotOKResult.getIfResultOk()) {
                            logger.error("Error, could not mark a not ok part as not ok in DB! Error code was: "
                                    + markedPartAsNotOKResult.getErrorCode().toString());
                        }

                        return new Product_Monitoring_ResultSet_Integer(averageDeviation, false, Product_Monitoring_ErrorCode.VALIDATOR_CHECK_FAILED);
                    } else {
                        logger.trace("Part with the ID: " +Integer.toString(partID) +" matched the validator test. The deviation was: " +Integer.toString(averageDeviation));
                        return new Product_Monitoring_ResultSet_Integer(averageDeviation, true, Product_Monitoring_ErrorCode.OK);
                    }
                } else {
                    logger.warn("Error, received an DB error when asking for the deviation and used parts list!");
                    return new Product_Monitoring_ResultSet_Integer(-1, false, partDeviationAndUsedParts.getErrorCode());
                }
            } else {
                logger.trace("Checked part was flagged as not ok!");
                return new Product_Monitoring_ResultSet_Integer(-1, false, Product_Monitoring_ErrorCode.VALIDATOR_CHECK_FAILED);
            }

        } else {
            logger.warn("Error, received an DB error when asking if the part is flagged ok!");
            return new Product_Monitoring_ResultSet_Integer(-1, false, resultSetProductOK.getErrorCode());
        }
    }
}
