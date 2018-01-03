package com.example.pradeep.word_meaningsaver;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.pradeep.word_meaningsaver.dbattributes.UserPojo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradeep on 26/7/17.
 */

public class MyIntentService extends IntentService {

    DBHandler db;
    public MyIntentService() {
        super("MyIntent Service");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        db= new DBHandler(getBaseContext());
        List<UserPojo> userPojoList=db.fetchAllData();
        writeToFile(userPojoList);


}
    public void writeToFile(List<UserPojo> userPojoList)  {
        // Get the directory for the user's public pictures directory.
        final File path = new File(Environment.getExternalStorageDirectory(), "WORD_MEANING_FOLDER");

        // Make sure the path directory exists.
        if (!path.exists()) {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "word_meaning.txt");

        // Save your stream, don't forget to flush() it before closing it
        try {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            int z = 1;
            for (UserPojo s:userPojoList) {
                myOutWriter.write(z+s.toString());
                z++;
                System.out.print("Test "+s);
            }
            myOutWriter.close();

            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
