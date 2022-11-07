package com.wise.develop.Landfill.wight;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.wight.wheelview.OnItemSelectedListener;
import com.wise.develop.Landfill.wight.wheelview.WheelView;

import java.util.List;

public class ItemPicker extends Dialog implements View.OnClickListener {

    private List<String> mTypeDatas;

    private Context context;
    private View view;
    private WheelView mViewType;
    private TextView mTvConfirm;
    private TextView mTvCancel;
    private OnTypeListener onTypeListener;

    public ItemPicker(Context context) {
        super(context, R.style.transparentWindowStyle);

        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_type_picker, null);

        initView();
        setListener();
        this.setContentView(view);

        this.setCanceledOnTouchOutside(true);

        //从底部弹出
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.pop_win_anim_style);  //添加动画

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    public void setItemData(List<String> itemData) {
        this.mTypeDatas = itemData;
        mViewType.setItems(mTypeDatas);
        mViewType.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_confirm) {
            if (onTypeListener != null && mTypeDatas.size() != 0) {
                String mCurrentTypeName = mTypeDatas.get(mViewType.getCurrentItem());
                onTypeListener.onTypeSelected(mCurrentTypeName, mViewType.getCurrentItem());
            }
        }
        cancel();
    }

    /**
     * 回调接口
     */
    public interface OnTypeListener {
        void onTypeSelected(String item, int position);
    }

    public void setTypeListener(OnTypeListener onTypeListener) {
        this.onTypeListener = onTypeListener;
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mViewType = (WheelView) view.findViewById(R.id.wv_type);
        mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);

        /**
         * 设置可见条目数量
         * 注：因为WheelView是圆形，最上面和最下面刚好在圆顶和圆底，
         * 所以最上面和最下面两个看不到，因此可见数量要比设置的少2个
         */
        mViewType.setVisibleItemCount(9);

    }

    /**
     * 设置监听
     */
    private void setListener() {

        mViewType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });

        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

}
