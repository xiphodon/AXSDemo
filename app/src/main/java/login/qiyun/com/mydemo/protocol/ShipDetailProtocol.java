package login.qiyun.com.mydemo.protocol;

import android.util.Log;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.ShipDetail;
import login.qiyun.com.mydemo.bean.Node;


/**
 * Created by Shipxy on 2016/7/7.
 */
public class ShipDetailProtocol extends BaseProtocol<ShipDetail> {

    private ShipDetail shipDetail;

    public interface ShowCallBack{
        void showResult(ShipDetail shipDetail);
    }

    /**
     * 从服务器获取数据
     * //http://axs.ikuaichuan.com:1098/MyShips/ShipDetail?mmsi=413444250
     */
    public void getDataFromService(String mmsi, final ShowCallBack showCallBack){
        //连接网路，解析json
        String url = Utils.URL + Utils.URL_MYSHIPS_SHIPDETAIL + "?mmsi=" + mmsi;
        Log.d("ShipDetailProtocol_url" , url);
        Utils.getHttp().send(HttpRequest.HttpMethod.GET,url , new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("ShipDetailProtocol",responseInfo.result);

                ShipDetail shipDetail = parseData(responseInfo.result);
                if(showCallBack != null){
                    showCallBack.showResult(shipDetail);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("ShipDetailProtocol","网络异常");
            }
        });
    }

    @Override
    public ShipDetail parseData(String result) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(result);

            boolean success = jo.getBoolean("success");
            if (!success){
                return null;
            }

            shipDetail = new ShipDetail();
            shipDetail.callsign = jo.getString("callsign");
            shipDetail.mmsi = jo.getString("mmsi");
            shipDetail.shipname = jo.getString("shipname");
            shipDetail.imo = jo.getString("imo");
            shipDetail.shiptype = jo.getString("shiptype");
            shipDetail.length = jo.getString("length");
            shipDetail.breadth = jo.getString("breadth");
            shipDetail.eta = jo.getString("eta");
            shipDetail.dest_port = jo.getString("dest_port");
            shipDetail.draught = jo.getString("draught");
            shipDetail.postime = jo.getString("postime");
            shipDetail.longitude = jo.getString("longitude");
            shipDetail.latitude = jo.getString("latitude");
            shipDetail.course = jo.getString("course");
            shipDetail.heading = jo.getString("heading");
            shipDetail.speed = jo.getString("speed");
            shipDetail.navStatus = jo.getString("navStatus");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return shipDetail;
    }
}
