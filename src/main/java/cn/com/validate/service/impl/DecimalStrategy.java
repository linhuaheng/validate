package cn.com.validate.service.impl;

import cn.com.validate.service.Strategy;
import cn.com.validate.util.RegexUtils;

/**
 * Created by jianjun.guan on 2017/5/16 0016.
 */
public class DecimalStrategy implements Strategy {
    public boolean calRecharge(String val) {
        return  RegexUtils.checkDecimals(val);
    }
}
