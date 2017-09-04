package cn.com.validate.factory;

import cn.com.validate.enums.ParameterType;
import cn.com.validate.service.*;
import cn.com.validate.service.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianjun.guan on 2017/5/16 0016.
 */
public class StrategyFactory {
    private static StrategyFactory factory = new StrategyFactory();

    private StrategyFactory(){

    }

    private static Map<String,Strategy> strategyMap = new HashMap();

    static{
        strategyMap.put(ParameterType.NUMBER.getValue(),new NumberStrategy());
        strategyMap.put(ParameterType.INTEGER.getValue(),new IntegerStrategy());
        strategyMap.put(ParameterType.DECIMAL.getValue(),new DecimalStrategy());
        strategyMap.put(ParameterType.EMAIL.getValue(),new EmailStrategy());
        strategyMap.put(ParameterType.DTAE.getValue(),new DateStrategy());
        strategyMap.put(ParameterType.CONTACTNUMBER.getValue(),new ContactnumberStrategy());
        strategyMap.put(ParameterType.MOBILE.getValue(),new MobileStrategy());
        strategyMap.put(ParameterType.PHONE.getValue(),new PhoneStrategy());
        strategyMap.put(ParameterType.STRING.getValue(),new StringStrategy());
        strategyMap.put(ParameterType.URL.getValue(),new UrlStrategy());
        strategyMap.put(ParameterType.ZIP.getValue(),new ZipStrategy());
        strategyMap.put(ParameterType.IDCARD.getValue(),new IdCardStrategy());
        strategyMap.put(ParameterType.REGEX.getValue(),new RegexStrategy());
    }

    public Strategy creator(String type){
        return strategyMap.get(type);
    }
    public static StrategyFactory getInstance(){

        return factory;

    }
}
