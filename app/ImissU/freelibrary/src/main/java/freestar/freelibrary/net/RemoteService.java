package freestar.freelibrary.net;

import freestar.freelibrary.model.RspModel;
import freestar.freelibrary.model.api.account.AccountRspModel;
import freestar.freelibrary.model.api.account.RegisterModel;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 描述：网络请求的所有的接口
 * 作者：一颗浪星
 * 日期：2017/10/14
 * github：
 */

public interface RemoteService {
    /**
     * 注册接口
     *
     * @param model 传入的是 RegisterModel
     * @return 返回的是 RspModel<AccountRspModel>
     */
    @POST("account/register")
    Observable<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

}
