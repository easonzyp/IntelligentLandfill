package com.wise.develop.IntelligentLandfill.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ProgressPercentView extends View {

    private int borderWidth = 20;
    private Paint outPaint;
    private Paint innerPaint;
    private float percent = 0;

    public ProgressPercentView(Context context) {
        this(context, null);
    }

    public ProgressPercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressPercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        outPaint = new Paint();
        outPaint.setColor(0xffDCDCDC);
        outPaint.setStrokeWidth(borderWidth);
        outPaint.setStrokeCap(Paint.Cap.ROUND);
        outPaint.setAntiAlias(true);
        outPaint.setStyle(Paint.Style.STROKE);

        innerPaint = new Paint();
        innerPaint.setColor(0xffE34637);
        innerPaint.setStrokeWidth(borderWidth);
        innerPaint.setStrokeCap(Paint.Cap.ROUND);
        innerPaint.setAntiAlias(true);
        innerPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentAngle = 270 * percent;

        int circleCenterCoordinates = getWidth() / 2;
        int circleRadius = getWidth() / 2;
        RectF rectF = new RectF(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth / 2, getHeight() - borderWidth / 2);
        canvas.drawArc(rectF, -90, 360, false, outPaint);
        canvas.drawArc(rectF, -90, 300, false, innerPaint);
    }

    public PointF calcArcEndPointXY(float cirX, float cirY, float radius, float
            cirAngle, float orginAngle) {
        cirAngle = (orginAngle + cirAngle) % 360;
        return calcArcEndPointXY(cirX, cirY, radius, cirAngle);
    }

    /*
     * @param cirAngle 当前弧角度
     */
    public PointF calcArcEndPointXY(float cirX, float cirY, float radius, float
            cirAngle) {
        float posX = 0.0f;
        float posY = 0.0f;
        // 将角度转换为弧度
        float arcAngle = (float) (Math.PI * cirAngle / 180.0);
        if (cirAngle < 90) {
            posX = cirX + (float) (Math.cos(arcAngle)) * radius;
            posY = cirY + (float) (Math.sin(arcAngle)) * radius;
        } else if (cirAngle == 90) {
            posX = cirX;
            posY = cirY + radius;
        } else if (cirAngle > 90 && cirAngle < 180) {
            arcAngle = (float) (Math.PI * (180 - cirAngle) / 180.0);
            posX = cirX - (float) (Math.cos(arcAngle)) * radius;
            posY = cirY + (float) (Math.sin(arcAngle)) * radius;
        } else if (cirAngle == 180) {
            posX = cirX - radius;
            posY = cirY;
        } else if (cirAngle > 180 && cirAngle < 270) {
            arcAngle = (float) (Math.PI * (cirAngle - 180) / 180.0);
            posX = cirX - (float) (Math.cos(arcAngle)) * radius;
            posY = cirY - (float) (Math.sin(arcAngle)) * radius;
        } else if (cirAngle == 270) {
            posX = cirX;
            posY = cirY - radius;
        } else {
            arcAngle = (float) (Math.PI * (360 - cirAngle) / 180.0);
            posX = cirX + (float) (Math.cos(arcAngle)) * radius;
            posY = cirY - (float) (Math.sin(arcAngle)) * radius;
        }
        return new PointF(posX, posY);
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }
}