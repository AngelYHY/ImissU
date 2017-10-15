package freestar.imissu.my_base;

import freestar.freelibrary.common.app.BaseApplication;
import freestar.imissu.injector.component.ApplicationComponent;
import freestar.imissu.injector.component.DaggerApplicationComponent;
import freestar.imissu.injector.module.ApplicationModule;

/**
 * 描述：
 * 作者：一颗浪星
 * 日期：2017/9/19
 * github：
 */

public class MyApplication extends BaseApplication {
    private ApplicationComponent mComponent;

    public ApplicationComponent getApplicationComponent() {
        return mComponent;
    }


    @Override
    protected void initKey() {
        this.appId = "24628076-1";
        this.appSecret = "22362113c07a706e34ade27174c38f7f";
        this.rsa = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoRLVCCBYXPtSG5ylbeqoTdVcfi+CYlFTmad7U0ZVsvrtYCSrPOVY9ybQf6WDmP04DOO54n4HCPsZVYIIUxSThChT7rT9YqtClp4D+iB+r3QQbwc5/V1dBmcnkMIoibaRvhH4U5pCVRvENgMx5ZB4WLMAHVryDOU24RmzLuUZovGG3q1COI3ETdOF0lNKCiswwjBgZ4WQMhfxAbvdR3/vDrJLSZO1sz6iY7jYABO0GKDD++Ut0RKS6pIQA2TM3aG6snyB+HnPcMVb4W0U6bBcbYYY3uihad7kNG3vT5+7ggt1XloJ7a7ChcZ6L8HG2GWQsC2G6chae8kPneSGt+vjJAgMBAAECggEATXz2i6X1plbEwCsLnJjsYXGT4W8kyp7kSgdPGmo5iY5775qpICyjTmjojhmHCgD1B/7bsqihtLX7161NugrfZNKjvxKVLVvcIaz1dkFxdE+faLjjQzkRcaBEnNTNh/U92C0HctCKnHPC5hj+EJjOUh/hfn+u6C8nX0jvKRH1Hp2BXQseH422lCnIraCizU5oqsjy3We15gIRbCa5iYW5Vf10A79Dsi5EA4lKDmQFMHs50a4QUZkcxDUvnyW1+jjCNNc+8GhCsW4+slpusRayQS2LQnrHKQgbYGqUQ2RMKsiPqrA7a9rwiWiURMJUWkQn83jiIo2Y7bdCFhBVkgpCQQKBgQDpOSqAbGEHnh3mfzQavE60k4eO60AGekRzgQyjvDkoMW1+LMKgDqzBYN17aoIa8aP4zE8pu+G/ppEfzWaX1qvK3iXej18TCjgGW3WnYitpLXdWFRoWsW389D5MN8w7xAsyioq38oeQdewI21pabJov1K3xMq/7Fxchqp0a936RfQKBgQC4s5lini5ka7wMxtCmAzrsAOSpbevhk9Ep5SWLQt6MfalMNrlHAraDUISEgLHIcJkrKURcA7K+1BFSuWzLoGZaVcXwzzvOZiMdgnTB4dfO4E+IUYhn8f5DVjFCFbUq7Mo7Pw0HVgtYWs94MYgALQaIDJOlS70fz8kVtNk2kUzmPQKBgECjq3f0kd/+nuLnlpViY54A1a1y3kftU1OADPTEkkLlkCdMWJTr/E2Mf1ZZar31AzFNzxMOE79+dvKNh0XGJc8QSwaoTf2E84ovhyhhOQ7pnw2ow5ZOjuFxAGM3g898wm27oD7jZVdQl57MzPoK56EiHSOgnM3G1I5sLybuOZIxAoGAGgbc32Ow39+6kQZXEn20CNubXTCrI49j0waCQtx6ZcuzVpUDiLkeJOTst4s3AiqzurxG8LxorLFPe5u7c/o3UhVyHhvlrpD/88GVWFjNw04ihvAOS69zB+QWbtyGOfVCDhU1bco3MQ0i3SoFoxi1KeizyYAht2DdUhy42xKghnUCgYEA5nGISjlrmGYBpYJK3xrkD/YnbdWdzuw06hKZ8SirkR2JN6u19HXx1KcXLapGiK8IJ3ioIEcPde2ygUwHBcs2p5qiuebEhzpCh0bJTBUwoKsuVpu3yw6oXZZyFn2CntW9v1GFYqQKsHeS4B97zV0jkmp8bFoHwEeUP8B4g91zwX8=";
    }

    @Override
    protected void initComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.injectApplication(this);
    }

}