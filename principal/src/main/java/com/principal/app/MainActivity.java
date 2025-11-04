package com.principal.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartMonitor = findViewById(R.id.buttonStartMonitor);
        btnStartMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the monitor service which will ensure secundario stays active
                Intent svc = new Intent(MainActivity.this, MonitorService.class);
                startService(svc);
            }
        });

        Button btnLaunchSec = findViewById(R.id.buttonLaunchSec);
        btnLaunchSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Directly launch secundario activity
                Intent intent = new Intent(MainActivity.this, com.principal.app.secundario.SecundarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
