package freestar.freelibrary.common.app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.StringRes;

import freestar.freelibrary.R;
import freestar.freelibrary.factory.BaseContract;


/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/11
 * github：
 */

public abstract class PresenterToolbarActivity<Presenter extends BaseContract.BasePresenter> extends ToolbarActivity  {
    protected Presenter mPresenter;
    protected ProgressDialog mLoadingDialog;

    @Override
    protected void initBefore() {
        super.initBefore();
        // 初始化 Presenter
        initPresenter();
    }

    protected abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面关闭时进行销毁的操作
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    @Override
    public void showError(@StringRes int str) {
        // 不管你怎么样，我先隐藏我
        hideDialogLoading();

        // 显示错误，优先使用占位布局
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
        } else {
            ProgressDialog dialog = mLoadingDialog;
            if (dialog == null) {
                dialog = new ProgressDialog(this, R.style.AppTheme_Dialog_Alert_Light);
                // 不可触摸取消
                dialog.setCanceledOnTouchOutside(false);
                // 强制取消关闭界面
                dialog.setCancelable(true);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                mLoadingDialog = dialog;
            }
            dialog.setMessage(getText(R.string.prompt_loading));
            dialog.show();
        }
    }

    private void hideDialogLoading() {
        ProgressDialog dialog = mLoadingDialog;
        if (dialog != null) {
            mLoadingDialog = null;
            dialog.dismiss();
        }
    }

    /**
     * 加载成功
     */
    protected void hideLoading() {
        // 不管你怎么样，我先隐藏我
        hideDialogLoading();

        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerLoadOk();
        }
    }

}
