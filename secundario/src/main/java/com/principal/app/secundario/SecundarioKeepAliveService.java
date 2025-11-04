package com.principal.app.secundario;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class SecundarioKeepAliveService extends Service {
    private static final String CHANNEL_ID = "secundario_keep_alive_channel";
    private static final int NOTIF_ID = 9876;

    @Override
    public void onCreate() {
        super.onCreate();
        // create notification channel and start foreground to be more persistent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(CHANNEL_ID, "Secundario KeepAlive", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(ch);
        }
        Notification.Builder nb = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
            new Notification.Builder(this, CHANNEL_ID) :
            new Notification.Builder(this);
        nb.setContentTitle("Secundario ativo").setContentText("Serviço keep-alive em execução").setSmallIcon(android.R.drawable.ic_dialog_info);
        startForeground(NOTIF_ID, nb.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Sticky so system tries to recreate
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }
}
