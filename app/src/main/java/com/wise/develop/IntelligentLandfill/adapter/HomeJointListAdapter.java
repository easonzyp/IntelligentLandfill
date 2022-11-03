package com.wise.develop.IntelligentLandfill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wise.develop.IntelligentLandfill.R;
import com.wise.develop.IntelligentLandfill.bean.HomeDayListBean;

import java.util.List;

public class HomeJointListAdapter extends RecyclerView.Adapter<HomeJointListAdapter.DayViewHolder> {

    private Context context;
    private List<String> dayList;

    public HomeJointListAdapter(Context context, List<String> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_joint_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {

    }

    public String getItem(int position) {
        return dayList.get(position);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {


        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
