package freestar.imissu.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import freestar.imissu.injector.PerFragment;
import freestar.imissu.injector.ContextLife;
import freestar.imissu.injector.module.FragmentModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */
@PerFragment
@Component(modules = FragmentModule.class,dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    @ContextLife("Application")
    Context getContext();

    @ContextLife("Activity")
    Context getActivityContext();

    Activity getActivity();

}
