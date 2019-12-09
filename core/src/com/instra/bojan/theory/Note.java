package com.instra.bojan.theory;

public enum Note {
    DO("do"),
    RE("re"),
    MI("mi"),
    FA("fa"),
    SO("so"),
    LA("la"),
    TI("ti");

    String text;

    Note(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static Note getEnum(String value) {
        for(Note v : values())
            if(v.text.equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }


}
