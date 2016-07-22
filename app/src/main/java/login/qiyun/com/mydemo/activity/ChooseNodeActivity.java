package login.qiyun.com.mydemo.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.adapter.ChooseNodeListAdapter;
import login.qiyun.com.mydemo.bean.Node;
import login.qiyun.com.mydemo.protocol.ChooseNodeProtocol;

public class ChooseNodeActivity extends BaseActivity {


    private View view;
    private ChooseNodeListAdapter chooseNodeListAdapter;
    private List<Node> list = new ArrayList<>();
    private String orderSignID;
    private ListView lv_choose_node;

    @Override
    protected void initUI() {

        Intent intent = getIntent();
        orderSignID = intent.getStringExtra("orderSignID");

        getData();

        view = getView();
        lv_choose_node = (ListView) view.findViewById(R.id.lv_choose_node);

        lv_choose_node.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.size() != 0){
                    //点击事件
                    Intent intent = new Intent();
                    intent.setClass(ChooseNodeActivity.this,EventActivity.class);
                    intent.putExtra("NodeID",list.get(position).NodeID + "");
                    intent.putExtra("NodeName",list.get(position).NodeName);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public int setContentViewLayoutID() {
        return R.layout.activity_choose_node;
    }

    @Override
    public void setTitleText(TextView tv_title) {
        tv_title.setText("选择航次节点");
    }

    @Override
    public void setOtherSettings(TextView tv_other) {
        tv_other.setVisibility(View.GONE);
    }

    public List getData() {
        ChooseNodeProtocol chooseNodeProtocol = new ChooseNodeProtocol();
        chooseNodeProtocol.getDataFromService(orderSignID, new ChooseNodeProtocol.ShowCallBack() {
            @Override
            public void showResult(List<Node> list) {
                if(list != null){
                    ChooseNodeActivity.this.list = list;

                    chooseNodeListAdapter = new ChooseNodeListAdapter(list);
                    lv_choose_node.setAdapter(chooseNodeListAdapter);
                }else{
                    Toast.makeText(Utils.getContext(),"网络错误",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return this.list;
    }
}
