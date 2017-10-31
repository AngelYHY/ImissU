package freestar.vip.factory.presenter;

import freestar.freelibrary.factory.BaseContract;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/13
 * github：
 */

public interface RegisterContact {
    interface RegisterView extends BaseContract.BaseView {
        // 注册成功
        void registerSuccess();
    }

    interface RegisterPresenter {
        // 发起一个注册
        void register(String phone, String password);

        // 检查手机号是否正确
        boolean checkMobile(String phone);
    }

}
