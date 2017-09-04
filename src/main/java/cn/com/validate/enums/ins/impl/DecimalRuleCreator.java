package cn.com.validate.enums.ins.impl;

import cn.com.validate.enums.rule.impl.DecimalValidate;
import cn.com.validate.enums.ins.IValidateCreator;
import cn.com.validate.service.ParamValidate;

/**
 * Created by zhigang.huang on 2017/5/24.
 */
public class DecimalRuleCreator implements IValidateCreator {
    public DecimalValidate createValidater(ParamValidate rule, String value) {
        return new DecimalValidate(rule, value);
    }
}
