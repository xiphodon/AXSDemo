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
import login.qiyun.com.mydemo.bean.OrderDetail;
import login.qiyun.com.mydemo.bean.OrderList01;

/**
 * Created by Shipxy on 2016/7/11.
 */
public class NewOrderDetailProtocol extends BaseProtocol<OrderDetail> {
    private OrderDetail orderDetail;

    public interface ShowCallBack {
        void ShowResult(OrderDetail orderDetail);
    }

    /**
     * 从服务器获取数据
     * http://axs.ikuaichuan.com:1098/Order/OrderDetail?OrderSignID=7&OrderID=3
     */
    public void getDataFromService(int OrderSignID, int OrderID, final ShowCallBack showCallBack) {
        String url = Utils.URL + Utils.URL_ORDER_DETAIL + "?OrderSignID=" + OrderSignID + "&OrderID=" + OrderID;
        Log.e("url",url);
        //连接网路，解析json
        Utils.getHttp().send(HttpRequest.HttpMethod.GET,url , new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("NewOrderDetailProtocol", responseInfo.result);

                OrderDetail orderDetail = parseData(responseInfo.result);
                if (showCallBack != null) {
                    showCallBack.ShowResult(orderDetail);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("NewOrderDetailProtocol", "网络异常");
            }
        });
    }

    @Override
    public OrderDetail parseData(String result) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(result);

            orderDetail = new OrderDetail();

            orderDetail.success = jo.getBoolean("success");
            if(!orderDetail.success){
                return null;
            }

            orderDetail.OrderHID = jo.getInt("OrderHID");
            orderDetail.OrderID = jo.getInt("OrderID");
            orderDetail.ContractNum = jo.getString("ContractNum");
            orderDetail.ReceiveType = jo.getString("ReceiveType");
            orderDetail.MaterialCategory = jo.getString("MaterialCategory");
            orderDetail.FromPort = jo.getString("FromPort");
            orderDetail.ToPort = jo.getString("ToPort");
            orderDetail.Total = jo.getInt("Total");
            orderDetail.LoadTime = jo.getString("LoadTime");
            orderDetail.LoadTimeRangeDay = jo.getInt("LoadTimeRangeDay");
            orderDetail.Loss = jo.getInt("Loss");
            orderDetail.BetweenTime = jo.getInt("BetweenTime");
            orderDetail.SealedAmount = jo.getInt("SealedAmount");
            orderDetail.ShipCompany = jo.getString("ShipCompany");
            orderDetail.ShipPhone = jo.getString("ShipPhone");
            orderDetail.ShipMan = jo.getString("ShipMan");
            orderDetail.MaterialCompany = jo.getString("MaterialCompany");
            orderDetail.MaterialPhone = jo.getString("MaterialPhone");
            orderDetail.MaterialMan = jo.getString("MaterialMan");
            orderDetail.MMSI = jo.getString("MMSI");
            orderDetail.Water = jo.getInt("Water");
            orderDetail.ShipName = jo.getString("ShipName");
            orderDetail.Remark = jo.getString("Remark");
            orderDetail.PicUrl = jo.getString("PicUrl");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }
}
