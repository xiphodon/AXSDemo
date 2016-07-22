package login.qiyun.com.mydemo.Utils;

import java.util.HashMap;

import login.qiyun.com.mydemo.fragment.BaseFragment;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.Fragment01;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.Fragment02;
import login.qiyun.com.mydemo.fragment.NewOrderFragments.ViewPager_BaseFragment;

/**
 * Fragment工厂类
 */
public class NewOrderFragmentFactory {
    private static HashMap<Integer,ViewPager_BaseFragment> fragmentMap = new HashMap<Integer, ViewPager_BaseFragment>();

    public static ViewPager_BaseFragment createFragment(int pos) {

        ViewPager_BaseFragment fragment = fragmentMap.get(pos);

        if (fragment == null){
            switch (pos) {
                case 0:
                    fragment = new Fragment01();
                    break;
                case 1:
                    fragment = new Fragment02();
                    break;

                default:
                    break;

            }

            fragmentMap.put(pos,fragment);
        }

        return fragment;
    }
}
