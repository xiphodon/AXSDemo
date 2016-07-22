package login.qiyun.com.mydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.adapter.NewOrderPagerAdapter;

/**
 * Created by Shipxy on 2016/7/7.
 */
public abstract class BaseFragment extends Fragment {

    private View view;
    private TabPageIndicator indicator;
    private ViewPager vp_new_order;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutID = setLayoutID();
        view = inflater.inflate(layoutID,container,false);


        initView();
        initData();

        return view;
    }

    private void initData() {
        vp_new_order.setAdapter(new NewOrderPagerAdapter(getActivity().getSupportFragmentManager()));
        indicator.setViewPager(vp_new_order);

    }

    private void initView() {
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        vp_new_order = (ViewPager) view.findViewById(R.id.vp_new_order);

    }

    public abstract int setLayoutID();

}
