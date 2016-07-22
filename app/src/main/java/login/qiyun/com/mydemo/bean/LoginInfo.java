package login.qiyun.com.mydemo.bean;


/**
 * Created by Administrator on 2016/7/10.
 */
public class LoginInfo {
    public boolean success;
    public UserInfo userInfo;

    public static class UserInfo{
        public int UserID;
        public String RealName;
        public String CellPhone;

        @Override
        public String toString() {
            return "UserInfo{" +
                    "UserID=" + UserID +
                    ", RealName='" + RealName + '\'' +
                    ", CellPhone='" + CellPhone + '\'' +
                    '}';
        }
    }
}
