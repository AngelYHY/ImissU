package freestar.freelibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import freestar.freelibrary.common.app.BaseApplication;
import freestar.freelibrary.model.RspModel;
import freestar.freelibrary.model.api.account.AccountRspModel;
import freestar.freelibrary.util.DBFlowExclusionStrategy;


/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/10/12
 * github：
 */

public class Factory {
    // 单例模式
    private static final Factory instance;
    // 全局的Gson
    private final Gson gson;

    static {
        instance = new Factory();
    }

    private final ExecutorService mExecutor;

    private Factory() {
        // 新建一个4个线程的线程池
        mExecutor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                // 设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                // 设置一个过滤器，数据库级别的 Model 不进行 Json 转换
                .setExclusionStrategies(new DBFlowExclusionStrategy())
                .create();
    }

    /**
     * Factory 中的初始化
     */
    public static void setup() {
        // 初始化数据库
        FlowManager.init(new FlowConfig.Builder(app())
                .openDatabasesOnInit(true) // 数据库初始化的时候就开始打开
                .build());
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static BaseApplication app() {
        return BaseApplication.getInstance();
    }

    public static Gson getGson() {
        return instance.gson;
    }

    /**
     * 进行错误Code的解析，
     * 把网络返回的Code值进行统一的规划并返回为一个String资源
     *
     * @param model    RspModel
//     * @param callback DataSource.FailedCallback 用于返回一个错误的资源Id
     */
//    public static void decodeRspCode(RspModel model, DataSource.FailedCallback callback) {
//        if (model == null)
//            return;
//
//        // 进行Code区分
//        switch (model.getCode()) {
//            case RspModel.SUCCEED:
//                return;
//            case RspModel.ERROR_SERVICE:
//                decodeRspCode(R.string.data_rsp_error_service, callback);
//                break;
//            case RspModel.ERROR_NOT_FOUND_USER:
//                decodeRspCode(R.string.data_rsp_error_not_found_user, callback);
//                break;
//            case RspModel.ERROR_NOT_FOUND_GROUP:
//                decodeRspCode(R.string.data_rsp_error_not_found_group, callback);
//                break;
//            case RspModel.ERROR_NOT_FOUND_GROUP_MEMBER:
//                decodeRspCode(R.string.data_rsp_error_not_found_group_member, callback);
//                break;
//            case RspModel.ERROR_CREATE_USER:
//                decodeRspCode(R.string.data_rsp_error_create_user, callback);
//                break;
//            case RspModel.ERROR_CREATE_GROUP:
//                decodeRspCode(R.string.data_rsp_error_create_group, callback);
//                break;
//            case RspModel.ERROR_CREATE_MESSAGE:
//                decodeRspCode(R.string.data_rsp_error_create_message, callback);
//                break;
//            case RspModel.ERROR_PARAMETERS:
//                decodeRspCode(R.string.data_rsp_error_parameters, callback);
//                break;
//            case RspModel.ERROR_PARAMETERS_EXIST_ACCOUNT:
//                decodeRspCode(R.string.data_rsp_error_parameters_exist_account, callback);
//                break;
//            case RspModel.ERROR_PARAMETERS_EXIST_NAME:
//                decodeRspCode(R.string.data_rsp_error_parameters_exist_name, callback);
//                break;
//            case RspModel.ERROR_ACCOUNT_TOKEN:
//                Application.showToast(R.string.data_rsp_error_account_token);
//                instance.logout();
//                break;
//            case RspModel.ERROR_ACCOUNT_LOGIN:
//                decodeRspCode(R.string.data_rsp_error_account_login, callback);
//                break;
//            case RspModel.ERROR_ACCOUNT_REGISTER:
//                decodeRspCode(R.string.data_rsp_error_account_register, callback);
//                break;
//            case RspModel.ERROR_ACCOUNT_NO_PERMISSION:
//                decodeRspCode(R.string.data_rsp_error_account_no_permission, callback);
//                break;
//            case RspModel.ERROR_UNKNOWN:
//            default:
//                decodeRspCode(R.string.data_rsp_error_unknown, callback);
//                break;
//        }
//    }
    public static int decodeRspCode(RspModel<AccountRspModel> model) {
        int str = 0;

        if (model == null) {
            return 0;
        }

        // 进行Code区分
        switch (model.getCode()) {
            case RspModel.SUCCEED:
                return 0;
            case RspModel.ERROR_SERVICE:
//                decodeRspCode(R.string.data_rsp_error_service, callback);
                str = R.string.data_rsp_error_service;
                break;
            case RspModel.ERROR_NOT_FOUND_USER:
//                decodeRspCode(R.string.data_rsp_error_not_found_user, callback;
                str = R.string.data_rsp_error_not_found_user;
                break;
            case RspModel.ERROR_NOT_FOUND_GROUP:
//                decodeRspCode(R.string.data_rsp_error_not_found_group, callback;
                str = R.string.data_rsp_error_not_found_group;
                break;
            case RspModel.ERROR_NOT_FOUND_GROUP_MEMBER:
//                decodeRspCode(R.string.data_rsp_error_not_found_group_member, callback;
                str = R.string.data_rsp_error_not_found_group_member;
                break;
            case RspModel.ERROR_CREATE_USER:
//                decodeRspCode(R.string.data_rsp_error_create_user, callback;
                str = R.string.data_rsp_error_create_user;
                break;
            case RspModel.ERROR_CREATE_GROUP:
//                decodeRspCode(R.string.data_rsp_error_create_group, callback;
                str = R.string.data_rsp_error_create_group;
                break;
            case RspModel.ERROR_CREATE_MESSAGE:
//                decodeRspCode(R.string.data_rsp_error_create_message, callback;
                str = R.string.data_rsp_error_create_message;
                break;
            case RspModel.ERROR_PARAMETERS:
//                decodeRspCode(R.string.data_rsp_error_parameters, callback;
                str = R.string.data_rsp_error_parameters;
                break;
            case RspModel.ERROR_PARAMETERS_EXIST_ACCOUNT:
//                decodeRspCode(R.string.data_rsp_error_parameters_exist_account, callback;
                str = R.string.data_rsp_error_parameters_exist_account;
                break;
            case RspModel.ERROR_PARAMETERS_EXIST_NAME:
//                decodeRspCode(R.string.data_rsp_error_parameters_exist_name, callback;
                str = R.string.data_rsp_error_parameters_exist_name;
                break;
            case RspModel.ERROR_ACCOUNT_TOKEN:
                BaseApplication.showToast(R.string.data_rsp_error_account_token);
                instance.logout();
                break;
            case RspModel.ERROR_ACCOUNT_LOGIN:
//                decodeRspCode(R.string.data_rsp_error_account_login, callback;
                str = R.string.data_rsp_error_account_login;
                break;
            case RspModel.ERROR_ACCOUNT_REGISTER:
//                decodeRspCode(R.string.data_rsp_error_account_register, callback;
                str = R.string.data_rsp_error_account_register;
                break;
            case RspModel.ERROR_ACCOUNT_NO_PERMISSION:
//                decodeRspCode(R.string.data_rsp_error_account_no_permission, callback;
                str = R.string.data_rsp_error_account_no_permission;
                break;
            case RspModel.ERROR_UNKNOWN:
            default:
//                decodeRspCode(R.string.data_rsp_error_unknown, callback;
                str = R.string.data_rsp_error_unknown;
                break;
        }
        return str;
    }

    /**
     * 收到账户退出的消息需要进行账户退出重新登录
     */
    private void logout() {

    }
}
