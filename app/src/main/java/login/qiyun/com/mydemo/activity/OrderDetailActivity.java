package login.qiyun.com.mydemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.adapter.OrderDetailListAdapter;
import login.qiyun.com.mydemo.bean.OrderDetail;
import login.qiyun.com.mydemo.protocol.NewOrderDetailProtocol;
import login.qiyun.com.mydemo.view.RefreshListView;

public class OrderDetailActivity extends BaseActivity {

    private OrderDetail orderDetail;
    private View view;
    private ListView rlv_order_detail;
    private List list;
    private int orderID;
    private int orderSignID;

    @Override
    protected void initUI() {
        Intent intent = getIntent();

        orderID = intent.getIntExtra("OrderID",-1);
        orderSignID = intent.getIntExtra("OrderSignID",-1);

        Log.d("OrderDetailActivity", orderID + "," + orderSignID);

        initData();

    }

    private void initData() {
        NewOrderDetailProtocol newOrderDetailProtocol = new NewOrderDetailProtocol();
        newOrderDetailProtocol.getDataFromService(orderSignID, orderID, new NewOrderDetailProtocol.ShowCallBack() {

            @Override
            public void ShowResult(OrderDetail orderDetail) {

                if(orderDetail != null){
                    Log.d("OrderDetailActivity",orderDetail.toString());
                    OrderDetailActivity.this.orderDetail = orderDetail;

                    list = orderDetailToOrderDetailItemList(orderDetail);

                    showListView();
                }else{
                    Toast.makeText(Utils.getContext(),"网络错误",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private OrderDetailListAdapter orderDetailListAdapter;

    private void showListView() {

        view = getView();
        rlv_order_detail = (ListView) view.findViewById(R.id.rlv_order_detail);

        View foot = View.inflate(Utils.getContext(), R.layout.order_detail_foot, null);
        rlv_order_detail.addFooterView(foot);

        initFootButtonClick(foot);

        orderDetailListAdapter = new OrderDetailListAdapter(list);
        rlv_order_detail.setAdapter(orderDetailListAdapter);

        initListViewClick();
    }

    private void initFootButtonClick(View foot) {
        Button btn_look_contract = (Button) foot.findViewById(R.id.btn_look_contract);
        //查看合同
        btn_look_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(orderDetail.PicUrl)){
                    Intent intent = new Intent();
                    intent.setClass(OrderDetailActivity.this, LookContractActivity.class);
                    intent.putExtra("PicUrl",orderDetail.PicUrl);
                    startActivity(intent);
                }else {
                    Toast.makeText(OrderDetailActivity.this,"暂无合同详情",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button btn_look_event = (Button) foot.findViewById(R.id.btn_look_event);
        //查看事件
        btn_look_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http://axs.ikuaichuan.com:1098/Order/Node?OrderSignID=10
                //http://axs.ikuaichuan.com:1098/Order/Incident?OrderSignID=10
                //http://axs.ikuaichuan.com:1098/Order/Alarm?OrderSignID=10
                Intent intent = new Intent();
                intent.setClass(OrderDetailActivity.this,LookEventActivity.class);
                intent.putExtra("orderSignID",orderSignID + "");
//                intent.putExtra("orderSignID",2055 + "");
                Log.d("OrderDetailActivity",orderSignID + "--------------------");
                startActivity(intent);
            }
        });

        ImageView iv_exception = (ImageView) foot.findViewById(R.id.iv_exception);
        //异常提报
        iv_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderDetailActivity.this,"异常提报",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListViewClick() {
        rlv_order_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == list.size() -2 ){
                    //船方电话
                    if(!TextUtils.isEmpty(orderDetail.ShipPhone)){
                        callPhone(orderDetail.ShipPhone);
                    }
                }else if(position == list.size() - 1){
                    //收货方电话
                    if(!TextUtils.isEmpty(orderDetail.MaterialPhone)){
                        callPhone(orderDetail.MaterialPhone);
                    }
                }
            }
        });
    }

    private void callPhone(final String shipPhone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
        builder.setTitle("呼叫：" + shipPhone);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //打电话
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + shipPhone.trim()));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


    private List orderDetailToOrderDetailItemList(OrderDetail orderDetail) {
        ArrayList list = new ArrayList<>();

        list.add("合同号" + "$#$" + orderDetail.ContractNum);
        list.add("船名" + "$#$" + orderDetail.ShipName);
        list.add("货品" + "$#$" + orderDetail.MaterialCategory);
        list.add("航线" + "$#$" + orderDetail.FromPort + "→" + orderDetail.ToPort);
        list.add("签约货量(吨)" + "$#$" + orderDetail.Total);
        list.add("装货时间" + "$#$" + orderDetail.LoadTime + "±" + orderDetail.LoadTimeRangeDay + "天");
        list.add("合理损耗(‰)" + "$#$" + orderDetail.Loss);
        list.add("两港作业时间" + "$#$" + orderDetail.BetweenTime + "小时");
        list.add("封样数量(个)" + "$#$" + orderDetail.SealedAmount);
        list.add("含水(‰)" + "$#$" + orderDetail.Water);
        list.add("交接方式" + "$#$" + orderDetail.ReceiveType);
        list.add("备注" + "$#$" + orderDetail.Remark);
        list.add("船方" + "$#$" + orderDetail.ShipCompany);
        list.add("收货方" + "$#$" + orderDetail.MaterialCompany);

//        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        map.put("合同号",orderDetail.ContractNum);
//        map.put("船名",orderDetail.ShipName);
//        map.put("货品",orderDetail.MaterialCategory);
//        map.put("航线",orderDetail.FromPort + "→" + orderDetail.ToPort);
//        map.put("签约货量(吨)",orderDetail.Total + "");
//        map.put("装货时间",orderDetail.LoadTime + "±" + orderDetail.LoadTimeRangeDay + "天");
//        map.put("合理损耗(‰)",orderDetail.Loss + "");
//        map.put("两港作业时间",orderDetail.BetweenTime + "小时");
//        map.put("封样数量(个)",orderDetail.SealedAmount + "");
//        map.put("含水(‰)",orderDetail.Water + "");
//        map.put("交接方式",orderDetail.ReceiveType);
//        map.put("备注",orderDetail.Remark);
//        map.put("船方",orderDetail.ShipCompany);
//        map.put("收货方",orderDetail.MaterialCompany);
//        return map;
        return list;
    }

    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText("现场执行");
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setText("船位查询");
        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("chooseShip",orderDetail.toString());

                //百度地图
                // http://axs.ikuaichuan.com:1098/MyShips/ShipDetail?mmsi=413444250
                if(TextUtils.isEmpty(orderDetail.MMSI)||"null".equals(orderDetail.MMSI)){
                    Toast.makeText(OrderDetailActivity.this,"该船不存在",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(OrderDetailActivity.this, ShowShipOnTheMap.class);
                    intent.putExtra("mmsi",orderDetail.MMSI);
                    intent.putExtra("shipname",orderDetail.ShipName);
                    startActivity(intent);
                }


            }
        });
    }
}
