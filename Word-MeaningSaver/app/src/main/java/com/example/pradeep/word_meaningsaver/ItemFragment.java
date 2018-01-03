package com.example.pradeep.word_meaningsaver;

import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradeep.word_meaningsaver.dbattributes.UserPojo;
import com.example.pradeep.word_meaningsaver.dummy.DummyContent;
import com.example.pradeep.word_meaningsaver.dummy.DummyContent.DummyItem;

import java.util.List;


public class ItemFragment extends Fragment {

    DBHandler db;
    RelativeLayout relative;
    public EditText word;
    public EditText meaning;
    public Button save;
    public TextView jump;
    public Vibrator vibe;

    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item, container, false);
        relative=(RelativeLayout)view.findViewById(R.id.relative);
        relative.getBackground().setAlpha(50);
        save=(Button)view.findViewById(R.id.save);
        word=(EditText) view.findViewById(R.id.word);
        meaning=(EditText) view.findViewById(R.id.meaning);
        jump=(TextView)view.findViewById(R.id.jump);

        db = new DBHandler(getActivity());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

                vibe.vibrate(100);

                String w=word.getText().toString();
                String m=word.getText().toString();
                if ((w.equalsIgnoreCase(""))||(m.equalsIgnoreCase(""))){
                    Toast.makeText(getActivity(),"Can't Leave word and meaning field blank",Toast.LENGTH_LONG).show();
                }
                else {


                    new DBWriter().execute(word.getText().toString(), meaning.getText().toString());//call doinbackground dbwriter
                    Toast.makeText(getActivity(), "Storing Values......", Toast.LENGTH_LONG).show();
                }


            }
        });


        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

                vibe.vibrate(100);
                new DBReader().execute();//call doinbackground dbreader



            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(DummyItem item);


    }


    public class DBWriter extends AsyncTask<String,String,Void> {

        @Override
        protected Void doInBackground(String... params) {
            UserPojo user1 = new UserPojo();
            user1.setWord(params[0]);
            user1.setMeaning(params[1]);
            db.addUserData(user1);


            return null;
        }


    }


   /*public class DBReader extends AsyncTask<Void,Void,UserPojo> {

        @Override
        protected UserPojo doInBackground(Void... params) {
            //sending string data "pradeep" to DBHANDLER fetuserdata()
            UserPojo user = db.fetchUserData("pradeep");

            return user;
        }

        @Override
        protected void onPostExecute(UserPojo user) {
            super.onPostExecute(user);

            Toast.makeText(getActivity(),user.getWord()+ " " + user.getMeaning(),Toast.LENGTH_LONG).show();

        }
    }*/


    public class DBReader extends AsyncTask<Void,Void,List<UserPojo> > {

        @Override
        protected List<UserPojo> doInBackground(Void... params) {
            return db.fetchAllData();
        }

        @Override
        protected void onPostExecute(List<UserPojo> userPojoList) {
            super.onPostExecute(userPojoList);

            ((MainActivity)getActivity()).callDetailFragment(userPojoList);



        }
    }


}
