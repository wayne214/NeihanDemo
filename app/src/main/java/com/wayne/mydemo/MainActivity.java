package com.wayne.mydemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.wayne.mydemo.fragments.DiscoverFragment;
import com.wayne.mydemo.fragments.HomeFragment;
import com.wayne.mydemo.fragments.MessageFragment;
import com.wayne.mydemo.fragments.ReviewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragments = new ArrayList<>();//实例化list集合
        //当activity 发生重复创建或者横竖屏切换，内部的Fragment
        //会自动创建一遍
        FragmentManager manager = getSupportFragmentManager();
        if (savedInstanceState == null)//第一次创建
        {
            Fragment fragment = new HomeFragment();
            mFragments.add(fragment);
            fragment = new DiscoverFragment();
            mFragments.add(fragment);
            fragment = new ReviewFragment();
            mFragments.add(fragment);
            fragment = new MessageFragment();
            mFragments.add(fragment);
            FragmentTransaction transaction
                    = manager.beginTransaction();//开启事务
            int index = 0;
            for (Fragment f : mFragments) {
                //添加
                //第三个参数 相当于给Fragment设置一个tag，之后可以进行查找
                transaction.add(R.id.fragment_container, f, "tag" + index);//将fragment添加到FragmentLayout中
                //隐藏
                transaction.hide(f);//全部隐藏
                index++;
            }
            //第一个显示
            transaction.show(mFragments.get(0));
            transaction.commit();//最后要提交事务
        } else {//不是第一次创建，Fragment会自动的由Activity创建好
            //FragmentManager的管理
            for (int i = 0; i < 4; i++) {
                String tag = "tag" + i;
                //根据add时，设置的tag，来查找Fragment
                Fragment f = manager.findFragmentByTag(tag);
                if (f != null) {
                    mFragments.add(f);
                }
            }
        }
        //--------------------
        //Tab 切换
        RadioGroup group = (RadioGroup) findViewById(R.id.main_tab_bar);
        if (group != null) {
            group.setOnCheckedChangeListener(this);
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=0;
        switch (checkedId) {
            case R.id.main_tab_home:
                index=0;
                break;
            case R.id.main_tab_discover:
                index=1;
                break;
            case R.id.main_tab_review:
                index=2;
                break;
            case R.id.main_tab_message:
                index=3;
                break;
        }
        switchFragment(index);
    }
    private void switchFragment(int index)
    {
        if(index >=0 && index<mFragments.size())
        {
            int size=mFragments.size();
            FragmentManager manager = getSupportFragmentManager();//获取Fragment管理器
            FragmentTransaction transaction = manager.beginTransaction();
            for (int i = 0; i <size ; i++) {
                Fragment f=mFragments.get(i);
                    if (i==index){
                        //显示
                        transaction.show(f);
                    }else{
                        //隐藏
                        transaction.hide(f);
                    }
            }
            transaction.commit();
        }
    }
}
