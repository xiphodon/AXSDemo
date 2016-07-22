package login.qiyun.com.mydemo.fragment.NewOrderFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import login.qiyun.com.mydemo.adapter.OrderListAdapter;


import java.util.ArrayList;


/**
 * Created by Shipxy on 2016/7/11.
 */
public class Fragment01 extends ViewPager_BaseFragment {

    @Override
    public ArrayList<String> parameter() {
        //SignType=1&UserID=1040&ShipName=&Status=1&page=1&pageSize=10
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("");
        list.add("1");
        list.add("1");
        list.add("10");
        return list;
    }
}
