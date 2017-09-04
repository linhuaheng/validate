package cn.com.validate.enums.ins.impl;

import cn.com.validate.enums.rule.impl.NumberValidate;
import cn.com.validate.enums.ins.IValidateCreator;
import cn.com.validate.service.ParamValidate;

/**
 * Created by zhigang.huang on 2017/5/24.
 */
public class NumberRuleCreator implements IValidateCreator {
    public NumberValidate createValidater(ParamValidate rule, String value) {
        return new NumberValidate(rule, value);
    }
}
