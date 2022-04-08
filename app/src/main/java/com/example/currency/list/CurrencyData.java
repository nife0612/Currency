package com.example.currency.list;

public class CurrencyData {
    private String Data;
    private String PreviousDate;
    private String PreviousURL;
    private String Timestamp;
    private String Valute;

    public CurrencyData(String data, String previousDate, String previousURL, String timestamp, String valute) {
        Data = data;
        PreviousDate = previousDate;
        PreviousURL = previousURL;
        Timestamp = timestamp;
        Valute = valute;
    }

    public CurrencyData(String valute){
        Valute = valute;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(String previousDate) {
        PreviousDate = previousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public void setPreviousURL(String previousURL) {
        PreviousURL = previousURL;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getValute() {
        return Valute;
    }

    public void setValute(String valute) {
        Valute = valute;
    }
}
