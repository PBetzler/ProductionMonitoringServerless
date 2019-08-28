package de.hdm.mi;

import de.hdm.mi.db.DB_Connection_Interface;
import de.hdm.mi.db.DB_Connection_MariaDB;
import de.hdm.mi.db.DB_Manager_Class;
import de.hdm.mi.db.DB_Manager_Interface;
import de.hdm.mi.handler.JSON_Handler_Class;
import de.hdm.mi.handler.JSON_Handler_Interface;
import de.hdm.mi.validator.Product_Validator_Class;
import de.hdm.mi.validator.Product_Validator_Interface;
import org.json.JSONObject;

public abstract class ProductionMonitoring_Controller_Abstract implements ProductionMonitoring_Controller_Interface{
    protected DB_Connection_Interface dbConnection;
    protected DB_Manager_Interface dbManager;
    protected Product_Validator_Interface validator;
    protected JSON_Handler_Interface handler;

    public ProductionMonitoring_Controller_Abstract() {
        this.dbConnection = new DB_Connection_MariaDB();
        this.dbManager = new DB_Manager_Class(dbConnection);
        this.validator = new Product_Validator_Class(dbManager, 15);
        this.handler = new JSON_Handler_Class(validator, dbManager);
    }
}
