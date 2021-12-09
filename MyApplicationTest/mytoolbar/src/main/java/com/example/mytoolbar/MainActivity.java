package com.example.mytoolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tb);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("cai", "onClick: toolbar被点击了");
            }
        });

        Toolbar toolbar2  = findViewById(R.id.tb2);
        toolbar2.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar2.setTitle("标题");
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("cai", "onClick: toolbar2被点击了");
            }
        });
    }
}