package org.example.entity;

import java.util.List;

public class WinCombination {
    private double reward_multiplier;
    private String when;
    private int count;
    private String group;
    private List<List<String>> covered_areas;

    public WinCombination(double reward_multiplier, int count, String group, List<List<String>> covered_areas, String when) {
        this.reward_multiplier = reward_multiplier;
        this.count = count;
        this.group = group;
        this.covered_areas = covered_areas;
        this.when = when;
    }

    public void setReward_multiplier(double reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setCovered_areas(List<List<String>> covered_areas) {
        this.covered_areas = covered_areas;
    }

    public WinCombination() {
    }

    // Getters
    public double getRewardMultiplier() { return reward_multiplier; }
    public int getCount() { return count; }
    public String getGroup() { return group; }
    public List<List<String>> getCoveredAreas() { return covered_areas; }
    public String getWhen() { return when; }
}

