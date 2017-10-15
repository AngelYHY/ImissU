package freestar.imissu.injector.module;

import android.app.Service;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import freestar.imissu.injector.PerService;
import freestar.imissu.injector.ContextLife;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */

@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ContextLife("Service")
    Context provideContext(){
        return mService;
    }

}
