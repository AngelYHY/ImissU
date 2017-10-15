package freestar.imissu.injector.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import freestar.imissu.injector.ContextLife;
import freestar.imissu.injector.module.ApplicationModule;
import freestar.freelibrary.net.RemoteService;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getContext();

    Application injectApplication(Application application);

    RemoteService getRemoteService();

}
