package com.wise.develop.Landfill.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2018/1/4.
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private int mOrientation;
    private Drawable mDivider;
    private int mDividerHeight = 10; //默认是2px
    private Paint mPaint;

    //绘制默认分割线
    public MyDividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        //系统属性中获取
        a.recycle();
        setOrientation(orientation);
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    public MyDividerItemDecoration(Context context, int orientation, int drawableId) {
        this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public MyDividerItemDecoration(Context context, int orientation, int dividerHeight, int dividerColor) {
        this(context, orientation);
        mDividerHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientaion");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(this.mOrientation == VERTICAL_LIST) {
            this.drawVertical(c, parent);
        } else {
            this.drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final  int left;
        final int right;
        // 判断RecyclerView是否要裁剪padding值
        if (parent.getClipToPadding()){
            // 需要裁剪那么就进行裁剪
            left = parent.getPaddingLeft();
            right = parent.getWidth()-parent.getPaddingRight();
            // 裁剪rv可视区域,  限制视图在该区域内是可见的，很重要的，这里
            canvas.clipRect(left,parent.getPaddingTop(),right,parent.getHeight()-parent.getPaddingBottom());
        }else {
            //  不裁剪则宽贼为rv的宽
            left = 0;
            right = parent.getWidth();
        }

        int childSize = parent.getChildCount();
        for(int i = 0; i < childSize; ++i) {
            View child = parent.getChildAt(i);
            // 获得item的信息包
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)child.getLayoutParams();
            // 分割线顶部 = item的底部 + item到底部的距离 + 动画偏移
            int top = child.getBottom() + layoutParams.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            // 分割线底部 =  分割线底部 + 高度
            int bottom = top + this.mDividerHeight;
            if(this.mDivider != null) {
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }

            if(this.mPaint != null) {
                canvas.drawRect((float)left, (float)top, (float)right, (float)bottom, this.mPaint);
            }
        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()){
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(),top,parent.getWidth()-parent.getPaddingRight(),bottom);
        }else {
            top  = 0;
            bottom = parent.getHeight();
        }

        int childSize = parent.getChildCount();

        for(int i = 0; i < childSize; ++i) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + this.mDividerHeight;
            if(this.mDivider != null) {
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }

            if(this.mPaint != null) {
                canvas.drawRect((float)left, (float)top, (float)right, (float)bottom, this.mPaint);
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDividerHeight);
        } else {
            outRect.set(0, 0, mDividerHeight, 0);
        }
    }
}
