package cn.com.validate.enums.ins;

import cn.com.validate.enums.rule.IValidate;
import cn.com.validate.service.ParamValidate;

/**
 * Created by zhigang.huang on 2017/5/24.
 */
public interface IValidateCreator {
    public IValidate createValidater(ParamValidate rule, String value);
}
