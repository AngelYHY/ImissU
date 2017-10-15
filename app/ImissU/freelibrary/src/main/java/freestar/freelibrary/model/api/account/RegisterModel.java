package freestar.freelibrary.model.api.account;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/14
 * github：
 */

public class RegisterModel {
    private String phone;
    private String password;

    public RegisterModel(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

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
}
