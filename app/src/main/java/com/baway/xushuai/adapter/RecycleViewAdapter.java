package com.baway.xushuai.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.xushuai.R;
import com.baway.xushuai.bean.MyBean;
import com.baway.xushuai.fragment.CircleFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation: RecycleView的适配器
 */

public class RecycleViewAdapter extends RecyclerView.Adapter {

    private List<MyBean.DataBean> list = new ArrayList<>();
    private CircleFragment mcontext;

    public RecycleViewAdapter(CircleFragment context) {
        this.mcontext = context;
    }

    //添加集合
    public void setData(List<MyBean.DataBean> data, boolean isRefresh) {
        if (isRefresh) {
            this.list = data;
        } else {
            this.list.addAll(data);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext.getContext(), R.layout.home_item, null);
        MyHolderView myHolderView = new MyHolderView(view);
        return myHolderView;
    }

    //绑定视图
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyHolderView my = (MyHolderView) holder;
        my.name.setText(list.get(position).User.name);
        Glide.with(mcontext).load(list.get(position).User.head).into(my.imageView);
        List<String> pictureList = list.get(position).Moment.pictureList;
        for (int i = 0; i < pictureList.size(); i++) {
            Glide.with(mcontext).load(pictureList.get(i)).into(my.imageone);
            Glide.with(mcontext).load(pictureList.get(i)).into(my.imagetwo);
            Glide.with(mcontext).load(pictureList.get(i)).into(my.imagethree);
        }

        my.riqi.setText(list.get(position).Moment.date);
        my.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.onCli(v, position);
            }
        });
        my.pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my.pinglunyu.setText(list.get(position).Moment.content);
            }
        });
    }

    //获取条目数量
    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    //定义viewholder
    class MyHolderView extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView name;
        private final ImageView imageone;
        private final ImageView imagetwo;
        private final ImageView imagethree;
        private final TextView riqi;
        private final TextView pinglun;
        private final TextView pinglunyu;

        public MyHolderView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            imageone = (ImageView) itemView.findViewById(R.id.imageone);
            imagetwo = (ImageView) itemView.findViewById(R.id.imagetwo);
            imagethree = (ImageView) itemView.findViewById(R.id.imagethree);
            riqi = (TextView) itemView.findViewById(R.id.riqi);
            pinglun = (TextView) itemView.findViewById(R.id.pinglun);
            pinglunyu = (TextView) itemView.findViewById(R.id.pinglunyu);
        }
    }

    //接口回调来获取点击条目
    public interface onClick {
        void onCli(View v, int position);
    }

    private onClick monClick;

    public void setOnClick(onClick onClick) {
        this.monClick = onClick;
    }
}