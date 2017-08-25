package com.baway.xushuai.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baway.xushuai.R;
import com.baway.xushuai.TopBar;
import com.baway.xushuai.bean.MyBean;
import com.baway.xushuai.utils.Connection;
import com.baway.xushuai.utils.HttpUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation: 第一个Fragment  圈子
 */

public class CircleFragment extends Fragment {

    //创建自定义控件
    private TopBar topBar;
    private View view;
    private RecyclerView recycleView;
    private SpringView sv;
    private String url = "http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";
    private LinearLayoutManager layoutManager;
    private List<MyBean.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.circle_fragment, null);
        //查找控件
        initView();

        //实例化判断网络工具类
        boolean available = Connection.isNetworkAvailable(getActivity());
        //如果是true(当前有网) 就请求网络
        if (available == true) {
            initData();
        } else {
            //没有网络吐司提醒
            Toast.makeText(getActivity(), "当前无网络", Toast.LENGTH_SHORT).show();
        }

        //添加数据
//        initData();
        return view;
    }

    private void initView() {
        //自定义标题
        topBar = (TopBar) view.findViewById(R.id.topbar);
        //RecycleView
        recycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        sv = (SpringView) view.findViewById(R.id.sv);

        //设置默认的下拉上拉动画
        sv.setHeader(new DefaultHeader(getActivity()));
        sv.setFooter(new DefaultFooter(getActivity()));

//        RecycleViewAdapter adapter = new RecycleViewAdapter(getActivity(),data);
//        recycleView.setAdapter(adapter);

        //设置刷新类型
        sv.setType(SpringView.Type.FOLLOW);

        //SpringView的监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //停止刷新
                        sv.onFinishFreshAndLoad();
//                        data.clear();
//                        initData();
//                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        initData();
                        //停止加载
                        sv.onFinishFreshAndLoad();
//                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        //RecycleView滑动监听
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemPosition = layoutManager.findLastVisibleItemPosition();
                if (itemPosition == data.size() - 1) {
                    initData();
//                    adapter.notifyDataSetChanged();
                }
            }
        });

        //为自定义控件添加按钮的监听事件
//        topBar.setOnTopBarBtnsClick(new TopBar.TopBarBtnsOnClickListener() {
//            @Override
//            public void leftBtnOnClick() {
//                Toast.makeText(getActivity(), "全部", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightBtnOnClick() {
//                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
//            }
//        });

        //设置线性布局管理器
        layoutManager = new LinearLayoutManager(getActivity());
        //将管理器设置给RecycleView
        recycleView.setLayoutManager(layoutManager);
    }

    private void initData() {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Gson解析
                Gson gson = new Gson();
                //获得实体类里面的对象
                MyBean bean = gson.fromJson(response.body().string(), MyBean.class);
                data = bean.getData();
                System.out.println("data = " + data);
            }
        });
    }
}