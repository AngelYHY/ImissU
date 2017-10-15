package freestar.imissu.activity;

import android.support.annotation.StringRes;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.orhanobut.logger.Logger;

import net.qiujuer.genius.ui.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import freestar.freelibrary.contact.account.RegisterContact;
import freestar.imissu.presenter.RegisterPresenter;
import freestar.imissu.R;
import freestar.imissu.my_base.MyActivity;
import freestar.imissu.my_base.MyApplication;

public class AccountActivity extends MyActivity implements View.OnClickListener, RegisterContact.IRegisterView {

    @Inject
    RegisterPresenter mPresenter;

    @Bind(R.id.account_txt_title)
    TextView mAccountTxtTitle;
    @Bind(R.id.account_edit_login_email)
    EditText mAccountEditLoginEmail;
    @Bind(R.id.account_edit_login_password)
    EditText mAccountEditLoginPassword;
    @Bind(R.id.account_lay_login)
    LinearLayout mAccountLayLogin;
    @Bind(R.id.account_edit_register_email)
    EditText mAccountEditRegisterEmail;
    @Bind(R.id.account_edit_register_password)
    EditText mAccountEditRegisterPassword;
    @Bind(R.id.account_edit_register_password_confirm)
    android.widget.EditText mAccountEditRegisterPasswordConfirm;
    @Bind(R.id.account_lay_register)
    LinearLayout mAccountLayRegister;
    @Bind(R.id.account_output)
    ViewAnimator mViewAnimator;
    @Bind(R.id.account_txt_whether)
    TextView mAccountTxtWhether;
    @Bind(R.id.account_btn_change)
    Button mAccountBtnChange;
    @Bind(R.id.account_toLogin_link)
    LinearLayout mAccountToLoginLink;
    @Bind(R.id.account_btn_submit)
    net.qiujuer.genius.ui.widget.Button mAccountBtnSubmit;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void presenterAttachView() {
        if (mPresenter == null) {
            Logger.e("空");
        } else {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void initView() {
        mViewAnimator.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_in_slide_right));
        mViewAnimator.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_out_slide_left));

        mAccountBtnSubmit.setOnClickListener(this);
        mAccountBtnChange.setOnClickListener(this);
    }

    @Override
    protected void setPresenterAndInjectActivity() {
        mActivityComponent.inject(this);
        mIPresenter = mPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_btn_submit:
                if (!isLogin()) {
                    String phone = mAccountEditRegisterEmail.getText().toString();
                    String password = mAccountEditRegisterPassword.getText().toString();
                    String confirm = mAccountEditRegisterPasswordConfirm.getText().toString();
                    if (password.equals(confirm)) {
                        mPresenter.register(phone, password);
                    }
                }
                break;
            case R.id.account_btn_change:
                mViewAnimator.showNext();
                if (isLogin()) {
                    mAccountTxtWhether.setText(getString(R.string.txt_not_have_account));
                    mAccountTxtTitle.setText(getString(R.string.txt_title_login));
                    mAccountBtnChange.setText(getString(R.string.txt_not_have_account_btn));
                } else {
                    mAccountTxtWhether.setText(getString(R.string.txt_have_account));
                    mAccountTxtTitle.setText(getString(R.string.txt_title_join));
                    mAccountBtnChange.setText(getString(R.string.txt_have_account_btn));
                }
                break;
        }
    }

    private boolean isLogin() {
        View view = mViewAnimator.getCurrentView();
        return view.getId() == R.id.account_lay_login;
    }

    @Override
    public void registerSuccess() {
        MyApplication.showToast("注册成功");
    }

    @Override
    public void setStatus(@StringRes int res) {
        if (res == R.string.status_account_login_running
                || res == R.string.status_account_register_running) {
            mAccountBtnChange.setEnabled(false);
            mAccountBtnSubmit.setEnabled(false);
            mAccountBtnSubmit.setText("");
        } else if (res == R.string.status_account_bind_succeed) {
            finish();
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        } else {
            mAccountBtnChange.setEnabled(true);
            mAccountBtnSubmit.setEnabled(true);
            mAccountBtnSubmit.setText(R.string.txt_go);
            hideLoading();
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        }
    }
}
