package freestar.imissu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import net.qiujuer.genius.ui.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        mViewAnimator.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_in_slide_right));
        mViewAnimator.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_out_slide_left));

        mAccountBtnSubmit.setOnClickListener(this);
        mAccountBtnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_btn_submit:
                if (isLogin()) {

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

}
