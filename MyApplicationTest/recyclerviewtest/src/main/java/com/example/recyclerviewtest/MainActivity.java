package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recyclerviewtest.adapter.GridViewAdapter;
import com.example.recyclerviewtest.adapter.ListViewAdapter;
import com.example.recyclerviewtest.adapter.RecyclerViewBaseAdapter;
import com.example.recyclerviewtest.adapter.StaggerAdapter;
import com.example.recyclerviewtest.bean.Datas;
import com.example.recyclerviewtest.bean.ItemBean;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //成员变量
    private static final String TAG = "MainActivity";
    private RecyclerView mList;
    private SwipeRefreshLayout mRefreshLayout;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        mList = (RecyclerView) this.findViewById(R.id.recycler_view);
        mRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_layout);
        //准备数据
        /*数据一般是从网络中获取的
        这里仅模拟数据
        * */
        initData();
        //设置默认的显示样式为ListView效果
        showList(true, false);

        //处理下拉刷新
        handlerDownPullUpdate();
    }

    @SuppressLint("ResourceAsColor")
    private void handlerDownPullUpdate() {
        //不知道为啥我没有颜色变化
        mRefreshLayout.setColorSchemeColors(R.color.design_default_color_primary, R.color.design_default_color_primary_dark,R.color.design_default_color_error);
        //先让他可用
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //在这里面执行刷新数据的操作
                /*
                * 当我们在顶部，下拉的时候这个方法就会被触发
                * 但是，这个方法是MainThread是主线程，不可以执行耗时操作，
                * 一般来说，我们去请求数据要开一个线程去获取
                * 这里面仅添加一条数据作为演示
                * */

                //添加数据
                ItemBean data = new ItemBean();
                data.title = "我是新添加的数据，……";
                data.icon = R.drawable.ceshi;
                mData.add(0,data);

                //更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //这里要做两件事，一件是让刷新停止，另外一件事就是去更新列表
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    //条目在适配器里面，所以我们在适配器里头设置
    private void initListener(){
        mAdapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //这里处理条目的点击事件
                //来个吐司哈哈哈哈哈 虽然它很好笑 但吐司是个嘛玩意
                //Toast 是一个 View 视图，快速的为用户显示少量的信息。 Toast 在应用程序上浮动显示信息给用户，它永远不会获得焦点，不影响用户的输入等操作，主要用于 一些帮助 / 提示
                // 第一个参数：当前的上下文环境。可用getApplicationContext()或this
                // 第二个参数：要显示的字符串。也可是R.string中字符串ID
                // 第三个参数：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)，也可以使用毫秒如2000ms
                Toast.makeText(MainActivity.this, "您点击的是第" +position +"个条目",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initData() {
        //数据List<DataBean>----->Adapter------>setAdapter------>显示数据
        //Bean描述这个条目

        //创建数据集合
        mData = new ArrayList<>();

        //创建模拟数据
        for (int i = 0; i < Datas.icons.length; i++) {
            //创建数据对象
            ItemBean data = new ItemBean();
            data.icon = Datas.icons[i];
            data.title = "我是第" + i + "个条目";
            //添加到集合里头
            mData.add(data);
        }

        showList(true, false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            //ListView部分
            case R.id.list_view_vertical_standard:
                showList(true, false);
                break;
            case R.id.list_view_horizontal_standard:
                showList(false, false);
                break;
            case R.id.list_view_vertical_reverse:
                showList(true, true);
                break;
            case R.id.list_view_horizontal_reverse:
                showList(false, true);
                break;

            //GridView部分
            case R.id.grid_view_vertical_standard:
                showGrid(true, false);
                break;
            case R.id.grid_view_horizontal_standard:
                showGrid(false, false);
                break;
            case R.id.grid_view_vertical_reverse:
                showGrid(true, true);
                break;
            case R.id.grid_view_horizontal_reverse:
                showGrid(false, true);
                break;

            //StaggerView部分
            case R.id.stagger_view_vertical_standard:
                showStagger(true,false);
                break;
            case R.id.stagger_view_horizontal_standard:
                showStagger(false, false);
                break;
            case R.id.stagger_view_vertical_reverse:
                showStagger(true,true);
                break;
            case R.id.stagger_view_horizontal_reverse:
                showStagger(false, true);
                break;

            //多种条目类型被带点击了
            case R.id.multi_type:

                //跳到一个新的Activity里面去实现这个功能
                Intent intent = new Intent(this,MultiTypeActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showStagger(boolean isVertical, boolean isReverse) {
        //创建布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, isVertical?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL );
        //设置水平还是垂直的
        layoutManager.setOrientation(isVertical?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);

        mList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new StaggerAdapter(mData);

        //设置适配器
        mList.setAdapter(mAdapter);
        //初始化事件
        initListener();
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        //创建布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(isVertical? GridLayoutManager.VERTICAL: GridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new GridViewAdapter(mData);

        //设置适配器
        mList.setAdapter(mAdapter);
        //初始化事件
        initListener();

    }

    private void showList(boolean isVertical, boolean isReserve) {
        //RecyclerView需要设置样式，其实就是设置布局管理器
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);;
        //设置布局管理器来设置
        //设置水平还是垂直
        layoutManager.setOrientation(isVertical? LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        //设置反向还是正向
        layoutManager.setReverseLayout(isReserve);
        mList.setLayoutManager(layoutManager);

        //创建适配器：数据和控件的桥梁，用于把数据弄得适当，然后显示在需要显示的控件上
        mAdapter = new ListViewAdapter(mData);

        //设置到RecyclerView里头
        mList.setAdapter(mAdapter);
        //初始化事件
        initListener();

    }
}