package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.application.MyApplication;
import login.qiyun.com.mydemo.fragment.CompletedFragment;
import login.qiyun.com.mydemo.fragment.ExecutingFragment;
import login.qiyun.com.mydemo.fragment.NewOrderFragment;
import login.qiyun.com.mydemo.fragment.SettingFragment;
import login.qiyun.com.mydemo.protocol.TotalItemCountProtocol;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout id_tab_new_order;
    private LinearLayout id_tab_executing;
    private LinearLayout id_tab_completed;
    private LinearLayout id_tab_settings;

    private ImageView id_tab_new_order_img;
    private ImageView id_tab_executing_img;
    private ImageView id_tab_completed_img;
    private ImageView id_tab_settings_img;

    private TextView id_tab_new_order_text;
    private TextView id_tab_executing_text;
    private TextView id_tab_completed_text;
    private TextView id_tab_settings_text;

    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;
    private TextView tv_title;
    private ImageView iv_back;
    private SharedPreferences sp;
    private int userID;
    private TextView tv_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();

        initData();

        setSelect(0);
    }

    private void initData() {
        sp = getSharedPreferences("user", MODE_PRIVATE);
        userID = sp.getInt("UserID", -1);

        if (userID == -1){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }

    private void initView() {
        id_tab_new_order = (LinearLayout) findViewById(R.id.id_tab_new_order);
        id_tab_executing = (LinearLayout) findViewById(R.id.id_tab_executing);
        id_tab_completed = (LinearLayout) findViewById(R.id.id_tab_completed);
        id_tab_settings = (LinearLayout) findViewById(R.id.id_tab_settings);

        id_tab_new_order_img = (ImageView) findViewById(R.id.id_tab_new_order_img);
        id_tab_executing_img = (ImageView) findViewById(R.id.id_tab_executing_img);
        id_tab_completed_img = (ImageView) findViewById(R.id.id_tab_completed_img);
        id_tab_settings_img = (ImageView) findViewById(R.id.id_tab_settings_img);

        id_tab_new_order_text = (TextView) findViewById(R.id.id_tab_new_order_text);
        id_tab_executing_text = (TextView) findViewById(R.id.id_tab_executing_text);
        id_tab_completed_text = (TextView) findViewById(R.id.id_tab_completed_text);
        id_tab_settings_text = (TextView) findViewById(R.id.id_tab_settings_text);

        tv_title = (TextView) findViewById(R.id.tv_title);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setVisibility(View.GONE);

        tv_other = (TextView) findViewById(R.id.tv_other);
        tv_other.setVisibility(View.GONE);
    }

    private void initEvents() {
        id_tab_new_order.setOnClickListener(this);
        id_tab_executing.setOnClickListener(this);
        id_tab_completed.setOnClickListener(this);
        id_tab_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.id_tab_new_order:
                setSelect(0);
                break;
            case R.id.id_tab_executing:
                setSelect(1);
                break;
            case R.id.id_tab_completed:
                setSelect(2);
                break;
            case R.id.id_tab_settings:
                setSelect(3);
                break;

        }
    }

    /**
     * 将所有的图片和文字切换为未选中的
     */
    private void resetImgs() {
        id_tab_new_order_img.setEnabled(false);
        id_tab_executing_img.setEnabled(false);
        id_tab_completed_img.setEnabled(false);
        id_tab_settings_img.setEnabled(false);

        id_tab_new_order_text.setTextColor(getResources().getColor(R.color.colorTabText));
        id_tab_executing_text.setTextColor(getResources().getColor(R.color.colorTabText));
        id_tab_completed_text.setTextColor(getResources().getColor(R.color.colorTabText));
        id_tab_settings_text.setTextColor(getResources().getColor(R.color.colorTabText));
    }

    private void setSelect(int i) {
        resetImgs();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //先全部隐藏
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i) {
            case 0:
                if (mTab01 == null) {
                    mTab01 = new NewOrderFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else {
                    transaction.show(mTab01);
                }
                tv_title.setText(R.string.tab_new_order);
                id_tab_new_order_img.setEnabled(true);
                id_tab_new_order_text.setTextColor(getResources().getColor(R.color.colorTabText_enable));


                TotalItemCountProtocol totalItemCountProtocol = new TotalItemCountProtocol();
                totalItemCountProtocol.getDataFromService(userID, new TotalItemCountProtocol.TotalItemCountCallBack() {
                    @Override
                    public void totalItemResult(int totalItemCount) {
                        MainActivity.this.setBadgeCount(totalItemCount);
                    }
                });



                break;
            case 1:
                if (mTab02 == null) {
                    mTab02 = new ExecutingFragment();
                    transaction.add(R.id.id_content, mTab02);
                } else {
                    transaction.show(mTab02);

                }
                tv_title.setText(R.string.tab_executing);
                id_tab_executing_img.setEnabled(true);
                id_tab_executing_text.setTextColor(getResources().getColor(R.color.colorTabText_enable));

                break;
            case 2:
                if (mTab03 == null) {
                    mTab03 = new CompletedFragment();
                    transaction.add(R.id.id_content, mTab03);
                } else {
                    transaction.show(mTab03);
                }
                tv_title.setText(R.string.tab_completed);
                id_tab_completed_img.setEnabled(true);
                id_tab_completed_text.setTextColor(getResources().getColor(R.color.colorTabText_enable));

                break;
            case 3:
                if (mTab04 == null) {
                    mTab04 = new SettingFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                tv_title.setText(R.string.tab_setting);
                id_tab_settings_img.setEnabled(true);
                id_tab_settings_text.setTextColor(getResources().getColor(R.color.colorTabText_enable));

                break;

        }

        transaction.commit();
    }


    /**
     * 隐藏所有 Fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }
    }

    /**
     * 设置右上角新订单提醒
     * @param badgeCount
     */
    public void setBadgeCount(final int badgeCount){

        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                BadgeView badgeView = new BadgeView(MainActivity.this);
                badgeView.setBadgeCount(badgeCount);
                badgeView.setTargetView(id_tab_new_order_img);
            }
        });


    }

}
