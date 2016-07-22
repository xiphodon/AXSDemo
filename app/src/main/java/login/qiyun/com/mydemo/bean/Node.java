package login.qiyun.com.mydemo.bean;

/**
 * Created by Administrator on 2016/7/14.
 */
public class Node {
    public int NodeID;
    public String NodeName;
    public int NodeItemEdition;
    public boolean IsDone;

    @Override
    public String toString() {
        return "Node{" +
                "NodeID=" + NodeID +
                ", NodeName='" + NodeName + '\'' +
                ", NodeItemEdition=" + NodeItemEdition +
                ", IsDone=" + IsDone +
                '}';
    }
}
