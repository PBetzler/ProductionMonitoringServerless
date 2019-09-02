package de.hdm.mi;

import de.hdm.mi.db.DB_Connection_Interface;
import de.hdm.mi.db.DB_Connection_MariaDB;
import de.hdm.mi.db.DB_Manager_Class;
import de.hdm.mi.db.DB_Manager_Interface;
import de.hdm.mi.handler.JSON_Handler_Class;
import de.hdm.mi.handler.JSON_Handler_Interface;
import de.hdm.mi.validator.Product_Validator_Class;
import de.hdm.mi.validator.Product_Validator_Interface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.Map;

public abstract class ProductionMonitoring_Controller_Abstract implements ProductionMonitoring_Controller_Interface{
    private static final Logger logger = LogManager.getLogger(de.hdm.mi.ProductionMonitoring_Controller_Abstract.class);

    protected DB_Connection_Interface dbConnection;
    protected DB_Manager_Interface dbManager;
    protected Product_Validator_Interface validator;
    protected JSON_Handler_Interface handler;

    public ProductionMonitoring_Controller_Abstract() {
        String dbUrl;
        String dbUsername;
        String dbPassword;

        try {
            Map<String, String> env = System.getenv();
            logger.trace("Received environment variables");

            if (env != null && !env.isEmpty()) {
                dbUrl = env.get("dbUrl");
                dbUsername = env.get("dbUsername");
                dbPassword = env.get("dbPassword");

                if (dbUrl == null || dbUrl.isEmpty()) {
                    logger.error("Could not receive environment variable dbUrl!");
                }

                if (dbUsername == null || dbUsername.isEmpty()) {
                    logger.error("Could not receive environment variable dbUsername!");
                }


                this.dbConnection = new DB_Connection_MariaDB(dbUrl, dbUsername, dbPassword);
            } else {
                logger.fatal("Fatal error, was not able to receive db access credential from environment variables! Trying to use placeholders....");
                this.dbConnection = new DB_Connection_MariaDB();
            }
        } catch (SecurityException e) {
            logger.fatal("Fatal error, did not have the permission to receive db access credential from environment variables! Trying to use placeholders....");
            this.dbConnection = new DB_Connection_MariaDB();
        }


        this.dbManager = new DB_Manager_Class(dbConnection);
        this.validator = new Product_Validator_Class(dbManager, 15);
        this.handler = new JSON_Handler_Class(validator, dbManager);
    }
}
