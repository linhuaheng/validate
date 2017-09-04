package cn.com.validate.service.impl;

import cn.com.validate.service.Strategy;
import cn.com.validate.util.RegexUtils;
import cn.com.validate.util.StringUtils;

/**
 * Created by jianjun.guan on 2017/5/16 0016.
 */
public class IntegerStrategy implements Strategy {
    public boolean calRecharge(String val) {
        return  RegexUtils.checkDigit(val);
    }
}
