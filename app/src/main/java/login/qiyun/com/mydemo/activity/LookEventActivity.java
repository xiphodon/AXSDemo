package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.LookEventFragmentFactory;
import login.qiyun.com.mydemo.adapter.LookEventPagerAdapter;
import login.qiyun.com.mydemo.fragment.LookEventFragment.LookEventBaseFragment;
import login.qiyun.com.mydemo.view.PagerTab;

public class LookEventActivity extends BaseActivity {


    private View view;
    private PagerTab pt_look_event;
    private ViewPager vp_look_event;
    private String orderSignID;
    private TextView tv_look_event_other;

    @Override
    protected void initUI() {
        Intent intent = getIntent();
        orderSignID = intent.getStringExtra("orderSignID");
        Log.d("LookEventActivity","orderSignID" + orderSignID);

        initViewAndData();
    }

    private void initViewAndData() {
        view = getView();
        pt_look_event = (PagerTab) view.findViewById(R.id.pt_look_event);
        vp_look_event = (ViewPager) view.findViewById(R.id.vp_look_event);

        LookEventPagerAdapter lookEventPagerAdapter = new LookEventPagerAdapter(getSupportFragmentManager(),orderSignID,tv_look_event_other);
        vp_look_event.setAdapter(lookEventPagerAdapter);
        vp_look_event.setOffscreenPageLimit(1);

        pt_look_event.setViewPager(vp_look_event);
        pt_look_event.selectTab(0);

    }

    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_look_event;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText("执行现场");
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        this.tv_look_event_other = tv_other;
    }
}
