package org.example.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class WinResultWithBonusSymbol extends WinResult {
    @JsonProperty("applied_bonus_symbol")
    private String appliedBonusSymbol;

    public WinResultWithBonusSymbol(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, String appliedBonsuSymbol, BigDecimal reward) {
        super(matrix, appliedWinningCombinations, reward);
        this.appliedBonusSymbol = appliedBonsuSymbol;
    }

    public String getAppliedBonusSymbol() {
        return appliedBonusSymbol;
    }

    public void setAppliedBonusSymbol(String appliedBonusSymbol) {
        this.appliedBonusSymbol = appliedBonusSymbol;
    }

}
