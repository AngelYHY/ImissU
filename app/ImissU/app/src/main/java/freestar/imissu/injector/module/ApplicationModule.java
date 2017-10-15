package freestar.imissu.injector.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import freestar.imissu.injector.ContextLife;
import freestar.freelibrary.net.Network;
import freestar.freelibrary.net.RemoteService;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    Context provideContext(){
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    RemoteService provideRemoteService() {
        return Network.remote();
    }
}
