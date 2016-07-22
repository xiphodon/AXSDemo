package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.LoginInfo;
import login.qiyun.com.mydemo.protocol.LoginProtocol;


public class LoginActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pwd;
    private String name;
    private String pwd;
    private LinearLayout ll_progress;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        sp = getSharedPreferences("user", MODE_PRIVATE);

        et_name.setText(sp.getString("name", ""));
    }


    /**
     * 初始化UI
     */
    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);

    }

    /**
     * 登录按钮
     * @param view
     */
    public void login(View view) {

        //获取用户名密码
        name = et_name.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();

        //记住用户名密码
        sp.edit().putString("name", name).commit();


        //用户名密码检查
        if (!checkLogin()) {
            return;
        }

        //显示进度条
        ll_progress.setVisibility(View.VISIBLE);

        LoginProtocol loginProtocol = new LoginProtocol();
        loginProtocol.getDataFromService(name, pwd, new LoginProtocol.LoginCallBack() {

            @Override
            public void loginResult(LoginInfo loginInfo) {
                LoginActivity.this.loginResult(loginInfo);
            }
        });
    }

    private void save(LoginInfo.UserInfo userInfo) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("UserID",userInfo.UserID);
        edit.putString("RealName",userInfo.RealName);
        edit.putString("CellPhone",userInfo.CellPhone);
        edit.commit();

        Log.d("tag",userInfo.toString());
    }

//    /**
//     * 从服务器获取数据
//     */
//    private void getDataFromService(String name ,String pwd){
//        //连接网路，解析json
//        final LoginProtocol loginProtocol = new LoginProtocol();
//        Utils.getHttp().send(HttpRequest.HttpMethod.GET, Utils.URL + "?name=" + name + "&pwd=" + pwd, new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                String state = loginProtocol.parseData(responseInfo.result);
//                loginResult(state);
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Log.d("Login","网络异常");
//            }
//        });
//    }

    /**
     * 检查登录是否合法
     * @return
     */
    private boolean checkLogin() {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "用户名或密码不能为空！！！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 登录结果
     * @param
     */
    private void loginResult(final LoginInfo loginInfo){

        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (loginInfo.success == Utils.STATE_SUCCESS) {
                    ll_progress.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    save(loginInfo.userInfo);

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                } else if (loginInfo.success == Utils.STATE_FAIL) {
                    ll_progress.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "账号或密码错误，请重新登录", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
