
package com.example.pradeep.word_meaningsaver;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pradeep.word_meaningsaver.dbattributes.UserPojo;
import com.example.pradeep.word_meaningsaver.dummy.DummyContent;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnFragmentInteractionListener{

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callService();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.parent,new ItemFragment());
        fragmentTransaction.commit();

    }



    public void callDetailFragment(List<UserPojo> userPojoList) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WordMeaningDetailsListFragment wordMeaningDetailsListFragment = new WordMeaningDetailsListFragment();
        wordMeaningDetailsListFragment.setUsersList(userPojoList);
        fragmentTransaction.replace(R.id.parent,wordMeaningDetailsListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void callService(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
            else {

                i = new Intent(MainActivity.this,MyIntentService.class);
                startService(i);
                //Toast.makeText(MainActivity.this, "Permision not Granted", Toast.LENGTH_SHORT).show();
            }
        } else {
            i = new Intent(MainActivity.this,MyIntentService.class);
            startService(i);
        }

    }

    //callback method which is automatically called when permissions granted by user
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                i = new Intent(MainActivity.this,MyIntentService.class);
                startService(i);
            }
        }
    }

}
