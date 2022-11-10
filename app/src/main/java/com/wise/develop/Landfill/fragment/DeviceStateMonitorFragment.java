package com.wise.develop.Landfill.fragment;

import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.adapter.DeviceStateMonitorListAdapter;
import com.wise.develop.Landfill.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class DeviceStateMonitorFragment extends BaseFragment {

    private EditText et_name;
    private TextView tv_location;
    private TextView tv_search;
    private TextView tv_type;
    private TextView tv_time;
    private TextView tv_reset;
    private RecyclerView rv_list;

    private DeviceStateMonitorListAdapter adapter;
    private List<String> list;

    @Override
    protected void initView() {
        et_name = findViewById(R.id.et_name);
        tv_location = findViewById(R.id.tv_location);
        tv_search = findViewById(R.id.tv_search);
        tv_type = findViewById(R.id.tv_type);
        tv_time = findViewById(R.id.tv_time);
        tv_reset = findViewById(R.id.tv_reset);
        rv_list = findViewById(R.id.rv_list);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListView() {
        list = new ArrayList<>();
        adapter = new DeviceStateMonitorListAdapter(context,list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initClick() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_device_state_monitor;
    }
}
