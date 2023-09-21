package org.example.entity;

import java.util.Map;

public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    private Map<String, WinCombination> win_combinations;

    public Config(int columns, int rows, Map<String, Symbol> symbols, Probabilities probabilities, Map<String, WinCombination> win_combinations) {
        this.columns = columns;
        this.rows = rows;
        this.symbols = symbols;
        this.probabilities = probabilities;
        this.win_combinations = win_combinations;
    }

    public Config() {
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public void setWin_combinations(Map<String, WinCombination> win_combinations) {
        this.win_combinations = win_combinations;
    }

    // Getters
    public int getColumns() { return columns; }
    public int getRows() { return rows; }
    public Map<String, Symbol> getSymbols() { return symbols; }
    public Probabilities getProbabilities() { return probabilities; }
    public Map<String, WinCombination> getWinCombinations() { return win_combinations; }
}

