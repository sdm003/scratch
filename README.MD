README - 3x3 SCRATCH GAME
This document explains the primary mechanisms behind our game's operation

Matrix Generation:
Base Matrix Generation: Our 3x3 matrix is generated based on the probability of each symbol. To achieve this:
We create a list (weightedSymbols) and populate it with symbols, each repeated by its weight.
To illustrate: if a symbol "A" has a weight of 5, it will appear 5 times in our list.

List<String> weightedSymbols = symbols.entrySet().stream()
                .flatMap(entry -> Collections.nCopies(entry.getValue(), entry.getKey()).stream())
                .collect(Collectors.toList());
Collections.shuffle(weightedSymbols);

Bonus Symbol Addition:
The decision to include a bonus symbol in the matrix is determined randomly using a boolean value.
If we decide to include a bonus symbol, its addition is similar to the base symbols. We populate the list with the bonus symbol according to its weight.

Win Determination:
    First, we identify all symbols that appear 3 times or more in the matrix and collect them into the map with key as Symbol name and value as count.
    This is because any winning combination requires at least three matching symbols.

Combination Check:
    Two when are provided:
        1) Based on the number of matching symbols.
        2) Based on matching lines with areas.

First, I check combinations based on the quantity of matching symbols simply going through all same_symbol combinations and checking the count with matching symbol
Next, we verify the specific patterns or lines, simply checking each point in the pattern by comparing with symbols that repeats more than 3 time in the matrix.
Reward Calculation: After all checks, we calculate the total reward sum based on the winning combinations present.

Important Notes:
After building jar file please run scratch-game-1.0-SNAPSHOT.jar 
There was ambiguity regarding whether a one bonus symbol is mandatory in the matrix. Since there's a winning combination involving nine matching symbols, it becomes impossible if a bonus symbol is present.
Hence, the inclusion of a bonus symbol is determined randomly.

Unfortunately, I didnt have enough time to cover this application with tests.