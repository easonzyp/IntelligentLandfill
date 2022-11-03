package com.wise.develop.IntelligentLandfill.wight;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wise.develop.IntelligentLandfill.R;
import com.wise.develop.IntelligentLandfill.wight.wheelview.WheelView;

import java.util.List;

public class TeamPicker extends Dialog implements View.OnClickListener {

    private List<String> teamList;
    private List<String> teamHeadList;

    private Context context;
    private View view;
    private WheelView wv_team;
    private WheelView wv_team_head;
    private TextView mTvConfirm;
    private TextView mTvCancel;
    private OnTownChooseListener onTeamChooseListener;
    private OnConfirmListener onConfirmListener;

    public TeamPicker(Context context) {
        super(context, R.style.transparentWindowStyle);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_team_picker, null);
        initView();
        setListener();
        this.setContentView(view);
        this.setCanceledOnTouchOutside(true);
        //从底部弹出
        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.windowAnimationStyle);  //添加动画
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
    }

    private void initView() {
        wv_team = view.findViewById(R.id.wv_team);
        wv_team_head = view.findViewById(R.id.wv_team_head);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mTvCancel = view.findViewById(R.id.tv_cancel);

        /*
         * 设置可见条目数量
         * 注：因为WheelView是圆形，最上面和最下面刚好在圆顶和圆底，
         * 所以最上面和最下面两个看不到，因此可见数量要比设置的少2个
         */
        wv_team.setVisibleItemCount(9);
        wv_team_head.setVisibleItemCount(9);

        wv_team.setCurrentItem(0);
    }

    public void setTeamData(List<String> teamList) {
        this.teamList = teamList;
        wv_team.setItems(this.teamList);
        wv_team.setCurrentItem(0);
    }

    public void setTeamHeadData(List<String> teamHeadList) {
        this.teamHeadList = teamHeadList;
        wv_team_head.setItems(this.teamHeadList);
        wv_team_head.setCurrentItem(0);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_confirm) {
            if (onConfirmListener != null) {
                String teamName = "";
                int teamPosition = 0;
                if (teamList != null && teamList.size() > 0) {
                    teamName = teamList.get(wv_team.getCurrentItem());
                    teamPosition = wv_team.getCurrentItem();
                }
                String teamHeadName = "";
                int teamHeadPosition = 0;
                if (teamHeadList != null && teamHeadList.size() > 0) {
                    teamHeadName = teamHeadList.get(wv_team_head.getCurrentItem());
                    teamHeadPosition = wv_team_head.getCurrentItem();
                }

                onConfirmListener.onConfirm(teamName, teamPosition, teamHeadName, teamHeadPosition);
            }
        }
        cancel();
    }

    /**
     * 回调接口
     */
    public interface OnTownChooseListener {
        void onTownSelected(String item, int position);
    }

    public void setTownListener(OnTownChooseListener onTownChooseListener) {
        this.onTeamChooseListener = onTownChooseListener;
    }

    public interface OnConfirmListener {
        void onConfirm(String teamName, int teamPosition, String teamHeadName, int teamHeadPosition);
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    /**
     * 设置监听
     */
    private void setListener() {

        wv_team.setOnItemSelectedListener(index -> {
            if (onTeamChooseListener != null && teamList.size() != 0) {
                String mCurrentTypeName = teamList.get(index);
                onTeamChooseListener.onTownSelected(mCurrentTypeName, index);
            }
        });
        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

}
