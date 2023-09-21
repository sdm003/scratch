package org.example.entity;

public enum BonusSymbolImpact {

    MULTIPLY_REWARD("multiply_reward"),
    EXTRA_BONUS("extra_bonus"),
    MISS("miss");
    private String param;

    BonusSymbolImpact(String p) {
        param = p;
    }

    String getName() {
        return param;
    }
}
