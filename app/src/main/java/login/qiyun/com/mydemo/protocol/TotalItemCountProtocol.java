package login.qiyun.com.mydemo.protocol;

import android.util.Log;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import login.qiyun.com.mydemo.Utils.Utils;


/**
 * Created by Administrator on 2016/7/12.
 */
public class TotalItemCountProtocol extends BaseProtocol<Integer> {
    private int totalItemCount;
    private JSONObject jo;

    public interface TotalItemCountCallBack {
        void totalItemResult(int totalItemCount);
    }

    /**
     * 从服务器获取数据
     * http://axs.ikuaichuan.com:1092/Order/GetNewOrderCount?UserID=1040
     */
    public void getDataFromService(int UserID, final TotalItemCountCallBack totalItemCountCallBack){
        //连接网路，解析json
        Utils.getHttp().send(HttpRequest.HttpMethod.GET, Utils.URL + Utils.URL_ORDER_COUNT + "?UserID=" + UserID , new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("totalItemCount",responseInfo.result);

                totalItemCount = parseData(responseInfo.result);

                if (totalItemCount == -1){
                    Log.d("totalItemCount","请求失败");
                }else {
                    if(totalItemCountCallBack != null){
                        totalItemCountCallBack.totalItemResult(totalItemCount);
                    }
                }


            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("totalItemCount","网络异常");
            }
        });
    }
    @Override
    public Integer parseData(String result) {

        try {
            jo = new JSONObject(result);
            boolean success = jo.getBoolean("success");
            if (!success){
                return -1;
            }

            totalItemCount = jo.getInt("TotalItemCount");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return totalItemCount;
    }
}
