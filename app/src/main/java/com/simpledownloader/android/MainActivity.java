package com.simpledownloader.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.simpledownloader.android.service.DownloadService;
import com.simpledownloader.android.util.PermissionUtil;
import com.simpledownloader.android.util.ToastUtil;

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

    private static String[] PERMISSIONS = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};



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
        PermissionUtil.checkPermission(this, PERMISSIONS, 1);
    }

    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }
        if (v.getId() == R.id.btn_start_download) {
            String url = "https://dldir1.qq.com/weixin/Windows/WeChatSetup.exe";
            downloadBinder.startDownload(url);
        } else if (v.getId() == R.id.btn_pause_download) {
            downloadBinder.pauseDownload();
        } else if (v.getId() == R.id.btn_cancel_download) {
            downloadBinder.cancelDownload();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (!PermissionUtil.checkGrant(grantResults)) {
                ToastUtil.show(this, "无权限");
            }
        }
    }
}