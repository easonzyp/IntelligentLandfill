package com.wise.develop.Landfill.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.wise.develop.Landfill.MainApplication;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.base.BaseActivity;
import com.wise.develop.Landfill.base.BaseFragment;
import com.wise.develop.Landfill.constant.IntentKey;
import com.wise.develop.Landfill.dialog.MoreFunctionPopup;
import com.wise.develop.Landfill.dialog.TwoButtonCenterDialog;
import com.wise.develop.Landfill.dialog.UpdateCenterDialog;
import com.wise.develop.Landfill.fragment.DeviceManageFragment;
import com.wise.develop.Landfill.fragment.HistoryFragment;
import com.wise.develop.Landfill.fragment.HomeFragment;
import com.wise.develop.Landfill.fragment.WorkPlanFragment;
import com.wise.develop.Landfill.fragment.AccountFragment;

/**
 * Created by zyp on 2020/5/12 0020.
 * class note:主页
 */
public class MainActivity extends BaseActivity {
    private DrawerLayout dl_drawer;
    private View view_user_info;
    private ImageView iv_my;

    private RadioGroup rg_main;
    private final String[] titleArray = {"首页", "作业计划", "吨袋扫码", "设备管理", "台账"};
    private final int[] radioButtonArray = {R.id.rb_home, R.id.rb_operation, R.id.rb_find, R.id.rb_statistics, R.id.rb_train};
    private BaseFragment[] fragmentArray;
    private int currentIndex;

    private MoreFunctionPopup popup;
    private TwoButtonCenterDialog dialog;

    private boolean isLogout = false;
    private UpdateCenterDialog updateCenterDialog;

    @Override
    protected void initView() {
        rg_main = findViewById(R.id.rg_main);
        initFragment();

        LiveEventBus.get(IntentKey.HOME_TAB_SWITCH, Integer.class).observe(this, integer -> {
//                setIndexSelected(integer);
            rg_main.check(radioButtonArray[integer]);
        });
    }

    private void initUpdateDialog() {
        if (updateCenterDialog == null) {
            updateCenterDialog = new UpdateCenterDialog(context);
        }
        updateCenterDialog.show();
        updateCenterDialog.setOnClickRateDialog(() -> {

        });
    }

    private void initFragment() {
        fragmentArray = new BaseFragment[]{
                new HomeFragment(),
                new WorkPlanFragment(),
                new HistoryFragment(),
                new DeviceManageFragment(),
                new AccountFragment()
        };

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fl_container, fragmentArray[0]);
        ft.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        rg_main.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_home) {
                setIndexSelected(0);
            } else if (checkedId == R.id.rb_operation) {
                setIndexSelected(1);
            } else if (checkedId == R.id.rb_find) {
                toClass(context, TonBagInfoActivity.class);
//                setIndexSelected(2);
            } else if (checkedId == R.id.rb_statistics) {
                setIndexSelected(3);
            } else if (checkedId == R.id.rb_train) {
                setIndexSelected(4);
            }
        });
    }

    private void showLogoutDialog() {
        if (dialog == null) {
            dialog = new TwoButtonCenterDialog(context);
        }

        dialog.setTips("退出登录", "确定退出当前账号吗?");
        dialog.show();
        dialog.setOnClickRateDialog(new TwoButtonCenterDialog.OnClickRateDialog() {
            @Override
            public void onClickLeft() {
                dialog.dismiss();
            }

            @Override
            public void onClickRight() {
                dialog.dismiss();

                MainApplication.getContext().setToken("");
                Intent intent = new Intent();
                intent.setClass(context, LoginActivity.class);
                //关键的一句，将新的activity置为栈顶
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }


    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }

        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragmentArray[currentIndex]);
        //判断Fragment是否已经添加
        if (!fragmentArray[index].isAdded()) {
            ft.add(R.id.fl_container, fragmentArray[index]).show(fragmentArray[index]);
        } else {
            //显示新的Fragment
            ft.show(fragmentArray[index]);
        }
        ft.commit();
        currentIndex = index;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}