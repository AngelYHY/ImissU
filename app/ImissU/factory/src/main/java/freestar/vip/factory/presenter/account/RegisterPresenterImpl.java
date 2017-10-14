package freestar.vip.factory.presenter.account;

import android.text.TextUtils;

import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.regex.Pattern;

import freestar.freelibrary.common.Common;
import freestar.freelibrary.common.app.BaseActivity;
import freestar.freelibrary.factory.BasePresenterImpl;
import freestar.vip.factory.R;
import freestar.vip.factory.model.RspModel;
import freestar.vip.factory.model.api.account.AccountRspModel;
import freestar.vip.factory.model.api.account.RegisterModel;
import freestar.vip.factory.net.Network;
import freestar.vip.factory.presenter.RegisterContact;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/14
 * github：
 */

public class RegisterPresenterImpl extends BasePresenterImpl<RegisterContact.RegisterView> implements RegisterContact.RegisterPresenter {


    public RegisterPresenterImpl(RegisterContact.RegisterView view) {
        super(view);
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

        Observable<AccountRspModel> register = Network.remote()
                .accountRegister(model);
        Observable compose = register.compose(mView.<AccountRspModel>applySchedulers());

        register.compose(mView.<RspModel<AccountRspModel>>applySchedulers())
                .compose(((BaseActivity) mView).<RspModel<AccountRspModel>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Observer<RspModel<AccountRspModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RspModel<AccountRspModel> o) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
