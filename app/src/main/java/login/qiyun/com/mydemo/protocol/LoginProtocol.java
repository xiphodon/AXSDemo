package login.qiyun.com.mydemo.protocol;

import android.util.Log;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.LoginInfo;


/**
 * Created by Shipxy on 2016/7/7.
 */
public class LoginProtocol extends BaseProtocol<LoginInfo> {

    private LoginInfo loginInfo;

    public interface LoginCallBack{
        void loginResult(LoginInfo loginInfo);
    }

    /**
     * 从服务器获取数据
     */
    public void getDataFromService(String name , String pwd, final LoginCallBack loginCallBack){
        //连接网路，解析json
        Utils.getHttp().send(HttpRequest.HttpMethod.GET, Utils.URL + Utils.URL_LOGIN + "?cellphone=" + name + "&password=" + pwd, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("login",responseInfo.result);

                LoginInfo loginInfo = parseData(responseInfo.result);
                if(loginCallBack != null){
                    loginCallBack.loginResult(loginInfo);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("Login","网络异常");
            }
        });
    }

    @Override
    public LoginInfo parseData(String result) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            loginInfo = new LoginInfo();
            loginInfo.success = jsonObject.getBoolean("success");

            LoginInfo.UserInfo userInfo = new LoginInfo.UserInfo();

            JSONObject jo1 = jsonObject.getJSONObject("UserInfo");

            userInfo.UserID = jo1.getInt("UserID");
            userInfo.RealName = jo1.getString("RealName");
            userInfo.CellPhone = jo1.getString("CellPhone");

            loginInfo.userInfo = userInfo;



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }
}
