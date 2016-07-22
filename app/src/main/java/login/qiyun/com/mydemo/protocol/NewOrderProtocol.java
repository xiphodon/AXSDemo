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
import login.qiyun.com.mydemo.bean.OrderList01;

/**
 * Created by Shipxy on 2016/7/11.
 */
public class NewOrderProtocol extends BaseProtocol<OrderList01> {
    private OrderList01 orderList01;

    public interface ShowCallBack {
        void ShowResult(OrderList01 orderList01);
    }

    /**
     * 从服务器获取数据
     * SignType=1&UserID=1040&ShipName=&Status=1&page=1&pageSize=10
     */
    public void getDataFromService(String SignType, String UserID, String ShipName, String Status, String page, String pageSize, final ShowCallBack showCallBack) {
        String url = Utils.URL + Utils.URL_ORDER + "?SignType=" + SignType + "&UserID=" + UserID + "&ShipName=" + ShipName + "&Status=" + Status + "&page=" + page + "&pageSize=" + pageSize;
        Log.e("url",url);
        //连接网路，解析json
        Utils.getHttp().send(HttpRequest.HttpMethod.GET,url , new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("NewOrderProtocolJson", responseInfo.result);

                OrderList01 orderList01 = parseData(responseInfo.result);
                if (showCallBack != null) {
                    showCallBack.ShowResult(orderList01);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("NewOrderProtocol", "网络异常");
            }
        });
    }

    @Override
    public OrderList01 parseData(String result) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            orderList01 = new OrderList01();
            orderList01.success = jsonObject.getBoolean("success");
            if(!orderList01.success){
                return null;
            }

            orderList01.PageNumber = jsonObject.getInt("PageNumber");
            orderList01.PageSize = jsonObject.getInt("PageSize");
            orderList01.TotalCount = jsonObject.getInt("TotalCount");
            JSONArray jsonArray = jsonObject.getJSONArray("PageList");

            orderList01.PageList = new ArrayList<OrderList01.OrderMsg>();

            for (int i = 0; i< jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                OrderList01.OrderMsg orderMsg = new OrderList01.OrderMsg();

                orderMsg.OrderSignID = jsonObject1.getInt("OrderSignID");
                orderMsg.OrderHID = jsonObject1.getInt("OrderHID");
                orderMsg.OrderID = jsonObject1.getInt("OrderID");
                orderMsg.MaterialCategory = jsonObject1.getString("MaterialCategory");
                orderMsg.ShipName = jsonObject1.getString("ShipName");
                orderMsg.FromPort = jsonObject1.getString("FromPort");
                orderMsg.ToPort = jsonObject1.getString("ToPort");
                orderMsg.Total = jsonObject1.getInt("Total");
                orderMsg.LoadTime = jsonObject1.getString("LoadTime");
                orderMsg.LoadTimeRangeDay = jsonObject1.getInt("LoadTimeRangeDay");
                orderMsg.ContractNum = jsonObject1.getString("ContractNum");
                orderMsg.IsNew = jsonObject1.getBoolean("IsNew");
                orderMsg.Status = jsonObject1.getInt("Status");
                orderMsg.Stage = jsonObject1.getString("Stage");


                orderList01.PageList.add(orderMsg);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderList01;
    }
}
