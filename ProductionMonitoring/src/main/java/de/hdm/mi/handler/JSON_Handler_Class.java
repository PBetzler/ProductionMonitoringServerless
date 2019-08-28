package de.hdm.mi.handler;

import de.hdm.mi.db.DB_Entry;
import de.hdm.mi.db.DB_Entry_Valuetypes;
import de.hdm.mi.db.DB_Manager_Interface;
import de.hdm.mi.helper.*;
import de.hdm.mi.validator.Product_Validator_Interface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class JSON_Handler_Class extends JSON_Handler_Abstract {

    private static final Logger logger = LogManager.getLogger(JSON_Handler_Class.class);
    public JSON_Handler_Class(Product_Validator_Interface validator, DB_Manager_Interface dbManager) {
        super(validator, dbManager);
    }

    public JSONObject handleRequest(JSONObject request) {
        if (request == null) {
            logger.fatal("called JSONHandler::handleRequest with null param!");
            return JSON_Helper.createNegativeResponse(-1, Product_Monitoring_ErrorCode.INTERNAL_ERROR.getValue());
        }

        int requestNumber = -1;

        if (request.has("Request")) {
            requestNumber = request.getInt("Request");
        } else {
            logger.warn("Request part in the received msg is missing!");
        }

        Product_Monitoring_ResultSet_General result = null;

        if (request.has("Produkt")) {
            result = addProductToDB(request);

        } else if (request.has("FertigungsSchritt")) {
            result = addProductionStepInfos(request);

        } else if (request.has("ProduktOK")) {
            int productID;
            if (request.getJSONObject("ProduktOK").has("ProduktID")) {
                productID = request.getJSONObject("ProduktOK").getInt("ProduktID");
                result = validator.validateProduct(productID);
            } else {
                return JSON_Helper.createNegativeResponse(requestNumber, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue());
            }

        } else {
            logger.warn("Wrong syntax received!");
            return JSON_Helper.createNegativeResponse(requestNumber, Product_Monitoring_ErrorCode.WRONG_SYNTAX.getValue());
        }

        if (!result.getIfResultOk()) {
            logger.warn("Processing of request failed with error code: " +Integer.toString(result.getErrorCode().getValue()) +" " +result.getErrorCode().toString());
            return JSON_Helper.createNegativeResponse(requestNumber, result.getErrorCode().getValue());
        }
        return JSON_Helper.createPositiveResponse(requestNumber, Product_Monitoring_ErrorCode.OK.getValue());
    }

    private Product_Monitoring_ResultSet_General addProductToDB(JSONObject jo) {

        if (jo == null) {
            logger.fatal("JSON_Handler_Class::addProductToDB was called with an null pointer!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }
        String tableName;

        if (jo.has("Produkt")) {
            tableName = "Produkt";
        } else {
            logger.error("Called JSON_Handler_Class::addProductToDB with an invalid JSON structure!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        jo = jo.getJSONObject("Produkt");
        List rowNamesAndValues = new LinkedList<DB_Entry>();

        if (jo.has("ProduktTypID")) {
            rowNamesAndValues.add(new DB_Entry("ProduktTypID", Integer.toString(jo.getInt("ProduktTypID")), DB_Entry_Valuetypes.Integer));
        } else {
            logger.warn("Request part in the received msg lacks ProduktTypID part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        if (jo.has("ID")) {
            rowNamesAndValues.add(new DB_Entry("ID", Integer.toString(jo.getInt("ID")), DB_Entry_Valuetypes.Integer));
        } else {
            logger.warn("Request part in the received msg lacks ID part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        Product_Monitoring_ResultSet_Integer insertResult = dbManager.insertColumn(tableName, rowNamesAndValues);

        if (!insertResult.getIfResultOk()) {
            logger.info("DB_Connection_Interface::insertColumn failed with error code: " + insertResult.getErrorCode().toString());
            return new Product_Monitoring_ResultSet_General(false, insertResult.getErrorCode());
        }

        tableName = "ProduktOK";

        rowNamesAndValues.clear();
        rowNamesAndValues.add(new DB_Entry("ProduktID", Integer.toString(jo.getInt("ID")), DB_Entry_Valuetypes.Integer));
        rowNamesAndValues.add(new DB_Entry("OK", "1", DB_Entry_Valuetypes.Integer));

        insertResult = dbManager.insertColumn(tableName, rowNamesAndValues);

        if (!insertResult.getIfResultOk()) {
            logger.info("DB_Connection_Interface::insertColumn failed with error code: " + insertResult.getErrorCode().toString());
            return new Product_Monitoring_ResultSet_General(false, insertResult.getErrorCode());
        }




        return new Product_Monitoring_ResultSet_General(true, Product_Monitoring_ErrorCode.OK);
    }

    private Product_Monitoring_ResultSet_General addProductionStepInfos(JSONObject paramJo) {
        if (paramJo == null) {
            logger.fatal("JSON_Handler_Class::addProductToDB was called with an null pointer!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.INTERNAL_ERROR);
        }

        JSONObject jo = new JSONObject(paramJo, JSONObject.getNames(paramJo));

        List rowsAndValuesList = new LinkedList<DB_Entry>();
        String tableName;

        if (jo.has("HardwareZustand")) {
            tableName = "HardwareZustand";
        } else {
            logger.warn("Request part in the received msg lacks HardwareZustand part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        jo = jo.getJSONObject("HardwareZustand");

        if (jo.has("HardwareID")) {
            rowsAndValuesList.add(new DB_Entry("HardwareID", Integer.toString(jo.getInt("HardwareID")), DB_Entry_Valuetypes.Integer));
        } else {
            logger.warn("Request part in the received msg lacks HardwareID part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        if (jo.has("FertigungsCounter")) {
            int counter = jo.getInt("FertigungsCounter");

            if (counter < 0) {
                logger.warn("Error, got an negative counter value!");
                return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
            }

            rowsAndValuesList.add(new DB_Entry("FertigungsCounter", Integer.toString(counter), DB_Entry_Valuetypes.Integer));

        } else {
            logger.warn( "Request part in the received msg lacks FertigungsCounter part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        Product_Monitoring_ResultSet_Integer result = dbManager.insertColumn(tableName, rowsAndValuesList);
        int hardwareZustandID;
        if (result.getIfResultOk()) {
            hardwareZustandID = result.getValue();
        } else {
            return new Product_Monitoring_ResultSet_General(false, result.getErrorCode());
        }


        rowsAndValuesList.clear();
        tableName = "FertigungsSchritt";



        jo = paramJo.getJSONObject("FertigungsSchritt");
        if (jo.has("ProduktID")) {
            rowsAndValuesList.add(new DB_Entry("ProduktID", Integer.toString(jo.getInt("ProduktID")), DB_Entry_Valuetypes.Integer));
        } else {
            logger.warn( "Request part in the received msg lacks ProduktID part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        if (jo.has("Info")) {
            rowsAndValuesList.add(new DB_Entry("Info", Integer.toString(jo.getInt("Info")), DB_Entry_Valuetypes.Integer));
        } else {
            logger.warn("Request part in the received msg lacks Info part!");
            return new Product_Monitoring_ResultSet_General(false, Product_Monitoring_ErrorCode.WRONG_SYNTAX);
        }

        if (jo.has("TeilID")) {
            if (jo.getInt("TeilID") > 0) {
                rowsAndValuesList.add(new DB_Entry("TeilID", Integer.toString(jo.getInt("TeilID")), DB_Entry_Valuetypes.Integer));
            } else {
                logger.trace("No parts to be added!");
            }
        } else {
            logger.info("Request part in the received msg lacks TeilID part!");
        }

        rowsAndValuesList.add(new DB_Entry("HardwareZustandID", Integer.toString(hardwareZustandID), DB_Entry_Valuetypes.Integer));

        result = dbManager.insertColumn(tableName, rowsAndValuesList);

        if (!result.getIfResultOk()) {
            logger.warn("DB_Connection.insertInDB failed!");
            return new Product_Monitoring_ResultSet_General(false, result.getErrorCode());
        }

        return new Product_Monitoring_ResultSet_General(true, Product_Monitoring_ErrorCode.OK);
    }
}
