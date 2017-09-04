package cn.com.validate.enums.rule.impl;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.validate.enums.ValidateErrorCode;
import cn.com.validate.enums.ValidateUtil;
import cn.com.validate.enums.rule.IValidate;
import cn.com.validate.service.ParamValidate;

public class BaseValidate implements IValidate {
    private ParamValidate rule;
    private Object value;

    public BaseValidate(ParamValidate rule, Object value) {
        this.rule = rule;
        this.value = value;
    }

    public AbsResponse<String> validate(String placeHolder) {
        return required() && type() ? new AbsResponse<String>() :
                ValidateUtil.createAbs(new AbsResponse<String>(), ValidateErrorCode.ERRTYPE.getErrorCode(), placeHolder);
    }

    public boolean type(){
        return true;
    }

    private boolean required() {
        return rule.required() ? value != null && value.toString().length() > 0 : true;
    }


}
