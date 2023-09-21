package org.example.param;

public enum When {
    SAME_SYMBOLS("same_symbols"),
    LINEAR_SYMBOLS("linear_symbols");
    private String param;

    When(String p) {
        param = p;
    }

    public String getName() {
        return param;
    }
}
