package com.example.pradeep.word_meaningsaver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pradeep.word_meaningsaver.dbattributes.UserPojo;


import org.w3c.dom.Text;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<UserPojo> mValues;

    public MyItemRecyclerViewAdapter(List<UserPojo> items) {
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_meaning_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.word.setText(holder.mItem.getWord());
        holder.meaning.setText(holder.mItem.getMeaning());



    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView word;
        public TextView meaning;

        public UserPojo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            word = (TextView)mView.findViewById(R.id.word);
            meaning = (TextView)mView.findViewById(R.id.meaning);

        }

        @Override
        public String toString() {
            return "p";
        }
    }
}
