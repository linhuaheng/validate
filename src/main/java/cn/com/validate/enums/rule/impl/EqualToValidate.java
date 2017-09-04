package cn.com.validate.enums.rule.impl;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.validate.enums.ValidateErrorCode;
import cn.com.validate.enums.ValidateUtil;
import cn.com.validate.service.ParamValidate;

public class EqualToValidate extends BaseValidate {
    private ParamValidate rule;
    private String value;
    private String otherValue;
    private String otherPlaceHolder;

    public EqualToValidate(ParamValidate rule, String value) {
        super(rule, value);
        this.value = value;
        this.rule = rule;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    public AbsResponse<String> validate(String placeHolder)  {
        AbsResponse<String> abs = super.validate(placeHolder);
        if(!abs.isSuccess()){
            return abs;
        }
        return equalTo()?abs:ValidateUtil.createAbs(abs, ValidateErrorCode.ERREQUALTO.getErrorCode(), placeHolder, otherPlaceHolder);
    }

    public boolean equalTo() {
        return this.value != null && this.value.equals(otherValue);
    }


    public void setOtherPlaceHolder(String otherPlaceHolder) {
        this.otherPlaceHolder = otherPlaceHolder;
    }
}
