package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.LoginInfo;
import login.qiyun.com.mydemo.protocol.LoginProtocol;


public class LogoutActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private String name;
    private String pwd;
    private ProgressBar pb_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        initView();
        initLogin();

    }

    private void initView() {
        pb_pro = (ProgressBar) findViewById(R.id.pb_pro);
        pb_pro.setVisibility(View.VISIBLE);
    }


    /**
     * 初始化登录
     */
    private void initLogin() {
        sp = getSharedPreferences("user", MODE_PRIVATE);

        name = sp.getString("name", "");
        pwd = sp.getString("pwd", "");

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }

        LoginProtocol loginProtocol = new LoginProtocol();
        loginProtocol.getDataFromService(name, pwd, new LoginProtocol.LoginCallBack() {

            @Override
            public void loginResult(LoginInfo loginInfo) {

            }
        });
    }

    public void loginResult(String state){
        if (state.equals(Utils.STATE_SUCCESS)){
            pb_pro.setVisibility(View.GONE);
        }else if(state.equals(Utils.STATE_FAIL)){
            Toast.makeText(this,"请重新登录",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }

    /**
     * 注销按钮
     * @param view
     */
    public void out(View view) {

        sp.edit().putString("pwd","").commit();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
