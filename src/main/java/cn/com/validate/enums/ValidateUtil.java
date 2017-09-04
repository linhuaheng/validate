package cn.com.validate.enums;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.iscs.common.util.ReqUtil;
import cn.com.validate.enums.ins.IValidateCreator;
import cn.com.validate.enums.rule.IValidate;
import cn.com.validate.service.ParamValidate;

/**
 *
 */
public class ValidateUtil {

    public static IValidate createValidateRule(ParamValidate rule, String value) {
        IValidateCreator creator = rule.type().getCreator();

        return creator == null? null : creator.createValidater(rule, value);
    }

    public static AbsResponse<String> createAbs(AbsResponse<String> abs, int errorCode, String... msg) {
        ReqUtil.setErrAbs(abs, errorCode, String.format("", msg));
        return abs;
    }
}
