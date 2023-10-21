package com.simpledownloader.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.simpledownloader.android.DownloadListener;
import com.simpledownloader.android.DownloadTask;

public class DownloadService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    private DownloadTask downloadTask;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailed() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onCanceled() {

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadTask = new DownloadTask(listener);
            }
        }

        public void pauseDownload() {

        }

        public void cancelDownload() {

        }
    }
}