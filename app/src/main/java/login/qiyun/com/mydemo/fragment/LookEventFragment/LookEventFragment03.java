package login.qiyun.com.mydemo.fragment.LookEventFragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import login.qiyun.com.mydemo.Utils.Utils;

/**
 * Created by Administrator on 2016/7/14.
 */
public class LookEventFragment03 extends LookEventBaseFragment {

    private TextView tv_look_event_other;
    private String orderSignID;

    public LookEventFragment03(String orderSignID, TextView tv_look_event_other) {
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
            tv_look_event_other.setText("添加异常");
            tv_look_event_other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //页面跳转
                    Toast.makeText(getActivity(),"添加异常",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public String setUrl() {
        return Utils.URL_ORDER_Alarm + "?OrderSignID=" + orderSignID;
    }
}
