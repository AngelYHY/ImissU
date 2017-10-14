package freestar.bean.api.account;

import freestar.bean.db.User;

public class AccountRspModel {


    private final String mPhone;
    private final String mPassword;

    public AccountRspModel(User user) {
        mPhone = user.getPhone();
        mPassword = user.getPassword();
    }

}
