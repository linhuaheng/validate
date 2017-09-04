package cn.com.validate.enums.ins.impl;

import cn.com.validate.enums.rule.impl.RegexValidate;
import cn.com.validate.enums.ins.IValidateCreator;
import cn.com.validate.service.ParamValidate;

/**
 * Created by zhigang.huang on 2017/5/24.
 */
public class RegexRuleCreator implements IValidateCreator{
    public RegexValidate createValidater(ParamValidate rule, String value){
        return new RegexValidate(rule, value);
    }
}
