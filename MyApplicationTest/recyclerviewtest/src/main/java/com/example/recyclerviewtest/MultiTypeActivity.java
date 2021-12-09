package com.example.recyclerviewtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.adapter.MultiTypeAdapter;
import com.example.recyclerviewtest.bean.Datas;
import com.example.recyclerviewtest.bean.MultiTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiTypeActivity extends AppCompatActivity {
    private static final String TAG = "MultiTypeActivity";
    private RecyclerView mRecyclerView;
    private List<MultiTypeBean> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type_activity);

        //found a view here
        mRecyclerView = findViewById(R.id.multi_type_list);

        //初始化数据 准备数据
        mData = new ArrayList<>();
        InitData();

        //创建和设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //创建适配器
        MultiTypeAdapter adapter = new MultiTypeAdapter(mData);

        //设置适配器
        mRecyclerView.setAdapter(adapter);


    }

    private void InitData() {
        Random random = new Random();

        for (int i = 0; i < Datas.icons.length; i++){
            MultiTypeBean data = new MultiTypeBean();
            data.pic = Datas.icons[i];
            data.type = random.nextInt(3);
            Log.d(TAG, "type == " + data.type);
            mData.add(data);
        }

    }

}
