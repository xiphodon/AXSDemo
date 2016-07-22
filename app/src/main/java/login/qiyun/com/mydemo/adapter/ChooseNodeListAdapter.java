package login.qiyun.com.mydemo.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.Node;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ChooseNodeListAdapter extends BaseAdapter {

    private List<Node> list;
    private TextView tv_choose_node;

    public ChooseNodeListAdapter(List<Node> list) {
        this.list = list;
        Log.d("list.size()",list.size() + "");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Node getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(Utils.getContext(), R.layout.choose_node_lv_item, null);
        tv_choose_node = (TextView) view.findViewById(R.id.tv_choose_node);
        tv_choose_node.setText(list.get(position).NodeName.trim());

        return view;
    }

}
