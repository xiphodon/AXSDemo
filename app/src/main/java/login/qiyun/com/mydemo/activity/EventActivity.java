package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidong.photopicker.ImageCaptureManager;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.squareup.okhttp.Request;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.OkHttpUtils;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.adapter.NodeEventListAdapter;
import login.qiyun.com.mydemo.bean.NodeEvent;
import login.qiyun.com.mydemo.protocol.NodeEventProtocol;

public class EventActivity extends BaseActivity {


    private String nodeName;
    private String nodeID;
    private View view;
    private ListView lv_event;

    private GridAdapter gridAdapter;

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类

    private GridView gridView;
    private EditText et_remark;
    private ImageView iv_camera;
    private ImageView iv_alarm;
    private ImageView iv_voice;
    private File file;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Log.i("response", String.valueOf(msg.obj));
            if(msg.obj.equals("SUCCESS")){
                Toast.makeText(EventActivity.this, "上传成功。", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    private String orderSignID;

    @Override
    protected void initUI() {
        Intent intent = getIntent();
        nodeID = intent.getStringExtra("NodeID");
        nodeName = intent.getStringExtra("NodeName");
        orderSignID = intent.getStringExtra("orderSignID");

        view = getView();
        lv_event = (ListView) view.findViewById(R.id.lv_event);

        NodeEventProtocol nodeEventProtocol = new NodeEventProtocol();
        nodeEventProtocol.getDataFromService(nodeID, new NodeEventProtocol.ShowCallBack() {
            @Override
            public void showResult(NodeEvent nodeEvent) {
                if (nodeEvent.success) {

                    NodeEventListAdapter nodeEventListAdapter = new NodeEventListAdapter(nodeEvent.NodeItems);
                    lv_event.setAdapter(nodeEventListAdapter);
//                    showNodeItems(nodeEvent.NodeItems);
                }
            }
        });

        initEventClick();
    }

    private void initEventClick() {
        iv_camera = (ImageView) view.findViewById(R.id.iv_camera);
        iv_alarm = (ImageView) view.findViewById(R.id.iv_alarm);
        iv_voice = (ImageView) view.findViewById(R.id.iv_voice);


        initCamera();
        initRemark();
        initAlarm();
        initVoice();
        initCommit();
    }

    private void initCommit() {
        Button btn_commit = (Button) view.findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交
                Log.d("333333", imagePaths.get(0));





                try {
                    OkHttpUtils.postAsyn(Utils.URL + Utils.URL_ORDER_ADD_FOLLOW_EVENT + "?json={\"Extra\":\"<node><item><key>26<\\/key><value>2016-07-29 16:14<\\/value><\\/item><\\/node>\",\"OrderHID\":\"1\",\"Node\":51,\"OrderID\":\"1\",\"Latitude\":31.168062,\"UserID\":\"1040\",\"Longitude\":121.317282,\"Remark\":\"\",\"OrderSignID\":\"8\"}", new OkHttpUtils.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.d("222222", "2");
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.d("111111", response.toString());
                        }

                    }, new File(imagePaths.get(0)), "file");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }


    private void initVoice() {
        //声音

        iv_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //声音



            }
        });
    }

    private void initAlarm() {
        //异常
        iv_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //异常
            }
        });
    }

    private void initRemark() {
        //备注
        et_remark = (EditText) view.findViewById(R.id.et_remark);

    }

    private void initCamera() {
        //相机
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机
                PhotoPickerIntent intent = new PhotoPickerIntent(EventActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
            }
        });


        gridView = (GridView) view.findViewById(R.id.gridView);

        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);

        // preview
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(EventActivity.this);
                intent.setCurrentItem(position);
                intent.setPhotoPaths(imagePaths);
                startActivityForResult(intent, REQUEST_PREVIEW_CODE);
            }
        });

        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);


    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;
        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if(listUrls.size() == 7){
                listUrls.remove(listUrls.size()-1);
            }
            inflater = LayoutInflater.from(EventActivity.this);
        }

        public int getCount(){
            return  listUrls.size();
        }
        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_image, parent,false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            final String path=listUrls.get(position);

            Glide.with(EventActivity.this)
                    .load(path)
                    .placeholder(R.mipmap.default_error)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(holder.image);

            return convertView;
        }
        class ViewHolder {
            ImageView image;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d("EventActivity", "list: " + "list = [" + list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    Log.d("EventActivity", "ListExtra: " + "ListExtra = [" + ListExtra.size());
                    loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
//        if (paths.contains("000000")){
//            paths.remove("000000");
//        }
//        paths.add("000000");
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.e("--", obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_event;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText(nodeName);
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setVisibility(View.GONE);
    }

}
