package login.qiyun.com.mydemo.fragment.NewOrderFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.activity.MainActivity;
import login.qiyun.com.mydemo.activity.OrderDetailActivity;
import login.qiyun.com.mydemo.adapter.OrderListAdapter;
import login.qiyun.com.mydemo.application.MyApplication;
import login.qiyun.com.mydemo.bean.OrderList01;
import login.qiyun.com.mydemo.protocol.NewOrderProtocol;
import login.qiyun.com.mydemo.view.RefreshListView;

/**
 * Created by Shipxy on 2016/7/11.
 */
public abstract class ViewPager_BaseFragment extends Fragment {

    private View view;
    private RefreshListView lv_new_order_list01;
    private SharedPreferences sp;
    private ArrayList<OrderList01.OrderMsg> pageList = new ArrayList<OrderList01.OrderMsg>(){};
    private OrderListAdapter orderListAdapter;
    private NewOrderProtocol newOrderProtocol;
    private int userID;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            orderListAdapter.notifyDataSetChanged();
            lv_new_order_list01.completeRefresh();
        }
    };
    private OrderList01 orderList;
    private ArrayList<String> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_order_fragment01,container,false);

        lv_new_order_list01 = (RefreshListView) view.findViewById(R.id.lv_new_order_list01);

        lv_new_order_list01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("OrderID",pageList.get(position-1).OrderID);
                intent.putExtra("OrderSignID",pageList.get(position-1).OrderSignID);
                intent.setClass(Utils.getContext(), OrderDetailActivity.class);
                Log.d("onItemClick",pageList.get(position-1).OrderID+","+pageList.get(position-1).OrderSignID);
                startActivity(intent);
            }
        });

        initData();

        return view;
    }


    private void initData() {

        orderListAdapter = new OrderListAdapter(pageList);
        lv_new_order_list01.setAdapter(orderListAdapter);

        newOrderProtocol = new NewOrderProtocol();
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        userID = sp.getInt("UserID", -1);

        requestDataFromServer(false);

        lv_new_order_list01.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onPullRefresh() {
                requestDataFromServer(false);
            }

            @Override
            public void onLoadingMore() {
                requestDataFromServer(true);
            }
        });

    }

    private void requestDataFromServer(final boolean isLoadingMore){
        if(isLoadingMore){


        }else{
            //刷新数据
            arrayList = new ArrayList<>();
            arrayList = parameter();
            //http://axs.ikuaichuan.com:1092/Order/GetOrderList?SignType=1&UserID=1040&ShipName=&Status=1&page=1&pageSize=10
            newOrderProtocol.getDataFromService(arrayList.get(0), userID+"", arrayList.get(1), arrayList.get(2), arrayList.get(3),arrayList.get(4),  new NewOrderProtocol.ShowCallBack() {

                @Override
                public void ShowResult(OrderList01 orderList01) {

                    if(orderList01 != null){
                        Log.d("NewOrderFragmentList01",orderList01.toString());
                        orderList = orderList01;
                        pageList = orderList.PageList;

                        orderListAdapter.setPageList(pageList);
                    }else{
                        Toast.makeText(Utils.getContext(),"网络错误",Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }

        handler.sendEmptyMessage(0);
    }

    public abstract ArrayList<String>  parameter();


}
