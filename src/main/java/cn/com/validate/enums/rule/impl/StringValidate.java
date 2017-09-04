package cn.com.validate.enums.rule.impl;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.validate.enums.ValidateErrorCode;
import cn.com.validate.enums.ValidateUtil;
import cn.com.validate.service.ParamValidate;

public class StringValidate extends BaseValidate {
    private ParamValidate rule;
    private String value;

    public StringValidate(ParamValidate rule, String value) {
        super(rule, value);
        this.value = value;
        this.rule = rule;
    }

    public AbsResponse<String> validate(String placeHolder) {
        AbsResponse<String> abs = super.validate(placeHolder);
        if (!abs.isSuccess()) {
            return abs;
        }
        return this.minLength() ? (this.maxLength() ? abs : ValidateUtil.createAbs(abs, ValidateErrorCode.ERRMAXLENGTH.getErrorCode(), placeHolder, String.valueOf(rule.maxLength())))
                : ValidateUtil.createAbs(abs, ValidateErrorCode.ERRMINLENGTH.getErrorCode(), placeHolder, String.valueOf(rule.minLength()));

    }

    public boolean minLength() {
        if (rule.minLength() == -1) {
            return true;
        }

        return value != null && value.length() >= rule.minLength();
    }

    public boolean maxLength() {
        if (rule.maxLength() == -1) {
            return true;
        }

        return value != null && value.length() <= rule.maxLength();
    }

}
