package in.relsellglobal.androidnotificationsdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by anilkukreti on 03/05/17.
 */

public class MyService extends Service{

    TimerNotificationTask timerNotificationTask;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        timerNotificationTask = new TimerNotificationTask();
        if(timerNotificationTask != null) {
            timerNotificationTask.execute();
        }

        return START_NOT_STICKY;

    }

    public class TimerNotificationTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            while(!isCancelled()) {
                showAndUpadateNotification("Notification Demo By Relsell","Notification Message");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    public void cancelNoticationTimer() {
        if(timerNotificationTask != null && !timerNotificationTask.isCancelled()) {
            timerNotificationTask.cancel(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelNoticationTimer();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }



    public int showAndUpadateNotification(String notificationTitle, String notificationMessage) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());

        String[] arrNotificationMessage = notificationMessage.split(".");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                    .setColor(Color.parseColor("#9BA947"))
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationMessage)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMessage));

        } else {

            mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationMessage)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMessage));
        }


        mBuilder.setAutoCancel(true);


        // Below is the target activity which we want to launch if notication is tapped in dropdown panel

        Intent resultIntent = new Intent(getApplicationContext(), MyService.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager.notify(1, mBuilder.build());
        return 1;
    }

}
