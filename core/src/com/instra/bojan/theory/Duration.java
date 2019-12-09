package com.instra.bojan.theory;

public enum Duration {
    EIGHT("1/8"),
    QUARTER("1/4"),
    HALF("1/2"),
    WHOLE("1");

    String text;

    Duration(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static Duration getEnum(String value) {
        for(Duration v : values())
            if(v.text.equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
