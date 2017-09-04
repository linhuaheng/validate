package cn.com.validate.enums;

import cn.com.validate.enums.ins.IValidateCreator;
import cn.com.validate.enums.ins.impl.*;

/**
 * Created by jianjun.guan on 2017/5/12 0012.
 */
public enum ParameterType {

    //字符串类型
    STRING("string", new StringRuleCreator()),
    //数字类型
    NUMBER("number", new NumberRuleCreator()),
    //小数类型
    DECIMAL("decimal", new DecimalRuleCreator()),
    //时间类型
    DATE("date", new DateRuleCreator()),
    //时间类型
    DATETIME("datetime", new DateTimeRuleCreator()),
    //手机号码
    MOBILE("mobile", null),
    //固定号码
    PHONE("phone", null),
    //邮箱
    EMAIL("email", new EmailRuleCreator()),
    //身份证号
    IDCARD("idCard", null),
    //邮编号码
    ZIP("zip", null),
    //正则表达式类型
    REGEX("regex", new RegexRuleCreator()),

    EQUALTO("equalTo", new EqualToRuleCreator()),

    URL("url", new UrlRuleCreator());


    private String value;
    private IValidateCreator creator;

    private ParameterType(String value, IValidateCreator creator) {
        this.value = value;
        this.creator = creator;
    }

    public String getValue() {
        return this.value;
    }

    public IValidateCreator getCreator() {
        return creator;
    }
}
