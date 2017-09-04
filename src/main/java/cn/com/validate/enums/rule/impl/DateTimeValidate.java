package cn.com.validate.enums.rule.impl;

import cn.com.validate.service.ParamValidate;
import cn.com.validate.util.DateUtil;

public class DateTimeValidate extends BaseValidate {
    private ParamValidate rule;
    private String value;

    public DateTimeValidate(ParamValidate rule, String value) {
        super(rule, value);
        this.value = value;
        this.rule = rule;
    }

    public boolean type() {
        return value != null && DateUtil.isDateTimeString(value);
    }
}
