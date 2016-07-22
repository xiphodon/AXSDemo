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
import java.util.List;

import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.Node;


/**
 * Created by Shipxy on 2016/7/7.
 */
public class ChooseNodeProtocol extends BaseProtocol<List<Node>> {

    private ArrayList<Node> list;

    public interface ShowCallBack{
        void showResult(List<Node> list);
    }

    /**
     * 从服务器获取数据
     * //http://axs.ikuaichuan.com:1098/Order/GetOrderSignNode?OrderSignID=9
     */
    public void getDataFromService(String orderSignID, final ShowCallBack showCallBack){
        //连接网路，解析json
        String url = Utils.URL + Utils.URL_ORDER_GET_ORDER_SIGN_NODE + "?OrderSignID=" + orderSignID;
        Log.d("ChooseNodeProtocol_url" , url);
        Utils.getHttp().send(HttpRequest.HttpMethod.GET,url , new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("ChooseNodeProtocol",responseInfo.result);

                List list = parseData(responseInfo.result);
                if(showCallBack != null){
                    showCallBack.showResult(list);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("ChooseNodeProtocol","网络异常");
            }
        });
    }

    @Override
    public List<Node> parseData(String result) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(result);

            boolean success = jo.getBoolean("success");

            if (!success){
                return null;
            }

            list = new ArrayList<>();

            JSONArray ja = jo.getJSONArray("Nodes");

            for (int i = 0; i<ja.length() ; i++){
                JSONObject jo1 = ja.getJSONObject(i);
                Node node = new Node();
                node.NodeID = jo1.getInt("NodeID");
                node.NodeName = jo1.getString("NodeName");
                node.NodeItemEdition = jo1.getInt("NodeItemEdition");
                node.IsDone = jo1.getBoolean("IsDone");

                list.add(node);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("ChooseNodeProtocol_list",list.toString());

        return list;
    }
}
