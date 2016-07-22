package login.qiyun.com.mydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.LookEventFragmentFactory;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.fragment.LookEventFragment.LookEventBaseFragment;

/**
 * Created by Administrator on 2016/7/14.
 */
public class LookEventPagerAdapter extends FragmentPagerAdapter {

    private TextView tv_look_event_other;
    private final String[] stringArrary;
    private String orderSignID;

    public LookEventPagerAdapter(FragmentManager fm, String orderSignID, TextView tv_look_event_other) {
        super(fm);
        stringArrary = Utils.getStringArrary(R.array.tab_new_look_event);
        this.orderSignID = orderSignID;
        this.tv_look_event_other = tv_look_event_other;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrary[position];
    }

    @Override
    public Fragment getItem(int position) {
        LookEventBaseFragment fragment = LookEventFragmentFactory.createFragment(position,orderSignID,tv_look_event_other);
        return fragment;
    }

    @Override
    public int getCount() {
        return stringArrary.length;
    }
}
