package eu.sk.jakab.bsc.ui;

import java.util.List;

import eu.sk.jakab.bsc.network.model.Note;
import eu.sk.jakab.bsc.network.operations.NotesOperations;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by jakab on 1/26/2020.
 */
public class ListPresenter implements Contracts.ListPresenter {
    private Contracts.ListView view;
    private NotesOperations notesOperations = new NotesOperations();
    private CompositeDisposable disposables = new CompositeDisposable();

    public ListPresenter(Contracts.ListView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        disposables.add(
                notesOperations.getAllNotes()
                        .subscribeWith(new DisposableObserver<List<Note>>() {
                            @Override
                            public void onNext(List<Note> notes) {
                                view.showData(notes);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showError(e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }
}
