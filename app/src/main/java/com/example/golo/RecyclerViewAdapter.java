package com.example.golo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<String> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int index = -1;
    private final int[] compIcons = { R.drawable.ic_liganos, R.drawable.ic_champions, R.drawable.ic_ligueone,
                                R.drawable.ic_worldcup, R.drawable.ic_championship, R.drawable.ic_eredivisie,
                                R.drawable.ic_bundesliga, R.drawable.ic_premierleague, R.drawable.ic_euro2016,
                                R.drawable.ic_seriea, R.drawable.ic_laliga, R.drawable.ic_brasileirao};

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.competitions_rows, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setText(mData.get(position));
        Drawable iconEredivisie = ContextCompat.getDrawable(mInflater.getContext(), R.drawable.ic_eredivisie);
        Drawable iconPremierLeague = ContextCompat.getDrawable(mInflater.getContext(), R.drawable.ic_premierleague);
        iconEredivisie.setColorFilter(ContextCompat.getColor(mInflater.getContext(), R.color.colorPrimaryNight), PorterDuff.Mode.MULTIPLY);
        iconPremierLeague.setColorFilter(ContextCompat.getColor(mInflater.getContext(),R.color.colorPrimaryNight), PorterDuff.Mode.MULTIPLY);
        holder.imageView.setImageResource(compIcons[position]);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView myTextView;
        public final ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.compName);
            imageView = itemView.findViewById(R.id.compIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

        // convenience method for getting data at click position
        String getItem(int id) {
            return mData.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }

        public void clear() {
            mData.clear();
            notifyDataSetChanged();
        }

        public void addAll(List<String> list) {
            mData.addAll(list);
            notifyDataSetChanged();
        }

}
