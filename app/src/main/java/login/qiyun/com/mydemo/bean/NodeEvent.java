package login.qiyun.com.mydemo.bean;

import java.util.List;

/**
 * Created by Qytrans on 2016/7/21.
 */
public class NodeEvent {

    /*
    *
    * {
    "success":true,
    "NodeItems":[
        {
            "NodeItemID":66,
            "ItemName":"交接数量",
            "DataType":"decimal",
            "Required":true,
            "NodeItemDrops":[

            ]
        },
        {
            "NodeItemID":67,
            "ItemName":"是否有事故",
            "DataType":"select",
            "Required":true,
            "NodeItemDrops":[
                {
                    "Value":"1",
                    "Name":"是"
                },
                {
                    "Value":"0",
                    "Name":"否"
                }
            ]
        },
        {
            "NodeItemID":68,
            "ItemName":"船舶晚到",
            "DataType":"select",
            "Required":true,
            "NodeItemDrops":[
                {
                    "Value":"2",
                    "Name":"正常"
                },
                {
                    "Value":"1",
                    "Name":"短时晚到"
                },
                {
                    "Value":"0",
                    "Name":"长时晚到"
                }
            ]
        }
    ]
}
    *
    *
    * */

    public boolean success;
    public List<NodeItem> NodeItems;

    public class NodeItem{
        public int NodeItemID;
        public String ItemName;
        public String DataType;
        public boolean Required;
        public List<NodeItemDrop> NodeItemDrops;

        public class NodeItemDrop{
            public String Value;
            public String Name;

            @Override
            public String toString() {
                return "NodeItemDrop{" +
                        "Value='" + Value + '\'' +
                        ", Name='" + Name + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "NodeItem{" +
                    "NodeItemID=" + NodeItemID +
                    ", ItemName='" + ItemName + '\'' +
                    ", DataType='" + DataType + '\'' +
                    ", Required=" + Required +
                    ", NodeItemDrops=" + NodeItemDrops +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NodeEvent{" +
                "success=" + success +
                ", NodeItems=" + NodeItems +
                '}';
    }
}
