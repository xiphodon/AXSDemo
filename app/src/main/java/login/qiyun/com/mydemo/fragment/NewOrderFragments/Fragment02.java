package login.qiyun.com.mydemo.fragment.NewOrderFragments;


import java.util.ArrayList;


/**
 * Created by Shipxy on 2016/7/11.
 */
public class Fragment02 extends ViewPager_BaseFragment {

    @Override
    public ArrayList<String> parameter() {
        //SignType=1&UserID=1040&ShipName=&Status=1&page=1&pageSize=10
        ArrayList<String> list = new ArrayList<>();
        list.add("2");
        list.add("");
        list.add("1");
        list.add("1");
        list.add("10");
        return list;
    }
}
