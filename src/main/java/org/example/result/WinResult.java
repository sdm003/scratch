package org.example.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class WinResult extends Result {
    @JsonProperty("applied_winning_combinations")
    protected Map<String, List<String>> appliedWinningCombinations;

    public WinResult(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, BigDecimal reward) {
        super(matrix, reward);
        this.appliedWinningCombinations = appliedWinningCombinations;
    }

    public Map<String, List<String>> getAppliedWinningCombinations() {
        return appliedWinningCombinations;
    }

    public void setAppliedWinningCombinations(Map<String, List<String>> appliedWinningCombinations) {
        this.appliedWinningCombinations = appliedWinningCombinations;
    }
}
