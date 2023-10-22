package com.simpledownloader.android.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.simpledownloader.android.DownloadListener;
import com.simpledownloader.android.DownloadTask;
import com.simpledownloader.android.util.NotificationUtil;
import com.simpledownloader.android.util.ToastUtil;

import java.io.File;

public class DownloadService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    private DownloadTask downloadTask;

    private String downloadUrl;

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

    public class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                Notification notification = NotificationUtil.createNotification(DownloadService.this, "Downloading...", 0);
                startForeground(1, notification);
                ToastUtil.show(DownloadService.this, "Downloading...");
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                //正在下载就取消（Task里会删除文件）
                downloadTask.cancelDownload();
            } else {
                //开始过下载且已经暂停就直接删除文件
                if (downloadUrl != null) {
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    NotificationUtil.getNotificationManager(DownloadService.this).cancel(1);
                    stopForeground(true);
                    ToastUtil.show(DownloadService.this, "Canceled");
                }
            }
        }
    }
}