package eu.sk.jakab.bsc.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import eu.sk.jakab.bsc.R;
import eu.sk.jakab.bsc.base.BaseFragment;
import eu.sk.jakab.bsc.network.model.Note;

/**
 * Created by jakab on 1/26/2020.
 */
public class DetailFragment extends BaseFragment<Contracts.DetailPresenter> implements Contracts.DetailView {
    private MainActivityInterface callback;
    private TextView txtTitle, txtDesc;

    public static DetailFragment newInstance() {
        return new DetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        callback.setHome(true,true);
        setHasOptionsMenu(true);

        txtTitle = view.findViewById(R.id.txtTitle);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail,menu);
    }

    @Override
    public void showNote(Note note) {
        txtTitle.setText(note.getTitle());
    }

    @Override
    public void exit() {
        callback.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                callback.onBackPressed();
                break;
            case R.id.action_save:
                presenter.saveNote(txtTitle.getText().toString());
                break;
            case R.id.action_remove:
                presenter.deleteNote();
                break;
        }
        return true;
    }
}
