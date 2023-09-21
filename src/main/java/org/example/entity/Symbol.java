package org.example.entity;

public class Symbol {
    private double reward_multiplier;
    private String type;
    private double extra;
    private String impact;

    public Symbol(double reward_multiplier, String type, double extra, String impact) {
        this.reward_multiplier = reward_multiplier;
        this.type = type;
        this.extra = extra;
        this.impact = impact;
    }

    public Symbol() {
    }

    public void setReward_multiplier(double reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    // Getters
    public double getRewardMultiplier() { return reward_multiplier; }
    public String getType() { return type; }
    public double getExtra() { return extra; }
    public String getImpact() { return impact; }
}
