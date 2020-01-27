package eu.sk.jakab.bsc.ui;

import eu.sk.jakab.bsc.R;
import eu.sk.jakab.bsc.network.NoContentException;
import eu.sk.jakab.bsc.network.model.Note;
import eu.sk.jakab.bsc.network.operations.NotesOperations;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by jakab on 1/26/2020.
 */
public class DetailPresenter implements Contracts.DetailPresenter {
    private Contracts.DetailView view;
    private NotesOperations notesOperations = new NotesOperations();
    private CompositeDisposable disposables = new CompositeDisposable();
    private Note note;
    private boolean createNewNote;

    public DetailPresenter(Contracts.DetailView view, Note note, boolean createNewNote) {
        this.view = view;
        this.note = note;
        this.createNewNote = createNewNote;
        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (!createNewNote && note != null) {
            view.showNote(note);
        }
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }

    @Override
    public void saveNote(String title) {
        if (createNewNote) {
            disposables.add(notesOperations.createNote(new Note(title))
                    .subscribeWith(new DisposableObserver<Note>() {
                        @Override
                        public void onNext(Note newNote) {
                            note = newNote;
                            createNewNote=false;
                            view.showToast(android.R.string.ok);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showError(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    }));
        } else {
            note.setTitle(title);
            disposables.add(notesOperations.updateNote(note)
                    .subscribeWith(new DisposableObserver<ResponseBody>() {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            view.showToast(android.R.string.ok);
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
    }

    @Override
    public void deleteNote() {
        disposables.add(notesOperations.deleteNote(note.getId())
                .subscribeWith(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof NoContentException) {
                            view.exit();
                        } else {
                            view.showError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}

