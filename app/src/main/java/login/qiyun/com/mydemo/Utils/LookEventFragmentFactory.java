package login.qiyun.com.mydemo.Utils;

import android.widget.TextView;

import java.util.HashMap;

import login.qiyun.com.mydemo.fragment.LookEventFragment.LookEventBaseFragment;
import login.qiyun.com.mydemo.fragment.LookEventFragment.LookEventFragment01;
import login.qiyun.com.mydemo.fragment.LookEventFragment.LookEventFragment02;
import login.qiyun.com.mydemo.fragment.LookEventFragment.LookEventFragment03;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.Fragment01;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.Fragment02;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.ViewPager_BaseFragment;

/**
 * Fragment工厂类
 */
public class LookEventFragmentFactory {
    private static HashMap<Integer,LookEventBaseFragment> fragmentMap = new HashMap<Integer, LookEventBaseFragment>();

    public static LookEventBaseFragment createFragment(int pos, String orderSignID, TextView tv_look_event_other) {

        LookEventBaseFragment fragment = fragmentMap.get(pos);

//        if (fragment == null){
            switch (pos) {
                case 0:
                    fragment = new LookEventFragment01(orderSignID,tv_look_event_other);
                    break;
                case 1:
                    fragment = new LookEventFragment02(orderSignID,tv_look_event_other);
                    break;
                case 2:
                    fragment = new LookEventFragment03(orderSignID,tv_look_event_other);
                    break;

                default:
                    break;

//            }

//            fragmentMap.put(pos,fragment);
        }

        return fragment;
    }
}
