package com.baway.xushuai.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baway.xushuai.R;
import com.baway.xushuai.TopBar;
import com.baway.xushuai.adapter.RecycleViewAdapter;
import com.baway.xushuai.bean.MyBean;
import com.baway.xushuai.utils.Connection;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    private List<MyBean.DataBean> list;
    private boolean isRefresh = true;
    private RecycleViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.circle_fragment, null);

        //添加数据
//        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断网络连接状态
        if (Connection.isNetWorkConnection(getActivity())) {
            initView();
            initData();
        } else {
            Toast.makeText(getActivity(), "没有网络，请连接网络", Toast.LENGTH_SHORT).show();
        }
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
                        list.clear();
                        initData();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        //停止加载
                        sv.onFinishFreshAndLoad();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        //为自定义控件添加按钮的监听事件
        topBar.setOnTopBarBtnsClick(new TopBar.TopBarBtnsOnClickListener() {
            @Override
            public void leftBtnOnClick() {
                Toast.makeText(getActivity(), "全部", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightBtnOnClick() {
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //设置线性布局管理器
        layoutManager = new LinearLayoutManager(getActivity());
        //将管理器设置给RecycleView
        recycleView.setLayoutManager(layoutManager);
    }

    private void initData() {
        adapter = new RecycleViewAdapter(this);
        recycleView.setAdapter(adapter);

        final boolean[] isLoadMore = {false};
        //RecycleView滑动监听
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    //获得最后一个显示的item下标
                    int lastposition = layoutManager.findLastVisibleItemPosition();
                    //获得item数量
                    int itemCount = layoutManager.getItemCount();
                    if (lastposition + 1 == itemCount && !isLoadMore[0]) {
                        isLoadMore[0] = true;
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                SystemClock.sleep(1000);
                                isRefresh = false;
                                adapter.setData(list.subList(0, list.size()), isRefresh);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        isLoadMore[0] = false;

                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }.start();
                    }
                }
            }
        });
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String str = response.body().string();
                    Log.d("haha", "" + str);
                    Gson gson = new Gson();
                    MyBean bean = gson.fromJson(str, MyBean.class);
                    list = bean.data;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isRefresh = true;
                            adapter.setData(list, isRefresh);
                            adapter.notifyDataSetChanged();
                        }
                    });

                } else {
                    Log.d("myMessage", "" + response.message());
                }
            }
        });
    }
}