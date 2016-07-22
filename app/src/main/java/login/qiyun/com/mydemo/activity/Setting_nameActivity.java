package login.qiyun.com.mydemo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import login.qiyun.com.mydemo.R;

public class Setting_nameActivity extends BaseActivity {

    @Override
    protected void initUI() {

    }

    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_setting_name;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText("修改姓名");
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setVisibility(View.GONE);
    }

}
