package com.aris.simpleasynctaskhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mProgressTextView;
    private ProgressBar mProgressView;
    private static final String TEXT_STATE = "currentText";
    private static final String TEXT_PROGRESS = "CurrentSleepDuration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView1);
        mProgressView = findViewById(R.id.progressBar);
        mProgressTextView = findViewById(R.id.textViewResult);
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressTextView.setText(savedInstanceState.getString(TEXT_PROGRESS));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putString(TEXT_PROGRESS, mProgressTextView.getText().toString());
    }

    public void startTask (View view) {
        mTextView.setText(R.string.napping);
        new MyAsyncTask(mTextView, mProgressView, mProgressTextView).execute();

    }
}
