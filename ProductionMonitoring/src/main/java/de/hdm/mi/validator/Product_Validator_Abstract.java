package de.hdm.mi.validator;

import de.hdm.mi.db.DB_Manager_Interface;

public abstract class Product_Validator_Abstract implements Product_Validator_Interface {
    protected DB_Manager_Interface dbManager;
    protected int threshold = 15;

    public Product_Validator_Abstract(DB_Manager_Interface dbManager, int threshold) {
        this.dbManager = dbManager;
        if (threshold <= 0) {
            this.threshold = threshold;
        }
    }
}
