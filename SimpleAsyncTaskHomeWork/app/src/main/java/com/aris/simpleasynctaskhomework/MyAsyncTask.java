package com.aris.simpleasynctaskhomework;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private WeakReference<TextView> mResultTextView;
    private static final int CHUNK_SIZE = 20;

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 2000;
        int chunksize = s/CHUNK_SIZE;

        for (int i = 0; i < CHUNK_SIZE; i++){
            try {
                Thread.sleep(chunksize);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100 / CHUNK_SIZE));
            Log.d(TAG, "doInBackground: " + (i + 1) * 100 / CHUNK_SIZE + " with the size of"+ chunksize);
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
        mResultTextView.get().setText("Progress: " + values[0] + "%");
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    public MyAsyncTask(TextView tv, ProgressBar progressbar,TextView result) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressbar);
        mResultTextView = new WeakReference<>(result);
    }
}
