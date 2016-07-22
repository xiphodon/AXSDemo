package login.qiyun.com.mydemo.bean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * 装货港bean
 */
public class OrderList01 {
    public boolean success;
    public int PageNumber;
    public int PageSize;
    public int TotalCount;
    public int TotalItemCount;
    public ArrayList<OrderMsg> PageList;

    public static class OrderMsg{
        public int OrderSignID;
        public int OrderHID;
        public int OrderID;
        public String MaterialCategory;
        public String ShipName;
        public String FromPort;
        public String ToPort;
        public int Total;
        public String LoadTime;
        public int LoadTimeRangeDay;
        public String ContractNum;
        public boolean IsNew;
        public int Status;
        public String Stage;

        @Override
        public String toString() {
            return "OrderMsg{" +
                    "OrderSignID=" + OrderSignID +
                    ", OrderHID=" + OrderHID +
                    ", OrderID=" + OrderID +
                    ", MaterialCategory='" + MaterialCategory + '\'' +
                    ", ShipName='" + ShipName + '\'' +
                    ", FromPort='" + FromPort + '\'' +
                    ", ToPort='" + ToPort + '\'' +
                    ", Total=" + Total +
                    ", LoadTime='" + LoadTime + '\'' +
                    ", LoadTimeRangeDay=" + LoadTimeRangeDay +
                    ", ContractNum='" + ContractNum + '\'' +
                    ", IsNew=" + IsNew +
                    ", Status=" + Status +
                    ", Stage='" + Stage + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderList01{" +
                "success=" + success +
                ", PageNumber=" + PageNumber +
                ", PageSize=" + PageSize +
                ", TotalCount=" + TotalCount +
                ", TotalItemCount=" + TotalItemCount +
                ", PageList=" + PageList +
                '}';
    }
}
