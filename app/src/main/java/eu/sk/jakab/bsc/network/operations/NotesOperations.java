package eu.sk.jakab.bsc.network.operations;

import java.util.List;

import eu.sk.jakab.bsc.network.model.Note;
import eu.sk.jakab.bsc.network.services.NotesService;
import eu.sk.jakab.bsc.utils.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by jakab on 7/29/2019.
 */
public class NotesOperations {

    public Observable<List<Note>> getAllNotes() {
        return NotesService.getInstance()
                .geAllNotes()
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }

    public Observable<Note> getNote(int id) {
        return NotesService.getInstance()
                .getNote(id)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }

    public Observable<Note> createNote(Note note) {
        return NotesService.getInstance()
                .createNote(note)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }

    public Observable<ResponseBody> updateNote(Note note) {
        return NotesService.getInstance()
                .updateNote(note.getId(), note)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }

    public Observable<ResponseBody> deleteNote(int id) {
        return NotesService.getInstance()
                .deleteNote(id)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }
}
