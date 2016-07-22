package login.qiyun.com.mydemo.fragment.LookEventFragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;

/**
 * Created by Administrator on 2016/7/14.
 */
public abstract class LookEventBaseFragment extends Fragment {

    private View view;
    private WebView wv_look_event;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.look_event_base_fragment, container, false);

        wv_look_event = (WebView) view.findViewById(R.id.wv_look_event);
        WebSettings settings = wv_look_event.getSettings();
        settings.setJavaScriptEnabled(true);
//        settings.setBuiltInZoomControls(true);
//        settings.setUseWideViewPort(true);

        wv_look_event.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });

        wv_look_event.loadUrl(Utils.URL + setUrl());
//        wv_look_event.loadUrl("http://192.168.1.103/");

        initUI();


        return view;
    }


    protected abstract void initUI();

    public abstract String setUrl();

    public View getView(){
        return view;
    }
}
