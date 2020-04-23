package com.aris.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 2000;

        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    public MyAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }
}
