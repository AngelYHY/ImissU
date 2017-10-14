package freestar.service;

import freestar.bean.db.User;
import freestar.bean.api.account.AccountRspModel;
import freestar.bean.api.account.RegisterModel;
import freestar.bean.api.base.ResponseModel;
import freestar.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountService extends BaseService {

    @GET
    @Path("/login2")
    public String get() {
        System.out.println("进入 get 方法");
        return "You get the login.";
    }

    // 注册
    @POST
    @Path("/register")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> register(RegisterModel model) {
        // 参数异常
        if (!RegisterModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        User user = UserFactory.findByPhone(model.getPhone());

        if (user != null) {
            // 号码已存在
            return ResponseModel.buildHaveAccountError();
        }

        // 开始注册逻辑
        user = UserFactory.register(model.getPhone(), model.getPassword());
        if (user != null) {
            // 返回当前的帐号
            AccountRspModel rspModel = new AccountRspModel(user);
            return ResponseModel.buildOk(rspModel);
        }else {
            // 注册异常
            return ResponseModel.buildRegisterError();
        }

    }
}
