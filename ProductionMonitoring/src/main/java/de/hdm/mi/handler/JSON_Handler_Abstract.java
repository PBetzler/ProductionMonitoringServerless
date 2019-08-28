package de.hdm.mi.handler;

import de.hdm.mi.db.DB_Manager_Interface;
import de.hdm.mi.validator.Product_Validator_Interface;

public abstract class JSON_Handler_Abstract implements JSON_Handler_Interface{
    public DB_Manager_Interface getDbManager() {
        return dbManager;
    }

    public Product_Validator_Interface getValidator() {
        return validator;
    }

    public void setDbManager(DB_Manager_Interface dbManager) {
        this.dbManager = dbManager;
    }

    public void setValidator(Product_Validator_Interface validator) {
        this.validator = validator;
    }

    protected DB_Manager_Interface dbManager = null;
    protected Product_Validator_Interface validator = null;

    public JSON_Handler_Abstract(Product_Validator_Interface validator, DB_Manager_Interface dbManager) {
        this.validator = validator;
        this.dbManager = dbManager;
    }

}
