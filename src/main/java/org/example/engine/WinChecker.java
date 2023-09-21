package org.example.engine;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.example.entity.Config;
import org.example.entity.Symbol;
import org.example.entity.WinCombination;
import org.example.param.SymbolType;
import org.example.param.When;

import java.util.*;
import java.util.stream.Collectors;

public class WinChecker {
    private final Config config;
    private final Matrix matrix;

    public WinChecker(Config config, Matrix matrix) {
        this.config = config;
        this.matrix = matrix;
    }

    // This method is used to check wins how many winning combination are in the matrix
    //Overall logic by steps:
    //1) I find all symbols in matrix with count more or equal to 3 ::getSymbolCountMapWithMore3Elements(matrix)
    //2) I split WinningCombinations to 2 types and check first same_symbol combinations and after linear.
    //I check same_symbol combinations just by finding by count, because I have collected all symbols with count more than 3
    //Linear combinations I check just by looking in point of areas
    public Map<String, List<String>> checkWins(Matrix matrix) {

        Map<String, List<String>> wins = new HashMap<>();
        Map<String, WinCombination> combinationMap = config.getWinCombinations();
        Map<String, Integer> symbolsWithCountMore3 = getSymbolCountMapWithMore3Elements(matrix);

        Map<String, WinCombination> sameSymbolCombinations =
                combinationMap.entrySet()
                        .stream()
                        .filter(combination -> combination.getValue().getWhen().equals(When.SAME_SYMBOLS.getName()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, WinCombination> linearCombinations =
                combinationMap.entrySet()
                        .stream()
                        .filter(combination -> combination.getValue().getWhen().equals(When.LINEAR_SYMBOLS.getName()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        checkAndFillSameSymbolWins(sameSymbolCombinations, symbolsWithCountMore3, wins);
        checkAndFillLinearSymbolWins(linearCombinations, symbolsWithCountMore3, wins);

        return wins;
    }
    //Here I already have Map with Symbol and its count, so I just find the winCombination with the same count and put it to the result list
    private void checkAndFillSameSymbolWins(Map<String, WinCombination> sameSymbolCombinations,
                                            Map<String, Integer> symbolsWithCountMore3,
                                            Map<String, List<String>> wins) {

        symbolsWithCountMore3.forEach((key, value) -> {

            Optional<String> winCom =
                    sameSymbolCombinations
                            .entrySet()
                            .stream()
                            .filter(winCombination -> winCombination.getValue().getCount() == value)
                            .map(Map.Entry::getKey)
                            .findFirst();

            winCom.ifPresent(val -> {
                wins.compute(key, (k, existingList) -> {
                    if (existingList == null) {
                        existingList = new ArrayList<>();
                    }
                    existingList.add(val);
                    return existingList;
                });
            });
        });
    }
    //Here I already have symbols which are repeated more than 3 times, so I just check this symbol in exact areas.
    // Cause every linear_combination contains more than 3 symbols
    private void checkAndFillLinearSymbolWins(Map<String, WinCombination> linearSymbolCombinations,
                                              Map<String, Integer> symbolsWithCountMore3,
                                              Map<String, List<String>> wins) {

        symbolsWithCountMore3.forEach((key, value) -> {
            Optional<String> winCom =
                    linearSymbolCombinations
                            .entrySet()
                            .stream()
                            .filter(winCombination -> checkAreasForSymbol(key, winCombination.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst();

            winCom.ifPresent(val -> {
                wins.compute(key, (k, existingList) -> {
                    if (existingList == null) {
                        existingList = new ArrayList<>();
                    }
                    existingList.add(val);
                    return existingList;
                });
            });
        });
    }
    //Check matrix for linear combination by comparing symbols in area
    private boolean checkAreasForSymbol(String symbol, WinCombination winCombination){
        ImmutablePair<Integer, Integer>[][] areas = transformArea(winCombination.getCoveredAreas());

        for (ImmutablePair<Integer, Integer>[] immutablePairs : areas) {
            for (int area = 0; area < immutablePairs.length; area++) {
                ImmutablePair<Integer, Integer> point = immutablePairs[area];
                if (!matrix.getSymbol(point.left, point.right).equals(symbol)) {
                    break;
                }
                if (immutablePairs.length - area == 1) {
                    return true;
                }

            }
        }

        return false;
    }
    //Just to transform "0:0" to Pair<Integer, Integer>
    private ImmutablePair<Integer, Integer>[][] transformArea(List<List<String>> areas){
        return areas.stream()
                .map(row -> row.stream()
                        .map(s -> {
                            String[] parts = s.split(":");
                            return ImmutablePair.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                        })
                        .toArray(ImmutablePair[]::new))
                .toArray(ImmutablePair[][]::new);
    }

    private Map<String, Integer> getSymbolCountMapWithMore3Elements(Matrix matrix) {
        Map<String, Integer> symbolCount = new HashMap<>();
        Map<String, Symbol> symbols = config.getSymbols();

        for (int col = 0; col < matrix.getColumns(); col++) {
            for (int row = 0; row < matrix.getRows(); row++) {
                String symbolName = matrix.getSymbol(row, col);
                if (symbols.get(symbolName).getType().equals(SymbolType.STANDART.getName())) {
                    symbolCount.merge(symbolName, 1, Integer::sum);
                }
            }
        }

        return symbolCount
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
