package freestar.bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;


public class RegisterModel {
    @Expose
    private String phone;
    @Expose
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 校验 true 参数正确
    public static boolean check(RegisterModel model) {
        return model != null &&
                !Strings.isNullOrEmpty(model.phone) &&
                !Strings.isNullOrEmpty(model.password);
    }

}
