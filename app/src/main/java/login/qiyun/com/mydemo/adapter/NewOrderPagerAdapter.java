package login.qiyun.com.mydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.NewOrderFragmentFactory;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.fragment.BaseFragment;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.ViewPager_BaseFragment;

/**
 * Created by Administrator on 2016/7/10.
 */
public class NewOrderPagerAdapter extends FragmentPagerAdapter {

    private final String[] stringArrary;

    public NewOrderPagerAdapter(FragmentManager fm) {
        super(fm);
        //加载页面标题数组
        stringArrary = Utils.getStringArrary(R.array.tab_new_order_names);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrary[position];
    }

    @Override
    public int getCount() {
        return stringArrary.length;
    }


    @Override
    public Fragment getItem(int position) {
        ViewPager_BaseFragment fragment = NewOrderFragmentFactory.createFragment(position);
        return fragment;
    }


}
