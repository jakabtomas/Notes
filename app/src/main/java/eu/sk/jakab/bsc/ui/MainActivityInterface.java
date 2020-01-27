package eu.sk.jakab.bsc.ui;

import eu.sk.jakab.bsc.network.model.Note;

/**
 * Created by jakab on 1/26/2020.
 */
public interface MainActivityInterface {
    void openNote(Note note);
    void setHome (boolean homeAsUpEnabled, boolean showHomeEnabled);

    void onBackPressed();
}
