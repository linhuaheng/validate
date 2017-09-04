package cn.com.validate.service.impl;

import cn.com.validate.service.Strategy;
import cn.com.validate.util.StringUtils;

/**
 * Created by jianjun.guan on 2017/5/16 0016.
 */
public class NumberStrategy implements Strategy{
    public boolean calRecharge(String val) {
        return  StringUtils.isDouble(val) || StringUtils.isNumeric(val);
    }
}
