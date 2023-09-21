package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<StandardSymbolProbability> standardSymbols;

    @JsonProperty("bonus_symbols")
    private BonusSymbolProbability bonusSymbols;

    public List<StandardSymbolProbability> getStandard_symbols() {
        return standardSymbols;
    }

    public void setStandard_symbols(List<StandardSymbolProbability> standard_symbols) {
        this.standardSymbols = standard_symbols;
    }

    public BonusSymbolProbability getBonus_symbols() {
        return bonusSymbols;
    }

    public void setBonus_symbols(BonusSymbolProbability bonus_symbols) {
        this.bonusSymbols = bonus_symbols;
    }

    public Probabilities(List<StandardSymbolProbability> standardSymbols, BonusSymbolProbability bonusSymbols) {
        this.standardSymbols = standardSymbols;
        this.bonusSymbols = bonusSymbols;
    }

    public Probabilities() {
    }
}