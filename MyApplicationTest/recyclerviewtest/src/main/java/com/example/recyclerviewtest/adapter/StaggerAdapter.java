package com.example.recyclerviewtest.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.bean.ItemBean;

import java.util.List;

public class StaggerAdapter extends RecyclerViewBaseAdapter{

    public StaggerAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_stagger_view, null);
        return view;
    }
}
