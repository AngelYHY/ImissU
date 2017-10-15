package freestar.imissu.my_base;

import freestar.freelibrary.common.app.BaseActivity;
import freestar.imissu.injector.component.ActivityComponent;
import freestar.imissu.injector.component.DaggerActivityComponent;
import freestar.imissu.injector.module.ActivityModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */

public abstract class MyActivity extends BaseActivity{
    protected ActivityComponent mActivityComponent;


    @Override
    protected void initComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .build();

        setPresenterAndInjectActivity();
    }

    /**
     * 设置 presenter 和 注入 activity
     */
    protected abstract void setPresenterAndInjectActivity();
}
