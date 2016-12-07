package com.example.gpcorser.bitsandpizzas;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class CookNotificationService extends IntentService {
    public static final String EXTRA_MESSAGE = "message";
    //private Handler handler;
    public static final int NOTIFICATION_ID = 5453;

    public CookNotificationService() {
        super("DelayedMessageService");
    }

//   @Override
//    public int onStartCommand(Intent intent, int flags,int startId){
//        handler = new Handler();
//        return super.onStartCommand(intent,flags,startId);
//    }

    @Override
    protected void onHandleIntent(Intent intent){
        synchronized (this) {
            try{
                wait(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(final String text){
/*       // Log.v("delayedMessageService","The message is: " +text);
        handler.post(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

            }
        });*/

        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setContentText(text)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,notification);
        Uri myNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone myRingtone = RingtoneManager.getRingtone(getApplicationContext(), myNotification);
        myRingtone.play();
    }

}
