package cn.com.validate.enums.rule.impl;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.validate.enums.ValidateErrorCode;
import cn.com.validate.enums.ValidateUtil;
import cn.com.validate.service.ParamValidate;

import java.util.regex.Pattern;

public class RegexValidate extends StringValidate {
    private ParamValidate rule;
    private String value;

    public RegexValidate(ParamValidate rule, String value) {
        super(rule, value);
        this.value = value;
        this.rule = rule;
    }

    public  AbsResponse<String> validate(String placeHolder) {
        AbsResponse<String> abs = super.validate(placeHolder);
        if(!abs.isSuccess()){
            return abs;
        }
        return this.regex()?abs:ValidateUtil.createAbs(abs, ValidateErrorCode.ERRREGEX.getErrorCode(), placeHolder);
    }

    public boolean regex() {
        return value != null && Pattern.matches(rule.regex(), value);
    }

}
