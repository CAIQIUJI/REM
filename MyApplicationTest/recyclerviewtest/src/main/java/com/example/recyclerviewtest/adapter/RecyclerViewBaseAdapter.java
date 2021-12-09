package com.example.recyclerviewtest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.bean.ItemBean;

import java.util.List;

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerViewBaseAdapter.InnerHolder> {

    private final List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewBaseAdapter(List<ItemBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        return new InnerHolder(view);
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    //绑定Holder，一般用来设置数据
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //在这里设置数据
        holder.setData(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    /*
    * 编写回调的步骤
    * 1、创建这个接口
    * 2、定义接口内部的方法
    * 3、提供设置接口的方法（其实是外部实现）
    * 4、接口方法的调用
    * */

    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置一个监听，其实就是要设置一个接口，一个回调的接口
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView mIcon;
        private int mPosition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目的控件
            mIcon = (ImageView) itemView.findViewById(R.id.icon);
            mTitle = (TextView) itemView.findViewById(R.id.title);

            //接口方法的调用，设置回调
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mPosition);
                    }
                }
            });
        }

        //在这里回调会出错
        //这个方法用于设置数据
        public void setData(ItemBean itemBean, int position) {

            this.mPosition = position;

            //开始设置数据
            //有bug 估计class Datas错了
            //破案了 得用jpg格式图片 不能用导入的图
            mIcon.setImageResource(itemBean.icon);
            mTitle.setText(itemBean.title);
        }
    }
}
