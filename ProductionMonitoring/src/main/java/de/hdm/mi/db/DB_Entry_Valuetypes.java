package de.hdm.mi.db;

public enum DB_Entry_Valuetypes {
    Integer("Integer"),
    String("String"),
    Double("Double");

    private final String stringRepresentation;

    private DB_Entry_Valuetypes(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public String toString() {
        return stringRepresentation;
    }
}

