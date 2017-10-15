package freestar.imissu.injector.component;

import android.content.Context;

import dagger.Component;
import freestar.imissu.injector.PerService;
import freestar.imissu.injector.ContextLife;
import freestar.imissu.injector.module.ServiceModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@PerService
@Component(dependencies = ApplicationComponent.class,modules = ServiceModule.class)
public interface ServiceComponent {

    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();

}
