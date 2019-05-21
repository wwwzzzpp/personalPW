package com.wzp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.wzp.utils.NetworkUtils;

public class BaseWebView extends BridgeWebView {

    private Context context;

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context);
    }

    public BaseWebView(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        this.context = context;
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled( true );// 设置支持js交互
        if ( !NetworkUtils.isConnectivityAvailable( context ) )
        {
            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ONLY );// 没有网，只从缓存获取；在模式LOAD_CACHE_ONLY下，只从本地缓存中取数据
        } else
        {
            webSettings.setCacheMode( WebSettings.LOAD_NO_CACHE );// 有网 只从网络取；
        }
        webSettings.setSupportZoom( true );// 开启缩放
        webSettings.setBuiltInZoomControls( true );// 开启缩放
        webSettings.setDisplayZoomControls(false);//不显示缩放button
        setVerticalScrollBarEnabled( true );//设置竖向滚动条不显示
        setHorizontalScrollBarEnabled( true );//设置横向滚动条不显示
        webSettings.setDefaultZoom( WebSettings.ZoomDensity.FAR );
        webSettings.setDomStorageEnabled( true );// 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled( true );// 开启 database storage API 功能
        webSettings.setAppCacheEnabled( true );// 开启 Application Caches 功能
//        webSettings.setDatabasePath( cacheDirPath );// 设置数据库缓存路径
//        webSettings.setAppCachePath( cacheDirPath );// 设置 Application Caches缓存目录
        webSettings.setAllowFileAccess( true );
        webSettings.setAppCacheMaxSize( 1024 * 1024 * 8 );
        webSettings.setDefaultTextEncodingName( "UTF-8" );
        BaseWebViewClient client = new BaseWebViewClient(this);
        setWebViewClient(client);
    }
}
