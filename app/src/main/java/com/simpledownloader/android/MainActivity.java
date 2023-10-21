package com.simpledownloader.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simpledownloader.android.service.DownloadService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_download) {

        } else if (v.getId() == R.id.btn_pause_download) {

        } else if (v.getId() == R.id.btn_cancel_download) {

        }
    }
}