package eu.sk.jakab.bsc.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import eu.sk.jakab.bsc.R;
import eu.sk.jakab.bsc.network.model.Note;

/**
 * Created by jakab on 1/26/2020.
 */
public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_NOTE = 1;
    private static final int TYPE_LOADING = 2;
    private List<Note> items;
    private static NotesAdapterInterface callback;
    
    public interface NotesAdapterInterface{

        void onNoteClicked(Note note);
    }

    public NotesAdapter(NotesAdapterInterface notesAdapterInterface) {
        this.callback = notesAdapterInterface;
    }

    @Override
    public int getItemViewType(int position) {
        if (items==null) {
            return TYPE_LOADING;
        }
        if (items.isEmpty()) {
            return TYPE_EMPTY;
        }
        return TYPE_NOTE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_LOADING:
                return new EmptyViewHolder(inflater.inflate(R.layout.row_loading, parent, false));
            case TYPE_EMPTY:
                return new EmptyViewHolder(inflater.inflate(R.layout.row_empty, parent, false));
            case TYPE_NOTE:
                return new NoteViewHolder(inflater.inflate(R.layout.row_note, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_NOTE:
                ((NoteViewHolder) holder).bind(items.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (items==null || items.isEmpty()) {
            return 1;
        }
        return items.size();
    }

    public void addItems(List<Note> notes) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.clear();
        this.items.addAll(notes);
        notifyDataSetChanged();
    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {
        EmptyViewHolder(View view) {
            super(view);
        }
    }

    private static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private CardView itemLayout;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            itemLayout = itemView.findViewById(R.id.layout_item);
        }

        public void bind(final Note note) {
            txtTitle.setText(note.getTitle());
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onNoteClicked(note);
                }
            });
        }
    }
}
