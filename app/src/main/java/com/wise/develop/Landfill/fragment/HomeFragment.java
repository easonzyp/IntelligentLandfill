package com.wise.develop.Landfill.fragment;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.activity.H5ServiceActivity;
import com.wise.develop.Landfill.adapter.HomeDayListAdapter;
import com.wise.develop.Landfill.adapter.HomeJointListAdapter;
import com.wise.develop.Landfill.base.BaseFragment;
import com.wise.develop.Landfill.bean.HomeDayListBean;
import com.wise.develop.Landfill.constant.IntentKey;
import com.wise.develop.Landfill.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private LinearLayout ll_container;
    private RecyclerView rv_day_list;
    private RecyclerView rv_joint_list;

    private HomeDayListAdapter dayListAdapter;
    private List<HomeDayListBean> dayList;

    private HomeJointListAdapter jointListAdapter;
    private List<String> jointList;

    @Override
    protected void initView() {
        ll_container = findViewById(R.id.ll_container);
        rv_day_list = findViewById(R.id.rv_day_list);
        rv_joint_list = findViewById(R.id.rv_joint_list);
        ll_container.setPadding(0, getStatusBarHeight(), 0, 0);
        initListView();
    }

    @Override
    protected void initListView() {
        dayList = new ArrayList<>();
        for (int i = -3; i < 4; i++) {
            dayList.add(DateUtil.getPreOrNextDate(i));
        }
        dayListAdapter = new HomeDayListAdapter(context, dayList);
        rv_day_list.setLayoutManager(new GridLayoutManager(context, 7));
        rv_day_list.setAdapter(dayListAdapter);

        jointList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            jointList.add("");
        }
        jointListAdapter = new HomeJointListAdapter(context, jointList);
        rv_joint_list.setLayoutManager(new LinearLayoutManager(context));
        rv_joint_list.setAdapter(jointListAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        dayListAdapter.setOnItemClickListener(new HomeDayListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
//                LiveEventBus.get(IntentKey.HOME_TAB_SWITCH, Integer.class).post(IntentKey.SWITCH_WORK_PLAN);
//                H5ServiceActivity.start(context,"http://www.baidu.com");
                ARouter.getInstance().build("/app/TonBagInfoActivity")
                        .navigation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }
}