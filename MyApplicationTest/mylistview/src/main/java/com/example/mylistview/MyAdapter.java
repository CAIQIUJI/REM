package com.example.mylistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Bean> data;
    private Context context;

    public MyAdapter(List<Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            //LayoutInflater.from(context)从一个context中加载布局管理器，将xml布局转换为view对象
            viewHolder.textView = convertView.findViewById(R.id.tv);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        TextView textView = convertView.findViewById(R.id.tv);
        //这个比较耗时，ViewHolder优化一下
        viewHolder.textView.setText(data.get(position).getName());

        Log.e("cai", "getView: " + position);
        
        return convertView;
    }

    private final class ViewHolder{
        TextView textView;
    }
}
