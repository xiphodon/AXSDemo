package login.qiyun.com.mydemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import login.qiyun.com.mydemo.R;

public class Setting_ChangePwdActivity extends BaseActivity {


    private LinearLayout view;

    @Override
    protected void initUI() {

    }

    @Override
    public int setContentViewLayoutID() {

        return R.layout.activity_setting__change_pwd;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText("修改密码");
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setVisibility(View.GONE);
    }
}
