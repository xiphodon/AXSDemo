package login.qiyun.com.mydemo.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.baidu.mapapi.SDKInitializer;


/**
 * Created by Shipxy on 2016/7/7.
 */
public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;


    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = Process.myTid();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(context);


    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

}
