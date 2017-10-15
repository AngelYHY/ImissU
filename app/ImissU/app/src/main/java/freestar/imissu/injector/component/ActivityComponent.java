package freestar.imissu.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import freestar.imissu.activity.AccountActivity;
import freestar.imissu.injector.ContextLife;
import freestar.imissu.injector.PerActivity;
import freestar.imissu.injector.module.ActivityModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(AccountActivity activity);

}
