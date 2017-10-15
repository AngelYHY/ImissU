package freestar.freelibrary.common.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/9/19
 * github：
 */

public abstract class BaseApp extends Application {

    protected String appId;
    protected String appSecret;
    protected String rsa;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
//        LogUtils.logInit(BuildConfig.LOG_DEBUG);
        init();
    }

    // 初始化 热修复的 key
    protected abstract void initKey();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        initKey();

        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }

        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setSecretMetaData(appId, appSecret, rsa)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Logger.e("表明补丁加载成功");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            Logger.e("表明新补丁生效需要重启");
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Logger.e("其他错误" + code + "--" + info);
                        }
                    }
                }).initialize();

    }

    private void init() {
        Logger.init("FreeStar").methodCount(2);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();

        initComponent();
    }

    protected abstract void initComponent();

}
