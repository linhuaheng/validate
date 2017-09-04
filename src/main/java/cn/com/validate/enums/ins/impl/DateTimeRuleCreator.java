package cn.com.validate.enums.ins.impl;

import cn.com.validate.enums.rule.impl.DateTimeValidate;
import cn.com.validate.enums.ins.IValidateCreator;
import cn.com.validate.service.ParamValidate;

/**
 * Created by zhigang.huang on 2017/5/24.
 */
public class DateTimeRuleCreator implements IValidateCreator{
    public DateTimeValidate createValidater(ParamValidate rule, String value){
        return new DateTimeValidate(rule, value);
    }
}
