package freestar.vip.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import freestar.freelibrary.common.app.BaseApplication;
import freestar.vip.factory.util.DBFlowExclusionStrategy;


/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/12
 * github：
 */

public class Factory {
    // 单例模式
    private static final Factory instance;
    // 全局的Gson
    private final Gson gson;

    static {
        instance = new Factory();
    }

    private final ExecutorService mExecutor;

    private Factory() {
        // 新建一个4个线程的线程池
        mExecutor = Executors.newFixedThreadPool(4);
        gson=new GsonBuilder()
                // 设置时间格式
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                // 设置一个过滤器，数据库级别的 Model 不进行 Json 转换
        .setExclusionStrategies(new DBFlowExclusionStrategy())
                .create();
    }

    /**
     * Factory 中的初始化
     */
    public static void setup() {
        // 初始化数据库
        FlowManager.init(new FlowConfig.Builder(app())
                .openDatabasesOnInit(true) // 数据库初始化的时候就开始打开
                .build());
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    private static BaseApplication app() {
        return BaseApplication.getInstance();
    }

    public static Gson getGson() {
        return instance.gson;
    }
}
