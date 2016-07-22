package login.qiyun.com.mydemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;
import login.qiyun.com.mydemo.bean.OrderList01;

/**
 * Created by Shipxy on 2016/7/11.
 */
public class OrderListAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private ArrayList<OrderList01.OrderMsg> pageList;

    public void setPageList(ArrayList<OrderList01.OrderMsg> pageList) {
        this.pageList = pageList;
    }

    public OrderListAdapter(ArrayList<OrderList01.OrderMsg> pageList) {
        this.pageList = pageList;
    }

    @Override
    public int getCount() {
        return pageList.size();
    }

    @Override
    public OrderList01.OrderMsg getItem(int i) {
        return pageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = View.inflate(Utils.getContext(), R.layout.new_order_list01_item, null);

            viewHolder = new ViewHolder();

            viewHolder.tv_item_MaterialCategory = (TextView) view.findViewById(R.id.tv_item_MaterialCategory);
            viewHolder.tv_item_from_to = (TextView) view.findViewById(R.id.tv_item_from_to);
            viewHolder.tv_item_total = (TextView) view.findViewById(R.id.tv_item_total);
            viewHolder.tv_item_loadtime = (TextView) view.findViewById(R.id.tv_item_loadtime);
            viewHolder.tv_item_shipname = (TextView) view.findViewById(R.id.tv_item_shipname);
            viewHolder.tv_item_new_order_state = (TextView) view.findViewById(R.id.tv_item_new_order_state);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_item_MaterialCategory.setText(pageList.get(i).MaterialCategory);
        viewHolder.tv_item_from_to.setText(pageList.get(i).FromPort + "→" + pageList.get(i).ToPort);
        viewHolder.tv_item_total.setText(pageList.get(i).Total + "吨");
        viewHolder.tv_item_loadtime.setText(pageList.get(i).LoadTime + "±" + pageList.get(i).LoadTimeRangeDay);
        viewHolder.tv_item_shipname.setText(pageList.get(i).ShipName);
        viewHolder.tv_item_new_order_state.setText("新订单");

        return view;
    }

    class ViewHolder{
        TextView tv_item_MaterialCategory;
        TextView tv_item_from_to;
        TextView tv_item_total;
        TextView tv_item_loadtime;
        TextView tv_item_shipname;
        TextView tv_item_new_order_state;
    }

}

