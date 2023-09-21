package org.example.param;

public enum SymbolType {

    STANDART("standard"),
    BONUS("bonus");
    private String param;

    SymbolType(String p) {
        param = p;
    }

    public String getName() {
        return param;
    }
}
