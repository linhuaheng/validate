package cn.com.validate.enums.rule.impl;

import cn.com.validate.service.ParamValidate;

import java.util.regex.Pattern;

public class UrlValidate extends BaseValidate {
    private ParamValidate rule;
    private String value;

    public UrlValidate(ParamValidate rule, String value) {
        super(rule, value);
        this.value = value;
        this.rule = rule;
    }

    public boolean type() {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, value);
    }
}
