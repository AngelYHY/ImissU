package freestar.imissu.my_base;

import freestar.freelibrary.common.app.BaseFragment;
import freestar.imissu.injector.component.DaggerFragmentComponent;
import freestar.imissu.injector.component.FragmentComponent;
import freestar.imissu.injector.module.FragmentModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/15 0015
 * github：
 */

public abstract class MyFragment extends BaseFragment {

    protected FragmentComponent mFragmentComponent;

    @Override
    protected void initComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(((MyApplication) getActivity().getApplication()).getApplicationComponent())
                .build();

        setPresenterAndInjectFragment();
    }

    /**
     * 设置 presenter 和 注入 fragment
     */
    protected abstract void setPresenterAndInjectFragment();

}
