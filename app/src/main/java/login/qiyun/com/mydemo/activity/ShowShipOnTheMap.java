package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.OrderDetail;
import login.qiyun.com.mydemo.bean.ShipDetail;
import login.qiyun.com.mydemo.protocol.ShipDetailProtocol;

public class ShowShipOnTheMap extends BaseActivity {


    private View pop;
    private String mmsi;
    private String shipname;
    private MapView mMapView = null;
    private BaiduMap baiduMap;
    /** 天安门坐标 */
    private LatLng tamPos = new LatLng(39.915112,116.403963);

    private ShipDetail shipDetail;
    private LatLng shipPos;

    private TextView tv_detail;
    private TextView tv_postime;
    private TextView tv_shiptype;
    private TextView tv_speed;
    private TextView tv_course;
    private TextView tv_pop_shipname;

    @Override
    protected void initUI() {
        Intent intent = getIntent();
        mmsi = intent.getStringExtra("mmsi");
        shipname = intent.getStringExtra("shipname");

        Log.d("ShowShipOnTheMap",mmsi + "," + shipname);

        //获取地图控件引用
        mMapView = (MapView) getView().findViewById(R.id.bmapView);


        //http://axs.ikuaichuan.com:1098/MyShips/ShipDetail?mmsi=413444250
        ShipDetailProtocol shipDetailProtocol = new ShipDetailProtocol();
        shipDetailProtocol.getDataFromService(mmsi, new ShipDetailProtocol.ShowCallBack() {
            @Override
            public void showResult(ShipDetail shipDetail) {

                if(shipDetail != null){
                    ShowShipOnTheMap.this.shipDetail = shipDetail;

                    showMap();
                }else{
                    baiduMap = mMapView.getMap();
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(tamPos);
                    baiduMap.setMapStatus(mapStatusUpdate);
                    mapStatusUpdate = MapStatusUpdateFactory.zoomTo(5);
                    baiduMap.setMapStatus(mapStatusUpdate);

                    Toast.makeText(Utils.getContext(),"查询失败",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void showMap() {
        // 1.	隐藏缩放按钮、比例尺
        // mapView.showScaleControl(false);	// 隐藏比例按钮，默认是显示的
        // mapView.showZoomControls(false);	// 隐藏缩放按钮，默认是显示的

        // 获取地图控制器
        baiduMap = mMapView.getMap();

        // 2.   获取最小缩放级别（3.0）、最大缩放级别（20.0）
        float maxZoomLevel = baiduMap.getMaxZoomLevel(); // 获取地图最大缩放级别
        float minZoomLevel = baiduMap.getMinZoomLevel(); // 获取地图最小缩放级别
        Log.i("ShowShipOnTheMap", "minZoomLevel = " + minZoomLevel + ", maxZoomLevel" + maxZoomLevel);

        // 3.	设置地图中心点为船坐标(维度，经度)
        shipPos = new LatLng(Double.parseDouble(shipDetail.latitude),Double.parseDouble(shipDetail.longitude));
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(shipPos);
        baiduMap.setMapStatus(mapStatusUpdate);

        // 4.	设置地图缩放
        mapStatusUpdate = MapStatusUpdateFactory.zoomTo(5);
        baiduMap.setMapStatus(mapStatusUpdate);

        // 6.	获取地图Ui控制器：隐藏指南针
        // UiSettings uiSettings = baiduMap.getUiSettings();
        // uiSettings.setCompassEnabled(false);	//  不显示指南针

        initMarker();
        showPop();
//        baiduMap.setOnMarkerClickListener(mOnMarkerClickListener);
    }

    private void showPop() {
        // 显示一个泡泡
        if (pop == null) {
            pop = View.inflate(Utils.getContext(), R.layout.pop, null);

            tv_pop_shipname = (TextView) pop.findViewById(R.id.tv_pop_shipname);
            tv_course = (TextView) pop.findViewById(R.id.tv_course);
            tv_speed = (TextView) pop.findViewById(R.id.tv_speed);
            tv_shiptype = (TextView) pop.findViewById(R.id.tv_shiptype);
            tv_postime = (TextView) pop.findViewById(R.id.tv_postime);
            tv_detail = (TextView) pop.findViewById(R.id.tv_detail);

            mMapView.addView(pop, createLayoutParams(shipPos));
        } else {
            mMapView.updateViewLayout(pop, createLayoutParams(shipPos));
        }
        tv_pop_shipname.setText(shipDetail.shipname + "/" + shipDetail.mmsi);
        tv_course.setText(shipDetail.course);
        tv_speed.setText(shipDetail.speed);
        tv_shiptype.setText(shipDetail.shiptype);
        tv_postime.setText(shipDetail.postime);
        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页
                Toast.makeText(Utils.getContext(),"跳转详情页",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMarker() {
        MarkerOptions options = new MarkerOptions();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.course);
        options.position(shipPos)		// 位置
                .title("航迹向")		// title
                .icon(icon)			// 图标
                .draggable(false)	// 设置图标可以拖动
                .rotate((float) (360 - Double.parseDouble(shipDetail.course)));          // 旋转

        baiduMap.addOverlay(options);
    }

//    BaiduMap.OnMarkerClickListener mOnMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
//
//
//
//        @Override
//        public boolean onMarkerClick(Marker marker) {
//
//            return true;
//        }
//    };

    /**
     * 创建一个布局参数
     * @param position
     * @return
     */
    private MapViewLayoutParams createLayoutParams(LatLng position) {
        MapViewLayoutParams.Builder buidler = new MapViewLayoutParams.Builder();
        buidler.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode);	// 指定坐标类型为经纬度
        buidler.position(position);		// 设置标志的位置
        buidler.yOffset(-Utils.dip2px(15));			// 设置View往上偏移
        MapViewLayoutParams params = buidler.build();
        return params;
    }

    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_show_ship_on_the_map;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText(shipname);
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
