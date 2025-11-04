package com.principal.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MonitorService extends Service {
    private static final String TAG = "MonitorService";
    private Handler handler = new Handler();
    private Runnable checkTask = new Runnable() {
        @Override
        public void run() {
            try {
                // Attempt to start the secundario keep-alive service (idempotent)
                Intent svc = new Intent(MonitorService.this, com.principal.app.secundario.SecundarioKeepAliveService.class);
                startService(svc);

                // Also try to bring the activity to front if desired
                Intent act = new Intent(MonitorService.this, com.principal.app.secundario.SecundarioActivity.class);
                act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(act);
            } catch (Exception e) {
                Log.e(TAG, "Erro ao tentar iniciar secundario: " + e.getMessage());
            } finally {
                handler.postDelayed(this, 10 * 1000); // check every 10s
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        handler.post(checkTask);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(checkTask);
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
