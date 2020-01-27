package eu.sk.jakab.bsc.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import eu.sk.jakab.bsc.R;
import eu.sk.jakab.bsc.base.BaseFragment;
import eu.sk.jakab.bsc.network.model.Note;

/**
 * Created by jakab on 1/26/2020.
 */
public class ListFragment extends BaseFragment<Contracts.ListPresenter> implements Contracts.ListView, NotesAdapter.NotesAdapterInterface {
    private MainActivityInterface callback;
    private NotesAdapter notesAdapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityInterface) {
            callback = (MainActivityInterface) context;
        } else {
            throw new IllegalArgumentException(context + " should implement " + MainActivityInterface.class.getSimpleName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        callback.setHome(false,false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.openNote(null);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //adapter
        notesAdapter = new NotesAdapter(this);
        recyclerView.setAdapter(notesAdapter);

        return  view;
    }

    @Override
    public void showData(List<Note> notes) {
        notesAdapter.addItems(notes);
    }

    @Override
    public void onNoteClicked(Note note) {
        callback.openNote(note);
    }
}
