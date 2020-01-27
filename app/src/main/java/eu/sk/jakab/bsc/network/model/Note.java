package eu.sk.jakab.bsc.network.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jakab on 1/26/2020.
 */
public class Note {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String title;

    public Note(String title) {
        this.title = title;
    }
}
