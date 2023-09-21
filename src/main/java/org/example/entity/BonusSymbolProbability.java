package org.example.entity;

import java.util.Map;

public class BonusSymbolProbability {
    private Map<String, Integer> symbols;

    public BonusSymbolProbability(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    public BonusSymbolProbability() {
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    // Getters
    public Map<String, Integer> getSymbols() { return symbols; }
}
