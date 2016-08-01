package login.qiyun.com.mydemo.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;


import com.lidroid.xutils.HttpUtils;

import login.qiyun.com.mydemo.application.MyApplication;


/**
 * Created by Shipxy on 2016/7/7.
 */
public class Utils {

    public static final boolean STATE_SUCCESS = true;
    public static final boolean STATE_FAIL = false;
    public static String URL = "http://axs.ikuaichuan.com:1092";
    public static String URL_LOGIN = "/Account/Login";
    public static String URL_ORDER = "/Order/GetOrderList";
    public static String URL_ORDER_COUNT = "/Order/GetNewOrderCount";
    public static String URL_ORDER_DETAIL = "/Order/OrderDetail";
    public static String URL_ORDER_NODE = "/Order/Node";
    public static String URL_ORDER_INCIDENT = "/Order/Incident";
    public static String URL_ORDER_Alarm = "/Order/Alarm";
    public static String URL_ORDER_GET_ORDER_SIGN_NODE = "/Order/GetOrderSignNode";
    public static String URL_MYSHIPS_SHIPDETAIL = "/MyShips/ShipDetail";
    public static String URL_ORDER_GET_ORDER_SIGN_NODE_ITEM = "/Order/GetOrderSignNodeItem";
    public static String URL_ORDER_ADD_FOLLOW_EVENT = "/Order/AddFollowEvent";

    private static HttpUtils httpUtils;

    public static HttpUtils getHttp(){

        if (httpUtils == null){
            synchronized (Utils.class){
                if (httpUtils == null){
                    httpUtils = new HttpUtils();
                }
            }

        }
        return httpUtils;
    };

    /**
     * dp转px
     * @param dip dp
     * @return px（像素）
     */
    public static int dip2px(float dip){
        //获取屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        //四舍五入
        return (int) (dip * density + 0.5f);
    }

    /**
     * 根据id获取颜色的状态选择器
     * @param id
     * @return
     */
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }


    /**
     * 获取图片
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id){
        return getContext().getResources().getDrawable(id);
    }


    /**
     * 获取Context对象
     * @return
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 获取Handler
     * @return
     */
    public static Handler getHandler(){
        return MyApplication.getHandler();
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int mainThreadId(){
        return MyApplication.getMainThreadId();
    }


    /**
     * 判断此线程是否是主线程
     * @return
     */
    public static boolean isRunOnUIThread(){
        int myTid = Process.myTid();
        return myTid == mainThreadId();
    }

    /**
     * 运行在主线程
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable){
        if(isRunOnUIThread()){
            //已经在主线程中
            runnable.run();
        }else{
            //不在主线程中，借助Handler让其运行在主线程
            getHandler().post(runnable);
        }
    }

    /**
     * 获取字符串数组
     * @param id
     * @return
     */
    public static String[] getStringArrary(int id){
        return getContext().getResources().getStringArray(id);
    }


}
