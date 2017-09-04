package cn.com.validate.service.impl;

import cn.com.validate.service.Strategy;
import cn.com.validate.util.DateUtil;
import cn.com.validate.util.RegexUtils;

import java.util.Date;

/**
 * Created by jianjun.guan on 2017/5/16 0016.
 */
public class EmailStrategy implements Strategy {
    public boolean calRecharge(String val) {
        return  RegexUtils.checkEmail(val);
    }
}
