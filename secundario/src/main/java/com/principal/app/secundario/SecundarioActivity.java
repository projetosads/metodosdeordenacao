package com.principal.app.secundario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecundarioActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundario);

        // Start its keep-alive service when activity starts
        Intent svc = new Intent(this, SecundarioKeepAliveService.class);
        startService(svc);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Activity destroyed; service continues to run
    }
}
