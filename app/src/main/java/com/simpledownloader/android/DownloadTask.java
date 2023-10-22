package com.simpledownloader.android;

import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private DownloadListener listener;
    private boolean isPaused;
    private boolean isCanceled;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }
}
