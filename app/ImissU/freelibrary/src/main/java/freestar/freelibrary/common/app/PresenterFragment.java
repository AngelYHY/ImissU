package freestar.freelibrary.common.app;

import android.content.Context;
import android.support.annotation.StringRes;

import freestar.freelibrary.factory.BaseContract;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/9/30 0030
 * github：
 */

public abstract class PresenterFragment extends BaseFragment implements BaseContract.IView {

    private BaseContract.IPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 在界面 onAttach 之后就触发初始化 Presenter
        initPresenter();
    }

    /**
     * 初始化 Presenter
     */
    protected abstract void initPresenter();

    @Override
    public void showError(@StringRes int str) {
        // 显示错误，优先试用占位布局
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerLoadError(str);
        } else {
            BaseApplication.showToast(str);
        }
    }

    @Override
    public void showLoading() {
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerLoading();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }
}
