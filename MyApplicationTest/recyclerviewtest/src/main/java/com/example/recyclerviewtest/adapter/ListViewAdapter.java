package com.example.recyclerviewtest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerViewBaseAdapter {

    public ListViewAdapter(List<ItemBean> data){
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_list_view, null);
        return view;
    }


}
