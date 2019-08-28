package de.hdm.mi.db;

public class DB_Entry {
    private String name;
    private String value;
    private DB_Entry_Valuetypes typ;

    public DB_Entry() {}
    public DB_Entry(String name, String value, DB_Entry_Valuetypes typ) {
        if (name != null) {
            this.name = name;
        }
        if (value != null) {
            this.value = value;
        }
        if (typ != null) {
            this.typ = typ;
        }
    }

    public boolean setName(String name) {
        if (name != null && name != "") {
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean setValue(String value) {
        if (value != null && value != "") {
            this.value = value;
            return true;
        }
        return false;
    }

    public boolean setTyp(DB_Entry_Valuetypes typ) {
        if (typ != null) {
            this.typ = typ;
            return true;
        }
        return false;
    }

    public String getName() { return name;}
    public String getValue() { return value;}
    public DB_Entry_Valuetypes getTyp() { return typ;}

    public String getTypAsString() { return typ.toString();}
}
