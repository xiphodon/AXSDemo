package login.qiyun.com.mydemo.bean;

import java.util.ArrayList;

/**
 * 订单节点
 */
public class OrderNode {
    public boolean success;
    public ArrayList<NodeItem> NodeItems;

    public class NodeItem{
        public int NodeItemID;
        public String ItemName;
        public String DataType;
        public boolean Required;
        public ArrayList<NodeItemDrop> NodeItemDrops;

        public class NodeItemDrop{
            public String Value;
            public String Name;
        }
    }
}
