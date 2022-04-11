package com.example.currency.list.data_types;

public class ValuteData{

    private String ID;
    private String NumCode;
    private String CharCode;
    private String Nominal;
    private String Name;
    private String Value;
    private String Previous;

    public ValuteData(String ID, String numCode, String charCode, String nominal, String name, String value, String previous) {
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
        Previous = previous;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumCode() {
        return NumCode;
    }

    public void setNumCode(String numCode) {
        NumCode = numCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public String getNominal() {
        return Nominal;
    }

    public void setNominal(String nominal) {
        Nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getPrevious() {
        return Previous;
    }

    public void setPrevious(String previous) {
        Previous = previous;
    }
}
