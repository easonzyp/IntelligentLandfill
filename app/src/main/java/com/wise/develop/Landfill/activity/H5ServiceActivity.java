package com.wise.develop.Landfill.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.luck.picture.lib.tools.ToastUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.base.BaseActivity;

/**
 * H5服务页面
 * 提供原生能力
 */
public final class H5ServiceActivity extends BaseActivity {

    private static final String INTENT_KEY_CONFIG = "h5_service_config";

    private LinearLayout ll_container;
    private ProgressBar mProgressBar;
    private SmartRefreshLayout mRefreshLayout;
    private WebView mBrowserView;
    private String url;
    private WebSettings webSettings;

    public static void start(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            ToastUtils.s(context, "地址为空，请输入正确的地址！");
            return;
        }

        Intent intent = new Intent(context, H5ServiceActivity.class);
        intent.putExtra(INTENT_KEY_CONFIG, url);
        context.startActivity(intent);
    }

    @Override
    protected int setLayout() {
        return R.layout.h5_service_activity;
    }

    @Override
    protected void initView() {
        ll_container = findViewById(R.id.ll_container);
        ll_container.setPadding(0, getStatusBarHeight(), 0, 0);
        mProgressBar = findViewById(R.id.pb_browser_progress);
        mBrowserView = findViewById(R.id.wv_browser_view);
        webSettings = mBrowserView.getSettings();
        setWebSettings();
    }

    private void setWebSettings() {
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        /**
         * 此处配置影响三方html5调用控件
         */

        //解决http图片不显示
        webSettings.setBlockNetworkImage(false);
        //判断sdk版本、【处理http图片配置显示】
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setSavePassword(false);
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initData() {
        url = getIntent().getStringExtra(INTENT_KEY_CONFIG);
        mBrowserView.setWebViewClient(new AppBrowserViewClient());
        mBrowserView.setWebChromeClient(new AppBrowserChromeClient());
        mBrowserView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mBrowserView.canGoBack()) {
            // 后退网页并且拦截该事件
            mBrowserView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void reload() {
        mBrowserView.reload();
    }


    private class AppBrowserViewClient extends WebViewClient {

        /**
         * 开始加载网页
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        /**
         * 完成加载网页
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
//            mRefreshLayout.finishRefresh();
        }
    }

    private class AppBrowserChromeClient extends WebChromeClient {
        /**
         * 收到网页标题
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title == null) {
                return;
            }
            setTitle(title);
        }

        /**
         * 收到加载进度变化
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String msg = consoleMessage.message();
            return super.onConsoleMessage(consoleMessage);
        }
    }
}