package cn.com.validate.enums.rule.impl;

import cn.com.validate.service.ParamValidate;

import java.util.regex.Pattern;

public class EmailValidate extends StringValidate {
    private ParamValidate rule;
    private String value;

    public EmailValidate(ParamValidate rule, String value) {
        super(rule, value);
        this.value = value;
        this.rule = rule;
    }

    public boolean type() {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, value);
    }
}
