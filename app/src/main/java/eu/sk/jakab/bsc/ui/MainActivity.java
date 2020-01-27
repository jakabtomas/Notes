package eu.sk.jakab.bsc.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import eu.sk.jakab.bsc.R;
import eu.sk.jakab.bsc.base.BaseActivity;
import eu.sk.jakab.bsc.network.model.Note;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity implements MainActivityInterface {
    private static final String FRAGMENT_TAG_LIST  = "FRAGMENT_LIST";
    private static final String FRAGMENT_TAG_DETAIL = "FRAGMENT_DETAIL";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();

            ListFragment listFragment = (ListFragment) fm.findFragmentByTag(FRAGMENT_TAG_LIST);
            if(listFragment!=null){
                new ListPresenter(listFragment);
            }

            DetailFragment detailFragment = (DetailFragment) fm.findFragmentByTag(FRAGMENT_TAG_DETAIL);
            if(detailFragment!=null){
                new DetailPresenter(detailFragment, null, false);
            }
        }else {
            openNotesList();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount()==0){
            finish();
        }
    }

    private void openNotesList() {
        ListFragment listFragment = ListFragment.newInstance();
        addFragment(listFragment, R.id.fragment_main, FRAGMENT_TAG_LIST, true, null);
        new ListPresenter(listFragment);
    }

    @Override
    public void openNote(Note note) {
        DetailFragment detailFragment = DetailFragment.newInstance();
        replaceFragment(detailFragment, null, R.id.fragment_main, FRAGMENT_TAG_DETAIL, true);
        new DetailPresenter(detailFragment, note, note==null);
    }

    @Override
    public void setHome(boolean homeAsUpEnabled, boolean showHomeEnabled) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        getSupportActionBar().setDisplayShowHomeEnabled(showHomeEnabled);
    }
}
