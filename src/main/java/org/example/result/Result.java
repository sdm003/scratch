package org.example.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Result {
    @JsonProperty()
    protected String[][] matrix;
    @JsonProperty()
    protected BigDecimal reward;

    public Result() {}

    public Result(String[][] matrix, BigDecimal reward) {
        this.matrix = matrix;
        this.reward = reward;
    }


    public String[][] getMatrix() {
        return matrix;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }
}
