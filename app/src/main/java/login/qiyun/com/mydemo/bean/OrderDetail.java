package login.qiyun.com.mydemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/12.
 */
public class OrderDetail implements Serializable {
    public boolean success;
    public int OrderHID;
    public int OrderID;
    public String ContractNum;
    public String ReceiveType;
    public String MaterialCategory;
    public String FromPort;
    public String ToPort;
    public int Total;
    public String LoadTime;
    public int LoadTimeRangeDay;
    public int Loss;
    public int BetweenTime;
    public int SealedAmount;
    public String ShipCompany;
    public String ShipPhone;
    public String ShipMan;
    public String MaterialCompany;
    public String MaterialPhone;
    public String MaterialMan;
    public String MMSI;
    public int Water;
    public String ShipName;
    public String Remark;
    public String PicUrl;

    public OrderDetail() {
    }


//    protected OrderDetail(Parcel in) {
//        success = in.readByte() != 0;
//        OrderHID = in.readInt();
//        OrderID = in.readInt();
//        ContractNum = in.readString();
//        ReceiveType = in.readString();
//        MaterialCategory = in.readString();
//        FromPort = in.readString();
//        ToPort = in.readString();
//        Total = in.readInt();
//        LoadTime = in.readString();
//        LoadTimeRangeDay = in.readInt();
//        Loss = in.readInt();
//        BetweenTime = in.readInt();
//        SealedAmount = in.readInt();
//        ShipCompany = in.readString();
//        ShipPhone = in.readString();
//        ShipMan = in.readString();
//        MaterialCompany = in.readString();
//        MaterialPhone = in.readString();
//        MaterialMan = in.readString();
//        MMSI = in.readString();
//        Water = in.readInt();
//        ShipName = in.readString();
//        Remark = in.readString();
//        PicUrl = in.readString();
//    }
//
//    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
//        @Override
//        public OrderDetail createFromParcel(Parcel in) {
//            return new OrderDetail(in);
//        }
//
//        @Override
//        public OrderDetail[] newArray(int size) {
//            return new OrderDetail[size];
//        }
//    };

    @Override
    public String toString() {
        return "OrderDetail{" +
                "success=" + success +
                ", OrderHID=" + OrderHID +
                ", OrderID=" + OrderID +
                ", ContractNum='" + ContractNum + '\'' +
                ", ReceiveType='" + ReceiveType + '\'' +
                ", MaterialCategory='" + MaterialCategory + '\'' +
                ", FromPort='" + FromPort + '\'' +
                ", ToPort='" + ToPort + '\'' +
                ", Total=" + Total +
                ", LoadTime='" + LoadTime + '\'' +
                ", LoadTimeRangeDay=" + LoadTimeRangeDay +
                ", Loss=" + Loss +
                ", BetweenTime=" + BetweenTime +
                ", SealedAmount=" + SealedAmount +
                ", ShipCompany='" + ShipCompany + '\'' +
                ", ShipPhone='" + ShipPhone + '\'' +
                ", ShipMan='" + ShipMan + '\'' +
                ", MaterialCompany='" + MaterialCompany + '\'' +
                ", MaterialPhone='" + MaterialPhone + '\'' +
                ", MaterialMan='" + MaterialMan + '\'' +
                ", MMSI='" + MMSI + '\'' +
                ", Water=" + Water +
                ", ShipName='" + ShipName + '\'' +
                ", Remark='" + Remark + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                '}';
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeBooleanArray(new boolean[]{success});
//        dest.writeInt(OrderHID);
//        dest.writeInt(OrderID);
//        dest.writeString(ContractNum);
//        dest.writeString(ReceiveType);
//        dest.writeString(MaterialCategory);
//        dest.writeString(FromPort);
//        dest.writeString(ToPort);
//        dest.writeInt(Total);
//        dest.writeString(LoadTime);
//        dest.writeInt(LoadTimeRangeDay);
//        dest.writeInt(Loss);
//        dest.writeInt(BetweenTime);
//        dest.writeInt(SealedAmount);
//        dest.writeString(ShipCompany);
//        dest.writeString(ShipPhone);
//        dest.writeString(ShipMan);
//        dest.writeString(MaterialCompany);
//        dest.writeString(MaterialPhone);
//        dest.writeString(MaterialMan);
//        dest.writeString(MMSI);
//        dest.writeInt(Water);
//        dest.writeString(ShipName);
//        dest.writeString(Remark);
//        dest.writeString(PicUrl);
//
//    }
}
