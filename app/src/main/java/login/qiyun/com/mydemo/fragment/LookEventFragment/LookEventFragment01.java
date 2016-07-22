package login.qiyun.com.mydemo.fragment.LookEventFragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.activity.ChooseNodeActivity;

/**
 * Created by Administrator on 2016/7/14.
 */
public class LookEventFragment01 extends LookEventBaseFragment {

    private TextView tv_look_event_other;
    private String orderSignID;


    public LookEventFragment01(String orderSignID, TextView tv_look_event_other) {
        this.orderSignID = orderSignID;
        this.tv_look_event_other = tv_look_event_other;
    }

    @Override
    protected void initUI() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            Log.d("tag",tv_look_event_other.toString());
            tv_look_event_other.setText("添加进度");
            tv_look_event_other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //页面跳转
                    Toast.makeText(getActivity(),"添加进度",Toast.LENGTH_SHORT).show();
                    //http://axs.ikuaichuan.com:1098/Order/GetOrderSignNode?OrderSignID=9
                    Intent intent = new Intent(getActivity(), ChooseNodeActivity.class);
                    intent.putExtra("orderSignID",orderSignID);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public String setUrl() {
        return Utils.URL_ORDER_NODE + "?OrderSignID=" + orderSignID;
    }
}
