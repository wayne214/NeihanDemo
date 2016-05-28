package com.wayne.mydemo.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayne.mydemo.R;
import com.wayne.mydemo.fragments.home.EassyFragment;
import com.wayne.mydemo.fragments.home.ImageFragment;
import com.wayne.mydemo.fragments.home.QuanFragment;
import com.wayne.mydemo.fragments.home.RecommendFragment;
import com.wayne.mydemo.fragments.home.VideoFragment;
import com.wayne.mylibrary.CommonFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private ViewPager mPager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.home_tab_bar);
        mPager = (ViewPager) view.findViewById(R.id.home_pager);
        List<Fragment> subFragments = new ArrayList<>();

        subFragments.add(new RecommendFragment());
        subFragments.add(new VideoFragment());
        subFragments.add(new ImageFragment());
        subFragments.add(new EassyFragment());
        subFragments.add(new QuanFragment());

        CommonFragmentAdapter adapter =
                new CommonFragmentAdapter(getChildFragmentManager(),
                        subFragments
                );
        mPager.setAdapter(adapter);
        //TabLayout处理
        mTabLayout.setOnTabSelectedListener(this);
        TabLayout.Tab tab = mTabLayout.newTab();
        tab.setText("推荐");
        mTabLayout.addTab(tab);
        tab = mTabLayout.newTab();
        tab.setText("视频");
        mTabLayout.addTab(tab);
        tab = mTabLayout.newTab();
        tab.setText("图片");
        mTabLayout.addTab(tab);
        tab = mTabLayout.newTab();
        tab.setText("段子");
        mTabLayout.addTab(tab);
        tab = mTabLayout.newTab();
        tab.setText("圈子");
        mTabLayout.addTab(tab);

        TabLayout.TabLayoutOnPageChangeListener listener
                = new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mPager.addOnPageChangeListener(listener);

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        mPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
