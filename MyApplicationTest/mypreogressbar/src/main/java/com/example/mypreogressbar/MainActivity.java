package com.example.mypreogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ProgressBar progressBarLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb);
        progressBarLoad = findViewById(R.id.pbload);
    }

    public void caiClick(View view) {
        //当前是隐藏的
        if(progressBar.getVisibility() == View.GONE){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void load(View view) {
        int progress = progressBarLoad.getProgress();
        progress += 10;
        progressBarLoad.setProgress(progress);
    }
}