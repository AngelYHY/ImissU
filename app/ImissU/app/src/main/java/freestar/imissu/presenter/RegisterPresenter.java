package freestar.imissu.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.regex.Pattern;

import javax.inject.Inject;

import freestar.freelibrary.Factory;
import freestar.freelibrary.R;
import freestar.freelibrary.common.Common;
import freestar.freelibrary.common.app.BaseActivity;
import freestar.freelibrary.contact.account.RegisterContact;
import freestar.freelibrary.factory.BasePresenter;
import freestar.freelibrary.model.RspModel;
import freestar.freelibrary.model.api.account.AccountRspModel;
import freestar.freelibrary.model.api.account.RegisterModel;
import freestar.freelibrary.net.RemoteService;
import freestar.freelibrary.persistence.Account;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/14
 * github：
 */

public class RegisterPresenter extends BasePresenter<RegisterContact.IRegisterView> implements RegisterContact.IRegisterPresenter {

    private final RemoteService mService;

    @Inject
    RegisterPresenter(RemoteService service) {
        mService = service;
    }

    @Override
    public void register(String phone, String password) {
        // 调用开始方法，在start中默认启动了Loading
        start();
        // 校验
        if (!checkMobile(phone)) {
            // 提示
            mView.showError(R.string.data_account_register_invalid_parameter_mobile);
        } else if (password.length() < 6) {
            // 密码需要大于 6 位
            mView.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            // 进行网络请求
            // 构造Model，进行请求调用
            RegisterModel model = new RegisterModel(phone, password);
            // 进行网络请求，并设置回送接口为自己
            register(model);
        }
    }

    private void register(RegisterModel model) {
        mService.accountRegister(model)
                .compose(mView.<RspModel<AccountRspModel>>applySchedulers())
                .compose(((BaseActivity) mView).<RspModel<AccountRspModel>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Observer<RspModel<AccountRspModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RspModel<AccountRspModel> rspModel) {
                        Logger.e("有返回值");
                        if (rspModel.success()) {
                            AccountRspModel result = rspModel.getResult();
                            // 同步到XML持久化中
                            Account.login(result.getPhone(), result.getPassword());
                            mView.registerSuccess();
                        } else {
                            // 错误解析
                            int msg = Factory.decodeRspCode(rspModel);
                            if (msg != 0) {
                                mView.showError(msg);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FreeStar", "onFailure:" + e.getMessage());
                        mView.showError(R.string.data_network_error);
                    }

                    @Override
                    public void onComplete() {
                        ((BaseActivity) mView).hideLoading();
                    }
                });

    }

    /**
     * 检查手机号是否合法
     *
     * @param phone 手机号码
     * @return 合法为 True
     */
    @Override
    public boolean checkMobile(String phone) {
        // 手机号不为空，并且满足格式
        return !TextUtils.isEmpty(phone)
                && Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }
}
