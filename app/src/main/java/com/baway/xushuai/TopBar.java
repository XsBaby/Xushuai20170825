package com.baway.xushuai;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation: 自定义组合控件继承 RelativeLayout
 */
public class TopBar extends RelativeLayout {
    /**
     * 声明控件
     */
    private Button leftBtn;//左按钮
    private TextView textView;//中间文本框
    private Button rightBtn;//右按钮

    /**
     * 声明三个控件的属性
     */
    //声明左按钮的属性
    private String leftText;//按钮文本
    private Drawable leftBackground;//按钮背景
    private int leftTextColor;//按钮文本颜色
    //声明中间文本框的属性
    private String tbTitle;//文本框文本
    private float titleTextSize;//文本框字体大小
    private int titleTextColor;//文本框字体颜色
    //声明右按钮的属性
    private String rightText;//按钮文本
    private Drawable rightBackground;//按钮背景
    private int rightTextColor;//按钮文本颜色

    /**
     * 声明三个控件的布局属性
     */
    private LayoutParams leftBtnLayoutParams;
    private LayoutParams textViewLayoutParams;
    private LayoutParams rightBtnLayoutParams;

    //声明左右按钮点击监听
    private TopBarBtnsOnClickListener listener;

    //创建一个监听左右按钮点击的接口
    public interface TopBarBtnsOnClickListener {
        public void leftBtnOnClick();//左按钮被点击的事件

        public void rightBtnOnClick();//右按钮被点击的事件
    }

    //向外提供一个设置监听的方法
    public void setOnTopBarBtnsClick(TopBarBtnsOnClickListener listener) {
        this.listener = listener;
    }

    //重写构造方法
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 用TypedArray可以获取用户在xml中声明的此控件的所有属性,以键值对存储,
         * K:资源文件(例 R.styleable.topBar_leftText)
         * V:属性值
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.topBar);

        //为左按钮的属性赋值
        leftText = ta.getString(R.styleable.topBar_leftText);
        leftBackground = ta.getDrawable(R.styleable.topBar_leftBackground);
        leftTextColor = ta.getInt(R.styleable.topBar_leftTextColor, 0);

        //为中间的文本框的属性赋值
        tbTitle = ta.getString(R.styleable.topBar_tbTitle);
        titleTextSize = ta.getDimension(R.styleable.topBar_titleTextSize, 0);
        titleTextColor = ta.getInt(R.styleable.topBar_titleTextColor, 0);

        //为右按钮的属性赋值
        rightText = ta.getString(R.styleable.topBar_rightText);
        rightBackground = ta.getDrawable(R.styleable.topBar_rightBackground);
        rightTextColor = ta.getInt(R.styleable.topBar_rightTextColor, 0);

        //使用完TypedArray之后需要调用其recycle()方法,以便重用
        ta.recycle();

        //实例化三个控件
        leftBtn = new Button(context);
        textView = new TextView(context);
        rightBtn = new Button(context);

        //设置左按钮的属性
        leftBtn.setText(leftText);
        leftBtn.setBackground(leftBackground);
        leftBtn.setTextColor(leftTextColor);
        //设置文本框的属性
        textView.setText(tbTitle);
        textView.setTextSize(titleTextSize);
        textView.setTextColor(titleTextColor);
        textView.setGravity(Gravity.CENTER);
        //设置右按钮的属性
        rightBtn.setText(rightText);
        rightBtn.setBackground(rightBackground);
        rightBtn.setTextColor(rightTextColor);

        //设置此自定义控件的背景颜色
        setBackgroundColor(Color.BLUE);

        //实例化左按钮的布局属性
        leftBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置左按钮靠左显示
        leftBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //将左按钮添加到本自定义控件中
        addView(leftBtn, leftBtnLayoutParams);

        //同上
        rightBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightBtn, rightBtnLayoutParams);

        //同上
        textViewLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(textView, textViewLayoutParams);

        //回调左按钮的监听事件
        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftBtnOnClick();
            }
        });

        //回调右按钮的监听事件
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightBtnOnClick();
            }
        });

    }

    //当然,我们还可以添加其他控制此控件的方法,如设置左按钮是否可见等等,大家可根据自己的需求进行扩展
    public void setLeftBtnVisible(boolean isVisible) {
        leftBtn.setVisibility(isVisible ? VISIBLE : INVISIBLE);
    }
}