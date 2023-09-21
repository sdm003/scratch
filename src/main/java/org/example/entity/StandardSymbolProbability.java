package org.example.entity;

import java.util.Map;

public class StandardSymbolProbability {
    private int column;
    private int row;
    private Map<String, Integer> symbols;

    public StandardSymbolProbability(int column, int row, Map<String, Integer> symbols) {
        this.column = column;
        this.row = row;
        this.symbols = symbols;
    }


    public StandardSymbolProbability() {
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    // Getters
    public int getColumn() { return column; }
    public int getRow() { return row; }
    public Map<String, Integer> getSymbols() { return symbols; }
}