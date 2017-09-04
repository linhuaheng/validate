package cn.com.validate.enums.rule.impl;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.validate.enums.ValidateErrorCode;
import cn.com.validate.enums.ValidateUtil;
import cn.com.validate.service.ParamValidate;

public class NumberValidate extends BaseValidate {
    private ParamValidate rule;
    private Long value;

    public NumberValidate(ParamValidate rule, String value) {
        super(rule, value);
        try {
            this.value = Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        this.rule = rule;
    }

    public AbsResponse<String> validate(String placeHolder){
        AbsResponse<String> abs = super.validate(placeHolder);
        if(!abs.isSuccess()){
            return abs;
        }
        return min() ? (max() ? abs : ValidateUtil.createAbs(abs, ValidateErrorCode.ERRMAXVALUE.getErrorCode(), placeHolder, String.valueOf(rule.maxValue())))
                : ValidateUtil.createAbs(abs, ValidateErrorCode.ERRMINVALUE.getErrorCode(), placeHolder, String.valueOf(rule.minValue()));
    }

    public boolean min() {
        if (rule.minValue() == -1.0) {
            return true;
        }

        return value != null && value.intValue() >= rule.minValue();
    }

    public boolean max() {
        if (rule.maxValue() == -1.0) {
            return true;
        }

        return value != null && value.intValue() <= rule.maxValue();
    }

}
