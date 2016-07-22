package login.qiyun.com.mydemo.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.Utils.Utils;

/**
 * Created by Administrator on 2016/7/13.
 */
public class OrderDetailListAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public OrderDetailListAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = View.inflate(Utils.getContext(), R.layout.order_detail_lv_item, null);

            viewHolder = new ViewHolder();

            viewHolder.tv_item_name = (TextView) view.findViewById(R.id.tv_item_name);
            viewHolder.tv_item_value = (TextView) view.findViewById(R.id.tv_item_value);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        String str = list.get(i);
        String[] split = str.split("\\$\\#\\$");

        viewHolder.tv_item_name.setText(split[0]);


        if(split.length<2||TextUtils.isEmpty(split[1])||split[1].equals("null")){
            viewHolder.tv_item_value.setText("空");
        }else{
            viewHolder.tv_item_value.setText(split[1]);
        }


        return view;
    }

    class ViewHolder{
        TextView tv_item_name;
        TextView tv_item_value;
    }

}

