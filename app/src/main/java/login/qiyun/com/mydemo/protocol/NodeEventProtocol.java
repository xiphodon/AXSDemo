package login.qiyun.com.mydemo.protocol;

import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.NodeEvent;


/**
 * Created by Shipxy on 2016/7/7.
 */
public class NodeEventProtocol extends BaseProtocol<NodeEvent> {

    private NodeEvent nodeEvent;

    public interface ShowCallBack{
        void showResult(NodeEvent nodeEvent);
    }

    /**
     * 从服务器获取数据
     * http://axs.ikuaichuan.com:1092/Order/GetOrderSignNodeItem?NodeID=102
     */
    public void getDataFromService(String nodeID, final ShowCallBack showCallBack){
        //连接网路，解析json
        String url = Utils.URL + Utils.URL_ORDER_GET_ORDER_SIGN_NODE_ITEM + "?NodeID=" + nodeID;
        Log.d("NodeEventProtocol_url" , url);
        Utils.getHttp().send(HttpRequest.HttpMethod.GET,url , new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("NodeEventProtocol",responseInfo.result);

                NodeEvent nodeEvent = parseData(responseInfo.result);
                if(showCallBack != null){
                    showCallBack.showResult(nodeEvent);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("NodeEventProtocol","网络异常");
            }
        });
    }

    @Override
    public NodeEvent parseData(String result) {

        Gson gson = new Gson();
        NodeEvent nodeEvent = gson.fromJson(result, NodeEvent.class);

        Log.d("ChooseNodeProtocol_Node", nodeEvent.toString());

        return nodeEvent;
    }
}
