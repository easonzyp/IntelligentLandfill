package com.wise.develop.Landfill.fragment;

import android.content.res.ColorStateList;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.adapter.MyPagerAdapter;
import com.wise.develop.Landfill.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceManageFragment extends BaseFragment {

    private LinearLayout ll_container;
    private TabLayout tb_tab;
    private ViewPager vp_pager;
    private List<String> mTitle;

    private List<BaseFragment> mFragment = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void initView() {
        ll_container = findViewById(R.id.ll_container);
        ll_container.setPadding(0, getStatusBarHeight(), 0, 0);
        tb_tab = findViewById(R.id.tb_tab);
        vp_pager = findViewById(R.id.vp_pager);
        vp_pager = findViewById(R.id.vp_pager);
        initFragment();
    }

    private void initFragment() {
        //标题集合
        mTitle = new ArrayList<>();
        mTitle.add("设备状态监控");
        mTitle.add("设备维护计划");
        mTitle.add("设备监控");

        //碎片集合
        mFragment = new ArrayList<>();
        mFragment.add(new DeviceStateMonitorFragment());
        mFragment.add(new DeviceRepairPlanFragment());
        mFragment.add(new DeviceMonitorFragment());

        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), context, mFragment, mTitle);
        vp_pager.setAdapter(myPagerAdapter);
        vp_pager.setOffscreenPageLimit(mTitle.size());
        tb_tab.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.white)));
        tb_tab.setupWithViewPager(vp_pager);
        tb_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_hidden_trouble;
    }
}