package cn.com.validate.action;

import cn.com.iscs.common.exception.ApiErrorCode;
import cn.com.iscs.common.util.AbsResponse;
import cn.com.iscs.common.util.JsonUtils;
import cn.com.iscs.common.util.ReqUtil;
import cn.com.validate.enums.rule.impl.EqualToValidate;
import cn.com.validate.enums.rule.IValidate;
import cn.com.validate.enums.ValidateUtil;
import cn.com.validate.service.ParamValidate;
import cn.com.validate.service.ParamsValidate;
import cn.com.validate.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jianjun.guan on 2017/5/11 0011.
 */
@Component
@Aspect
public class ActionValidate {

    private Logger logger = LogManager.getLogger(getClass());

    private static Map<String, ParamsValidate> validateMap = new ConcurrentHashMap<String, ParamsValidate>();

    @Around("@annotation(cn.com.validate.service.ParamsValidate)")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        HashMap<String, Object> params = null;
        HttpServletResponse response = null;
        HttpServletRequest request = null;
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object object : args) {
                if (object instanceof HttpServletResponse) {
                    response = (HttpServletResponse) object;//获取请求对象
                }
//                if (object instanceof HttpServletRequest) {
//                    request = (HttpServletRequest) object;//获取请求对象
//                }
                if (object instanceof HashMap) {
                    params = (HashMap) object;//获取请求对象
                }
            }
        }
        //获取校验对象
        ParamsValidate paramsValidate = invoke(joinPoint);
        if (paramsValidate == null || paramsValidate.value().length == 0) {
            return joinPoint.proceed();
        }

        if (params == null || params.isEmpty()) {
            logger.warn("ControllerValidate.validate Failed to get request");
            writeJson(ApiErrorCode.InvalidArguments.getErrorCode(), response, "");
            return false;
        }

        //开始校验
        ParamValidate[] pvs = paramsValidate.value();
        AbsResponse<String> abs = null;
        for (ParamValidate pv : pvs) {
            abs = validate(params, pv, pvs);
            if (!abs.isSuccess()) {
                writeJson(abs.getCode(), response, abs.getMsg());
                return false;
            }
        }
        return joinPoint.proceed();
    }

    private AbsResponse<String> validate(HashMap<String, Object> params, ParamValidate pv, ParamValidate[] pvs) throws Exception {
        String name = pv.name();
        String placeHolder = pv.placeHolder();
        if (StringUtils.isEmpty(placeHolder)) {
            placeHolder = name;
        }
        int errorCode = pv.errorCode();
        Object oValue = params.get(name);

        IValidate rule = ValidateUtil.createValidateRule(pv, (String) oValue);
        if (StringUtils.isNotEmpty(pv.equalTo())) {
            String otherValue = (String) params.get(pv.equalTo());

            EqualToValidate ev = (EqualToValidate) rule;
            String otherPlaceHolder = null;
            for (ParamValidate paramValidate : pvs) {
                if (paramValidate.name().equals(pv.equalTo())) {
                    otherPlaceHolder = paramValidate.placeHolder();
                }
            }

            ev.setOtherValue(otherValue);
            ev.setOtherPlaceHolder(otherPlaceHolder);
        }
        return rule.validate(placeHolder);
    }

    //获取校验对象
    private ParamsValidate invoke(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        String methodLongString = signature.toLongString();
        ParamsValidate paramsValidate = validateMap.get(methodLongString);

        Method method = null;
        if (paramsValidate == null) {
            MethodSignature msign = (MethodSignature) signature;
            method = msign.getMethod();
//            Object target = joinPoint.getTarget();
//            Method currentMethod = target.getClass().getMethod(msign.getName(), msign.getParameterTypes());
            paramsValidate = method.getAnnotation(ParamsValidate.class);

            validateMap.put(methodLongString, paramsValidate);
        }

        return paramsValidate;
    }

    /**
     * 输出信息
     */
    protected void writeJson(int errorCode, HttpServletResponse response, String msg) throws IOException {
        AbsResponse<String> abs = new AbsResponse<String>(errorCode, msg);
        String data = JsonUtils.toJson(abs);
        ReqUtil.writeData(response, data);
    }
}
