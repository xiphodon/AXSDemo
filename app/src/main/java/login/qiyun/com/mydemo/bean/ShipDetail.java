package login.qiyun.com.mydemo.bean;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ShipDetail {
/*          "callsign":"BLGS8",
            "mmsi":"413444250",
            "shipname":"XING LONG ZHOU 172",
            "imo":"0",
            "shiptype":"油船",
            "length":"119",
            "breadth":"18",
            "eta":"07-13 18:00",
            "dest_port":"ZHA PU",
            "draught":"5.0",
            "postime":"2016-07-15 11:13:51",
            "longitude":"121.63310000",
            "latitude":"28.24350000",
            "course":"38.7",
            "heading":"N/A",
            "speed":"0.2",
            "navStatus":"锚泊"        */
    public String callsign;
    public String mmsi;
    public String shipname;
    public String imo;
    public String shiptype;
    public String length;
    public String breadth;
    public String eta;
    public String dest_port;
    public String draught;
    public String postime;
    public String longitude;
    public String latitude;
    public String course;
    public String heading;
    public String speed;
    public String navStatus;

    @Override
    public String toString() {
        return "ShipDetail{" +
                "callsign='" + callsign + '\'' +
                ", mmsi='" + mmsi + '\'' +
                ", shipname='" + shipname + '\'' +
                ", imo='" + imo + '\'' +
                ", shiptype='" + shiptype + '\'' +
                ", length='" + length + '\'' +
                ", breadth='" + breadth + '\'' +
                ", eta='" + eta + '\'' +
                ", dest_port='" + dest_port + '\'' +
                ", draught='" + draught + '\'' +
                ", postime='" + postime + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", course='" + course + '\'' +
                ", heading='" + heading + '\'' +
                ", speed='" + speed + '\'' +
                ", navStatus='" + navStatus + '\'' +
                '}';
    }
}
