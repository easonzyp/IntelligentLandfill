package com.wise.develop.IntelligentLandfill.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.wise.develop.IntelligentLandfill.R;

public class GlideUtil {

    public static void displayImage(Context activity, int url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .into(imageView);
    }

    public static void displayImage(Context activity, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            displayImage(activity, R.drawable.ic_default, imageView);
            return;
        }
        Glide.with(activity)
                .load(url)
                .into(imageView);
    }

    /**
     * 圆角效果
     */
    public static void displayRoundImage(Context activity, int url, ImageView imageView, int corner) {
        Glide.with(activity).load(url)
                .transform(new CenterCrop(), new CornersTransform(corner))
                .into(imageView);
    }

    /**
     * 圆角效果
     */
    public static void displayRoundImage(Context activity, String url, ImageView imageView, int corner) {
        if (TextUtils.isEmpty(url)) {
            displayRoundImage(activity, R.drawable.ic_default, imageView, corner);
            return;
        }
        Glide.with(activity).load(url)
                .transform(new CenterCrop(), new CornersTransform(corner))
                .into(imageView);
    }

    /**
     * 部分圆角效果
     */
    public static void displayPartRoundImage(Context activity,
                                             int url,
                                             ImageView imageView,
                                             boolean leftTop,
                                             boolean rightTop,
                                             boolean leftBottom,
                                             boolean rightBottom,
                                             int corner) {
        RoundedCornersTransform transform = new RoundedCornersTransform(activity, corner);
        transform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        Glide.with(activity)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new CenterCrop(), transform)
                .into(imageView);
    }

    /**
     * 部分圆角效果
     */
    public static void displayPartRoundImage(Context activity,
                                             String url,
                                             ImageView imageView,
                                             boolean leftTop,
                                             boolean rightTop,
                                             boolean leftBottom,
                                             boolean rightBottom,
                                             int corner) {
        if (TextUtils.isEmpty(url)) {
            displayPartRoundImage(activity, R.drawable.ic_default, imageView, leftTop, rightTop, leftBottom, rightBottom, corner);
            return;
        }
        RoundedCornersTransform transform = new RoundedCornersTransform(activity, corner);
        transform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        Glide.with(activity)
                .load(url)
                .transform(new CenterCrop(), transform)
                .into(imageView);
    }

    /**
     * 圆形图片
     */
    public static void displayCircleImage(Context activity, int path, ImageView imageView) {
        Glide.with(activity).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform())
                .into(imageView);
    }

    /**
     * 圆形图片
     */
    public static void displayCircleImage(Context activity, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            displayCircleImage(activity, R.drawable.ic_default, imageView);
            return;
        }
        Glide.with(activity)
                .load(url)
                .transform(new GlideCircleTransform())
                .into(imageView);
    }

    public static void loadImage(RequestManager glide, String url, ImageView view) {
        glide.load(url).into(view);
    }
}