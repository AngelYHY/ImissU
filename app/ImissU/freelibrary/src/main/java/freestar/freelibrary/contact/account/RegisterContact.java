package freestar.freelibrary.contact.account;


import android.support.annotation.StringRes;

import freestar.freelibrary.factory.BaseContract;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/13
 * github：
 */

public interface RegisterContact {
    interface IRegisterView extends BaseContract.IView {
        // 注册成功
        void registerSuccess();

        void setStatus(@StringRes int stringRes);

    }

    interface IRegisterPresenter {
        // 发起一个注册
        void register(String phone, String password);

        // 检查手机号是否正确
        boolean checkMobile(String phone);
    }

}
