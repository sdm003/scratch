package org.example.engine;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.example.entity.BonusSymbolProbability;
import org.example.entity.Config;
import org.example.entity.StandardSymbolProbability;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MatrixGenerator {

    //Main method to generate Matrix
    public static Matrix generateMatrix(Config config){
        Matrix matrix = new Matrix(config.getRows(), config.getColumns());
        fillMatrix(matrix, config);

        return matrix;
    }

    private static void fillMatrix(Matrix matrix, Config config) {

        for (StandardSymbolProbability prob : config.getProbabilities().getStandard_symbols()) {
            List<String> weightAndShuffleSymbols = addWeightAndShuffle(prob.getSymbols());
            matrix.setSymbol(prob.getRow(), prob.getColumn(), weightAndShuffleSymbols.get(0));
        }

        setBonusSymbol(matrix, config.getProbabilities().getBonus_symbols());
    }
    //Here I just multiply the symbol to its probability and put everything to single list.
    //After that I shuffle this list and take the first element.
    //Because the more same element persist in list, than the more probability of this element to be first
    private static List<String> addWeightAndShuffle(Map<String, Integer> symbols){
        List<String> weightedSymbols = symbols.entrySet().stream()
                .flatMap(entry -> Collections.nCopies(entry.getValue(), entry.getKey()).stream())
                .collect(Collectors.toList());
        Collections.shuffle(weightedSymbols);

        return weightedSymbols;
    }
    //Here I am setting bonus_symbol to random cell.
    // I asked you about the necessity of having a single bonus symbol in the matrix, but unfortunately, it was the last day and I dont get answer.
    // So, I implemented a random boolean to determine whether this matrix would have a bonus symbol.
    private static void setBonusSymbol(Matrix matrix, BonusSymbolProbability bonusSymbols) {
        Random random = new Random();

        if(random.nextBoolean()) return;

        List<String> weightedSymbols = addWeightAndShuffle(bonusSymbols.getSymbols());
        int randomRow = random.nextInt(matrix.getRows());
        int randomColumn = random.nextInt(matrix.getColumns());

        matrix.setSymbol(randomRow, randomColumn, weightedSymbols.get(0));
        matrix.setBonusSymbolPoint(ImmutablePair.of(randomRow, randomColumn));
    }
}
