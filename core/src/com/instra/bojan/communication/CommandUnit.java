package com.instra.bojan.communication;

import com.instra.bojan.theory.Duration;
import com.instra.bojan.theory.Note;

public class CommandUnit {

    private Note note;
    private Duration duration;

    public CommandUnit(Note note, Duration duration) {
        this.note = note;
        this.duration = duration;
    }

    public CommandUnit(Note note) {
        this.note = note;
    }

    public CommandUnit(Duration duration) {
        this.duration = duration;
    }

    public Note getNote() {
        return note;
    }

    public Duration getDuration() {
        return duration;
    }
}
