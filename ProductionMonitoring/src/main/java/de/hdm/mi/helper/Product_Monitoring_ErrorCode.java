package de.hdm.mi.helper;

public enum Product_Monitoring_ErrorCode {
    OK(0, "OK"),
    WRONG_SYNTAX(1,"WRONG_SYNTAX"),
    DB_NOT_AVAILABLE(2,"DB_NOT_AVAILABLE"),
    CANNOT_ADD_ENTRY(3,"CANNOT_ADD_ENTRY"),
    NO_SUCH_ID(4,"NO_SUCH_ID"),
    VALIDATOR_CHECK_FAILED(5,"VALIDATOR_CHECK_FAILED"),
    INTERNAL_ERROR(6,"INTERNAL_ERROR");

    private Product_Monitoring_ErrorCode(int value, String stringRepresentation) {
        this.value = value;
        this.stringRepresentation = stringRepresentation;
    }

    private final int value;

    public String toString() {
        return stringRepresentation;
    }

    private final String stringRepresentation;

    public int getValue() {
        return value;
    }



}
