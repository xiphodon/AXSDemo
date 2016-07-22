package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import login.qiyun.com.mydemo.R;


public class LookContractActivity extends BaseActivity {

    @Override
    protected void initUI() {
        Intent intent = getIntent();
        final String picUrl = intent.getStringExtra("PicUrl");

        Log.d("LookContract_picUrl",picUrl);

        View view = getView();

//
//        ImageView iv_contract = (ImageView) view.findViewById(R.id.iv_contract);
//        BitmapUtils bitmapUtils = new BitmapUtils(this);
//        bitmapUtils.display(iv_contract,picUrl);

        WebView wv_look_contract = (WebView) view.findViewById(R.id.wv_look_contract);
        WebSettings settings = wv_look_contract.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);

        wv_look_contract.setWebViewClient(new WebViewClient(){
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

        wv_look_contract.loadUrl(picUrl);
    }

    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_look_contract;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText("查看合同");
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setVisibility(View.GONE);
    }

}
