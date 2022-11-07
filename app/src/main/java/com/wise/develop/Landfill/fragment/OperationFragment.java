package com.wise.develop.Landfill.fragment;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.adapter.HomeJointListAdapter;
import com.wise.develop.Landfill.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class OperationFragment extends BaseFragment {
    private LinearLayout ll_container;
    private SmartRefreshLayout srl_refresh;
    private RecyclerView rv_joint_list;

    private HomeJointListAdapter jointListAdapter;
    private List<String> jointList;

    @Override
    protected void initView() {
        ll_container = findViewById(R.id.ll_container);
        srl_refresh = findViewById(R.id.srl_refresh);
        rv_joint_list = findViewById(R.id.rv_joint_list);
        ll_container.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListView() {
        jointList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            jointList.add("");
        }
        jointListAdapter = new HomeJointListAdapter(context, jointList);
        rv_joint_list.setLayoutManager(new LinearLayoutManager(context));
        rv_joint_list.setAdapter(jointListAdapter);
    }

    @Override
    protected void initClick() {
        srl_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srl_refresh.finishRefresh(1000);
            }
        });

        srl_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                srl_refresh.finishLoadMore(1000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_risk_list;
    }
}