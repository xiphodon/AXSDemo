package login.qiyun.com.mydemo.activity;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.lidong.photopicker.ImageCaptureManager;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;

import org.json.JSONArray;

import java.util.ArrayList;

import login.qiyun.com.mydemo.R;
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

    @Override
    protected void initUI() {
        Intent intent = getIntent();
        nodeID = intent.getStringExtra("NodeID");
        nodeName = intent.getStringExtra("NodeName");

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
        ImageView iv_camera = (ImageView) view.findViewById(R.id.iv_camera);
        ImageView iv_alarm = (ImageView) view.findViewById(R.id.iv_alarm);
        ImageView iv_voice = (ImageView) view.findViewById(R.id.iv_voice);

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

        iv_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //异常
            }
        });

        iv_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //声音
            }
        });

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


            }
        });
    }

    private void initVoice() {
        //声音
    }

    private void initAlarm() {
        //异常
    }

    private void initRemark() {
        //备注
        et_remark = (EditText) view.findViewById(R.id.et_remark);

    }

    private void initCamera() {
        //相机
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
