package login.qiyun.com.mydemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.NodeEvent;

/**
 * Created by Qytrans on 2016/7/21.
 */
public class NodeEventListAdapter extends BaseAdapter{

    private List<NodeEvent.NodeItem> nodeItems;
    private ViewHolder viewHolder;
    private View view;

    public NodeEventListAdapter(List<NodeEvent.NodeItem> nodeItems) {
        this.nodeItems = nodeItems;
    }

    @Override
    public int getCount() {
        return nodeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return nodeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            viewHolder = new ViewHolder();
            view = View.inflate(Utils.getContext(), R.layout.event_lv_item,null);

            viewHolder.tv_star = (TextView) view.findViewById(R.id.tv_star);
            viewHolder.tv_event_item_name = (TextView) view.findViewById(R.id.tv_event_item_name);
            viewHolder.fl_event_item_value = (FrameLayout) view.findViewById(R.id.fl_event_item_value);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        NodeEvent.NodeItem nodeItem = nodeItems.get(position);

        if(nodeItem.Required){
            viewHolder.tv_star.setVisibility(View.VISIBLE);
        }else{
            viewHolder.tv_star.setVisibility(View.INVISIBLE);
        }

        viewHolder.tv_event_item_name.setText(nodeItem.ItemName);

        switch (nodeItem.DataType){
            case "decimal":
                break;

            case "number":
                break;

            case "datetime":
                break;

            case "select":
                break;

            case "date":
                break;
        }

        return view;
    }

    class ViewHolder{
        TextView tv_star;
        TextView tv_event_item_name;
        FrameLayout fl_event_item_value;
    }
}
