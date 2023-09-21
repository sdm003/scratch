package org.example.engine;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.example.entity.*;
import org.example.result.Result;
import org.example.result.WinResult;
import org.example.result.WinResultWithBonusSymbol;
import java.math.BigDecimal;
import java.util.*;
// This class combines all game logic and returns the final Result.
public class ScratchGame {
    private final Config config;

    public ScratchGame(Config config) {
        this.config = config;
    }

    // The main method to play
    public Result play(Integer bet) {
        Matrix matrix = MatrixGenerator.generateMatrix(config);
        return calculateFinalReward(matrix, bet);
    }


    private Result calculateFinalReward(Matrix matrix, Integer bet) {
        BigDecimal totalReward;

        WinChecker winChecker = new WinChecker(config, matrix);
        Map<String, List<String>> winCombinations = winChecker.checkWins(matrix);

        if(winCombinations.isEmpty()) return new Result(matrix.getSymbols(), BigDecimal.ZERO);

        totalReward =
                winCombinations.entrySet().stream()
                                          .map(win -> calculateReward(win.getKey(), win.getValue(), bet))
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(matrix.bonusSymbolExist()){
            ImmutablePair<Integer, Integer> bonusSymbolPoint = matrix.getBonusSymbol().get();
            String bonusSymbol = matrix.getSymbol(bonusSymbolPoint.left, bonusSymbolPoint.right);

            totalReward = applyBonusReward(totalReward, bonusSymbol);

            return new WinResultWithBonusSymbol(matrix.getSymbols(), winCombinations, bonusSymbol, totalReward);
        }

        return new WinResult(matrix.getSymbols(), winCombinations, totalReward);
    }

    private BigDecimal calculateReward(String symbolName, List<String> wins, Integer bet){
        BigDecimal totalReward = BigDecimal.valueOf(bet);

        Symbol symbol = config.getSymbols().get(symbolName);
        Map<String, WinCombination> winCombinations = config.getWinCombinations();

        totalReward = totalReward.multiply(BigDecimal.valueOf(symbol.getRewardMultiplier()));

        for (String win : wins) {
            totalReward = totalReward.multiply(BigDecimal.valueOf(winCombinations.get(win).getRewardMultiplier()));
        }
        return totalReward;
    }

    private BigDecimal applyBonusReward(BigDecimal totalReward, String bonusRewardName){
        Symbol symbol = config.getSymbols().get(bonusRewardName);

        switch (symbol.getImpact()){
            case "multiply_reward" : totalReward = totalReward.multiply(BigDecimal.valueOf(symbol.getRewardMultiplier()));
            case "extra_bonus" : totalReward = totalReward.add(BigDecimal.valueOf(symbol.getExtra()));
        }

        return totalReward;
    }
}

