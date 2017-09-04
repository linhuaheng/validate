package cn.com.validate.action;

import cn.com.validate.enums.ParameterType;
import cn.com.validate.service.ParamValidate;
import cn.com.validate.service.ParamsValidate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianjun.guan on 2017/5/11 0011.
 */
@Controller
@RequestMapping("/validateTest")
public class ValidateTest  {
    private Logger logger = LogManager.getLogger(getClass());
    @RequestMapping("/testV")
    @ParamsValidate(value={
            @ParamValidate(name = "name1", required = true,type = "String",placeHolder = "名称1"),
            @ParamValidate(name = "name2", required = true,type = "String", minLength = 2, maxLength = 10,placeHolder = "名称2"),
            @ParamValidate(name = "value", required = true,type = "number", minValue = "0", maxValue = "20.5",placeHolder = "数值"),
            @ParamValidate(name = "age", required = true,type = "integer", minValue = "15", maxValue = "25",placeHolder = "年龄"),
            @ParamValidate(name = "qq", required = true,type = "regex", regex = "[1-9]\\d{4,}",placeHolder = "qq"),
            @ParamValidate(name = "date", required = true,type = "date", placeHolder = "时间"),
            @ParamValidate(name = "phone", required = true,type = "phone", placeHolder = "联系电话"),
            @ParamValidate(name = "email", required = true, type = "email",placeHolder = "email"),
            @ParamValidate(name = "mobile", required = true, type = "mobile", placeHolder = "手机号"),
            @ParamValidate(name = "idCard", required = true, type = "idCard", placeHolder = "身份证号"),
            @ParamValidate(name = "address", required = false, type = "String", maxLength = 20, placeHolder = "地址")}
//            , postToken=true,iCode=true
            )
    public String testV(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody() HashMap<String, Object> params ){
        System.out.println(params.get("name1"));
        System.out.println(params.get("value"));
        System.out.println(params.get("age"));
        System.out.println(params.get("qq"));
        System.out.println(params.get("date"));
        System.out.println(params.get("phone"));
        System.out.println(params.get("mobile"));
        System.out.println(params.get("idCard"));
        System.out.println(params.get("email"));
        return null;
    }
}
