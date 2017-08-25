package com.baway.xushuai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.xushuai.R;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation: 第三个Fragment  我的
 */

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.mine_fragment, null);
        return view;
    }
}