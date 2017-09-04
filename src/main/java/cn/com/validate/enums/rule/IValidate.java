package cn.com.validate.enums.rule;

import cn.com.iscs.common.util.AbsResponse;
import cn.com.validate.service.ParamValidate;

/**
 *
 */
public interface IValidate {

    public AbsResponse<String> validate(String placeHolder);

    public boolean type();

}
