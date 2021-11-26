package com.wee0.box.examples.multiModule;

import com.wee0.box.IBoxConfigObject;
import com.wee0.box.code.IBizCode;
import com.wee0.box.code.impl.BizCodeDef;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/12/8 9:58
 * @Description 项目对框架可定制组件的定制对象
 * <pre>
 * 补充说明
 * </pre>
 **/
public class AppBoxConfigObject implements IBoxConfigObject {

    @Override
    public int getBizExceptionHttpStatusCode() {
        return 500;
    }

    @Override
    public IBizCode getSystemErrorBizCode() {
        return BizCodeDef.S000000;
    }

    @Override
    public IBizCode getSystemErrorInfoBizCode() {
        return BizCodeDef.S000001;
    }

    @Override
    public IBizCode getSignErrorBizCode() {
        return BizCodeDef.SignError;
    }

    @Override
    public IBizCode getNeedLoginBizCode() {
        return BizCodeDef.NeedLogin;
    }

    @Override
    public IBizCode getUnauthorizedBizCode() {
        return BizCodeDef.Unauthorized;
    }

    @Override
    public IBizCode getParamsErrorBizCode() {
        return BizCodeDef.ParamsError;
    }

}
