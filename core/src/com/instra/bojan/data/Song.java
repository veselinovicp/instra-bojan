package com.instra.bojan.data;

public class Song {

    private String title;
    private String notation;

    public Song(String title, String notation) {
        this.title = title;
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    @Override
    public String toString() {
        return title;
    }
}
