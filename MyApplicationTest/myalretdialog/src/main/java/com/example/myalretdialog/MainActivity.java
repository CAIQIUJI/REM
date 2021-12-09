package com.example.myalretdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "cai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("InflateParams")
    public void caiClick(View view) {

        View dialogView = getLayoutInflater().inflate(R.layout.diaglog_view, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("我是对话框")
                .setMessage("今天天气怎么样啊")//set返回builder
                .setView(dialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClick: 点击了确定");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClick: 点击了取消");
                    }
                })
                .setNeutralButton("中间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClick: 点击了中间");
                    }
                })
                .create() //create返回alertdialog
                .show();//show只能放链式调用的最后 它是dialog里头的
    }
}