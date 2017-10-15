package freestar.freelibrary.factory;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.ObservableTransformer;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/9/30 0030
 * github：
 */

public interface BaseContract {

    interface IView {
        // 公共的：显示一个字符串错误
        void showError(@StringRes int str);

        // 公共的：显示进度条
        void showLoading();

        <M> ObservableTransformer<M, M> applySchedulers();  //rxjava2

    }

    // 基本的 Presenter 职责

    interface IPresenter<T>  {
        /**
         * 注入View，使之能够与View相互响应
         *
         * @param iView
         */
        void attachView(@NonNull T iView);


        // 共用的开始触发
        void start();

        // 共用的销毁触发
        void destroy();
    }

}
