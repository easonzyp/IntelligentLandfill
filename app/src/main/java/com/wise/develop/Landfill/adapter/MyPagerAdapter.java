package com.wise.develop.Landfill.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wise.develop.Landfill.base.BaseFragment;

import java.util.List;

//懒加载版 每次点击刷新
public class MyPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<BaseFragment> fragmentList;
    private List<String> list_Title;

    public MyPagerAdapter(FragmentManager fm, Context context, List<BaseFragment> fragmentList, List<String> list_Title) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public long getItemId(int position) {
        return fragmentList.get(position).hashCode();
    }
    /**
     * //此方法用来显示tab上的名字
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }



}