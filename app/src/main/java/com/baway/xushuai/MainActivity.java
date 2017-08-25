package com.baway.xushuai;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.baway.xushuai.adapter.MyFragmentPagerAdapter;
import com.baway.xushuai.fragment.CircleFragment;
import com.baway.xushuai.fragment.FriendFragment;
import com.baway.xushuai.fragment.MineFragment;

import java.util.ArrayList;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation: 主Activity 用于添加布局和数据
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager myviewpager;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;
    //选项卡中的按钮
    private RadioButton btn_first;
    private RadioButton btn_second;
    private RadioButton btn_third;

    //作为指示标签的按钮
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的集合
    private int[] widthArgs;
    //所有按钮的集合
    private Button[] btnArgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找控件的方法
        initView();
    }

    private void initView() {
        myviewpager = (ViewPager) this.findViewById(R.id.viewPager);
        btn_first = (RadioButton) findViewById(R.id.btn_first);
        btn_second = (RadioButton) findViewById(R.id.btn_second);
        btn_third = (RadioButton) findViewById(R.id.btn_third);
        btnArgs = new Button[]{btn_first, btn_second, btn_third};

        cursor = (ImageView) this.findViewById(R.id.cursor_btn);

        cursor.setBackgroundColor(Color.RED);
        //给ViewPager设置改变的监听
        myviewpager.setOnPageChangeListener(this);

        //设置按钮的选中改变监听
        btn_first.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        btn_second.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
        btn_third.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());

        //添加Fragment
        fragments = new ArrayList<Fragment>();
        fragments.add(new CircleFragment());
        fragments.add(new FriendFragment());
        fragments.add(new MineFragment());
        //实例化Fragment和ViewPager适配器并将适配器添加到viewpager上
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);

        //重置所有按钮颜色
        resetButtonColor();
        //把第一个按钮的颜色设置为红色
        btn_first.setTextColor(Color.RED);
        //为什么不直接cursor.setWidth()和cursor.setX()
        //因为Android系统绘制原理是只有全部遍历测量之后才会布局，
        //只有在整个布局绘制完毕后，视图才能得到自身的高和宽。
        //所以在正常情况下，在OnCreate()方法中直接获取控件的宽度和高度取得值是0。
        //而我们此处设置指示器的大小和位置都需要用到第一个按钮的大小作为参考值，
        //所以可以通过post将一个runnable投递到消息队列的尾部，然后等待UI线程Looper调用此runnable的时候，view也已经初始化好了。这个时候就能成功获取控件的宽高
        btn_first.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
                //减去边距*2，以对齐标题栏文字
                lp.width = btn_first.getWidth() - btn_first.getPaddingLeft() * 2;
                cursor.setLayoutParams(lp);
                cursor.setX(btn_first.getPaddingLeft());
            }
        });
    }

    //把事件的内部类定义出来
    private class InnerOnCheckedChangeListener implements OnCheckedChangeListener {
        //单选按钮选中事件方法
        //buttonView表示谁的状态被改变
        //isChecked上面的参数代表的状态是否选中
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.btn_first:
                    //单选按钮通过参数isChecked去得到当前到底是选中还是未选中
                    if (isChecked) {
                        myviewpager.setCurrentItem(0);
                        cursorAnim(0);
                    }

                    break;
                case R.id.btn_second:
                    //单选按钮通过参数isChecked去得到当前到底是选中还是未选中
                    if (isChecked) {
                        myviewpager.setCurrentItem(1);
                        cursorAnim(1);
                    }

                    break;
                case R.id.btn_third:
                    //单选按钮通过参数isChecked去得到当前到底是选中还是未选中
                    if (isChecked) {
                        myviewpager.setCurrentItem(2);
                        cursorAnim(2);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //重置所有按钮的颜色
    public void resetButtonColor() {
        btn_first.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_second.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_third.setBackgroundColor(Color.parseColor("#DCDCDC"));

        btn_first.setTextColor(Color.BLACK);
        btn_second.setTextColor(Color.BLACK);
        btn_third.setTextColor(Color.BLACK);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (widthArgs == null) {
            widthArgs = new int[]{btn_first.getWidth(),
                    btn_second.getWidth(),
                    btn_third.getWidth()};
        }
        //每次滑动首先重置所有按钮的颜色
        resetButtonColor();

        //将滑动到的当前按钮颜色设置为红色
        btnArgs[position].setTextColor(Color.RED);

        cursorAnim(position);

        //把当前页面的单选按钮设置为选中状态
        ((CompoundButton) btnArgs[position]).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem) {
        //每次调用，就将指示器的横坐标设置0，即开始的位置
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
        //首先获得当前按钮的宽度，再减去按钮左右边距距，以对齐标题栏文本
        lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
        //通过指示标签对象，将标签设置到父容器中
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX + btnArgs[curItem].getPaddingLeft());
    }
}