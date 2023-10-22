package com.simpledownloader.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.simpledownloader.android.service.DownloadService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_start_download = findViewById(R.id.btn_start_download);
        Button btn_pause_download = findViewById(R.id.btn_pause_download);
        Button btn_cancel_download = findViewById(R.id.btn_cancel_download);
        btn_start_download.setOnClickListener(this);
        btn_pause_download.setOnClickListener(this);
        btn_cancel_download.setOnClickListener(this);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }
        if (v.getId() == R.id.btn_start_download) {
            String url = "";
            downloadBinder.startDownload(url);
        } else if (v.getId() == R.id.btn_pause_download) {
            downloadBinder.pauseDownload();
        } else if (v.getId() == R.id.btn_cancel_download) {
            downloadBinder.cancelDownload();
        }
    }
}