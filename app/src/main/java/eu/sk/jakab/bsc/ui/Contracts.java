package eu.sk.jakab.bsc.ui;

import java.util.List;

import eu.sk.jakab.bsc.base.BasePresenter;
import eu.sk.jakab.bsc.base.BaseView;
import eu.sk.jakab.bsc.network.model.Note;

/**
 * Created by jakab on 1/26/2020.
 */
public interface Contracts {

    interface ListView extends BaseView<ListPresenter>{

        void showData(List<Note> notes);
    }

    interface ListPresenter extends BasePresenter{

    }

    interface DetailView extends BaseView<DetailPresenter>{

        void showNote(Note note);

        void exit();
    }

    interface DetailPresenter extends BasePresenter{

        void saveNote(String title);

        void deleteNote();
    }
}
