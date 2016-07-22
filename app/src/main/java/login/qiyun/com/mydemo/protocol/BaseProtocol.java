package login.qiyun.com.mydemo.protocol;

/**
 * 访问网络的基类
 */
public abstract class BaseProtocol<T> {

    // 解析json数据, 子类必须实现
    public abstract T parseData(String result);
}