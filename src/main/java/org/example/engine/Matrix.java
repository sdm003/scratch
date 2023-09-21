package org.example.engine;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public class Matrix {
    private final String[][] symbols;
    private Optional<ImmutablePair<Integer, Integer>> bonusSymbolPoint = Optional.empty();

    public Matrix(int rows, int columns) {
        this.symbols = new String[rows][columns];
    }

    public void setSymbol(int row, int column, String symbol) {
        symbols[row][column] = symbol;
    }

    public String getSymbol(int row, int column) {
        return symbols[row][column];
    }

    public void setBonusSymbolPoint(ImmutablePair<Integer, Integer> bonusSymbolPoint){
        this.bonusSymbolPoint = Optional.of(bonusSymbolPoint);
    }

    public Optional<ImmutablePair<Integer, Integer>> getBonusSymbol(){
        return bonusSymbolPoint;
    }

    public Boolean bonusSymbolExist(){
        return bonusSymbolPoint.isPresent();
    }

    public String[][] getSymbols(){
        return symbols;
    }
    public int getRows() {
        return symbols.length;
    }

    public int getColumns() {
        return symbols[0].length;
    }
}
