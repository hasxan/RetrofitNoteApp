package com.example.hassansardar.retrofitnoteapp.activity.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hassansardar.retrofitnoteapp.R;
import com.example.hassansardar.retrofitnoteapp.model.Note;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecycledViewAdapter> {

    private Context context;
    private List<Note> notes;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecycledViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false);
        return new RecycledViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycledViewAdapter holder, int position) {
        Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date.setText(note.getDate());
        holder.card_item.setCardBackgroundColor(note.getColor());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class RecycledViewAdapter extends  RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title,tv_note,tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;

        public RecycledViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.card_item);
            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            itemClickListener.onItemClick(v,getAdapterPosition());

        }
    }

    public  interface ItemClickListener
    {
        void onItemClick(View view, int position);

    }
}
