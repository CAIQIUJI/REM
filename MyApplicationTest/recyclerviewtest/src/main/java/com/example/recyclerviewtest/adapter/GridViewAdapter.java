package com.example.recyclerviewtest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.bean.ItemBean;

import java.util.List;

public class GridViewAdapter extends RecyclerViewBaseAdapter {

    public GridViewAdapter(List<ItemBean> data) {
        super(data);
    }
    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.list_grid_view, null);
        return view;
    }

}