package com.wise.develop.Landfill.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.base.BaseFragment;

public class DeviceMonitorFragment extends BaseFragment {
    private ProgressBar mProgressBar;
    private WebView mBrowserView;
    private WebSettings webSettings;

    @Override
    protected void initView() {
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

        //解决http图片不显示
        webSettings.setBlockNetworkImage(false);
        //判断sdk版本、【处理http图片配置显示】
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initData() {
        mBrowserView.setWebViewClient(new AppBrowserViewClient());
        mBrowserView.setWebChromeClient(new AppBrowserChromeClient());
        mBrowserView.loadUrl("http://www.baidu.com");
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
         * 收到加载进度变化
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_device_monitor;
    }
}
