package com.baway.xushuai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.xushuai.R;
import com.baway.xushuai.bean.MyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation: RecycleView的适配器
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context context;
    private List<MyBean.DataBean.UserBean> list = new ArrayList();
    private LayoutInflater inflater;

    public RecycleViewAdapter(Context context, List<MyBean.DataBean.UserBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载条目视图
        View view = inflater.inflate(R.layout.recy_item, parent, false);
        //添加到内部类 MyViewHolder中
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //解析图片
        Glide.with(context).load(list.get(position).getHead()).into(holder.img);
        holder.title.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img, img1, img2, img3;
        private TextView title, content;

        public MyViewHolder(View itemView) {
            super(itemView);
            //获得控件id
            img = (ImageView) itemView.findViewById(R.id.img);
            img1 = (ImageView) itemView.findViewById(R.id.img1);
            img2 = (ImageView) itemView.findViewById(R.id.img2);
            img3 = (ImageView) itemView.findViewById(R.id.img3);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}