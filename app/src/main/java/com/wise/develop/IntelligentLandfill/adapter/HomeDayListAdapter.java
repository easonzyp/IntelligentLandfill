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

public class HomeDayListAdapter extends RecyclerView.Adapter<HomeDayListAdapter.DayViewHolder> {

    private Context context;
    private List<HomeDayListBean> dayList;

    public HomeDayListAdapter(Context context, List<HomeDayListBean> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_day_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        HomeDayListBean item = getItem(position);
        holder.tv_month.setText(String.format("%s月", item.getMonth()));
        holder.tv_day.setText(item.getDay());
    }

    public HomeDayListBean getItem(int position) {
        return dayList.get(position);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_container;
        private TextView tv_month;
        private TextView tv_day;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_container = itemView.findViewById(R.id.ll_container);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_day = itemView.findViewById(R.id.tv_day);
        }
    }
}
