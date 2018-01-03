package com.example.pradeep.word_meaningsaver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradeep.word_meaningsaver.dbattributes.UserPojo;
import com.example.pradeep.word_meaningsaver.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WordMeaningDetailsListFragment extends Fragment {

    DBHandler db;
    RecyclerView wordMeaningList;
    MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    List<UserPojo> userPojoList;




    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WordMeaningDetailsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.details, container, false);
        wordMeaningList = (RecyclerView)view.findViewById(R.id.wordmeaninglist);
        wordMeaningList.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }

    public void setUsersList(List<UserPojo> userPojoList) {
        this.userPojoList = userPojoList;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(userPojoList);
        wordMeaningList.setAdapter(myItemRecyclerViewAdapter);

    }

}
