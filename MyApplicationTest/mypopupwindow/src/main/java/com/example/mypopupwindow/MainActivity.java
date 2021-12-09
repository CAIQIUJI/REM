package com.example.mypopupwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "cai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void caiClick(View view) {
        View popupView = getLayoutInflater().inflate(R.layout.popup, null);

        Button btn1 = popupView.findViewById(R.id.btn1);
        Button btn2 = popupView.findViewById(R.id.btn2);

        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);//我这个focuable好像不行

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ceshi));

        popupWindow.showAsDropDown(view,view.getWidth(),view.getHeight());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 住上海？");
                popupWindow.dismiss();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 住北京？" );
                popupWindow.dismiss();
            }
        });

    }
}