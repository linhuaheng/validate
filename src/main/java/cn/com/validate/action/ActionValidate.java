package cn.com.validate.action;

import cn.com.iscs.common.exception.ApiErrorCode;
import cn.com.iscs.common.util.AbsResponse;
import cn.com.iscs.common.util.JsonUtils;
import cn.com.iscs.common.util.ReqUtil;
import cn.com.validate.enums.ParameterType;
import cn.com.validate.factory.Context;
import cn.com.validate.service.ParamValidate;
import cn.com.validate.service.ParamsValidate;
import cn.com.validate.util.DateUtil;
import cn.com.validate.util.RegexUtils;
import cn.com.validate.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
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

    private static int ERRTYPE = 101;

    private static int ERRMINLENGTH = 102;

    private static int ERRMAXLENGTH = 103;

    private static int ERRMINVALUE = 104;

    private static int ERRMAXVALUE = 106;

    private static int ERRREGEX = 107;

    private static int ERREQUALTO = 108;


    private static Map<String, ParamsValidate> validateMap = new ConcurrentHashMap<String, ParamsValidate>();

    @Around("@annotation(cn.com.validate.service.ParamsValidate)")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {

        HashMap<String, Object> map = null;
        HttpServletResponse response = null;
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object object : args) {
                if (object instanceof HttpServletResponse) {
                    response = (HttpServletResponse) object;//获取请求对象
                }
                if (object instanceof HashMap) {
                    map = (HashMap) object;//获取请求对象
                }
            }
        }

        if (map == null) {
            logger.warn("ControllerValidate.validate Failed to get request");
            writeJson(ApiErrorCode.InvalidArguments.getErrorCode(), response, "");
            return false;
        }
        //获取校验对象
        Signature sign = joinPoint.getSignature();
        String LongStr = sign.toLongString();
        ParamsValidate paramsValidate = validateMap.get(LongStr);
        if (paramsValidate == null) {
            MethodSignature msign = (MethodSignature) sign;
            Method method = msign.getMethod();
            paramsValidate = method.getAnnotation(ParamsValidate.class);
            validateMap.put(LongStr, paramsValidate);
        }
        if (paramsValidate == null) {
            logger.warn("Failed to get request ParamsValidate");
            writeJson(ApiErrorCode.InvalidArguments.getErrorCode(), response, "");
            return false;
        }
        //验证码和重复提交
//        HttpSession session = request.getSession();
//        if(paramsValidate.iCode()){//校验验证码
//            Object code = session.getAttribute(IdentifyingCode.ICODE);
//            String icode = request.getParameter(IdentifyingCode.ICODE.toLowerCase());
//            if(icode==null || code==null || !icode.equalsIgnoreCase((String)code)){
//                Object count = session.getAttribute(IdentifyingCode.ICODE+"_count");//验证码比较次数
//                int c = 1;
//                String errorMsg = "验证码错误";
//                if(count instanceof Integer){
//                    c = (Integer) count;
//                    if(c<5){
//                        session.setAttribute(IdentifyingCode.ICODE+"_count", ++c);
//                    }else{//验证码比较 5次及以上
//                        session.removeAttribute(IdentifyingCode.ICODE);
//                        session.removeAttribute(IdentifyingCode.ICODE+"_count");
//                        errorMsg="验证码错误，请刷新验证码后再输入！";
//                    }
//                }else{
//                    session.setAttribute(IdentifyingCode.ICODE+"_count", c);
//                }
//                throw new HYException("VERIFICATION CODE ERROR", errorMsg);
//            }else{
//                session.removeAttribute(IdentifyingCode.ICODE);
//                session.removeAttribute(IdentifyingCode.ICODE+"_count");
//            }
//        }
//        if(paramsValidate.postToken()){//校验重复提交
//            String postToken = request.getParameter("postToken");
//            if(postToken==null || !postToken.equals(session.getAttribute(BaseController.POST_TOKEN))){
//                throw new HYException("REPEAT SUBMIT", "请勿重复提交");
//            }else{
//                session.setAttribute(BaseController.POST_TOKEN,BaseUtil.getRandomStr(32));
//            }
//        }



        //开始校验
        ParamValidate[] pvs = paramsValidate.value();
        if (pvs == null) {
            logger.warn("ParamValidate is empty");
            writeJson(ApiErrorCode.InvalidArguments.getErrorCode(), response, "");
            return false;
        }

        for (ParamValidate pv : pvs) {
            String name = pv.name();
            int errorCode = pv.errorCode();
            Object value = map.get(name);
            if (value != null) {

                String val = value.toString();

                if (!"".equals(pv.type()) && !checkType(pv.type(), val)) {
                    logger.warn("数据类型不正确" + name);
                    writeJson(ERRTYPE, response, pv.placeHolder());
                    return false;
                }

                if (0 != pv.minLength() && val.length() < pv.minLength()) {
                    logger.warn("数据长度小于最小长度" + name);
                    writeJson(ERRMINLENGTH, response, pv.placeHolder(), String.valueOf(pv.minLength()));
                    return false;
                }

                if (0 != pv.maxLength() && val.length() > pv.maxLength()) {
                    logger.warn("数据长度大于最大长度" + name);
                    writeJson(ERRMAXLENGTH, response, pv.placeHolder(), String.valueOf(pv.maxLength()));
                    return false;
                }

                if (checkType(ParameterType.NUMBER.getValue(), val)) {
                    if (!"".equals(pv.minValue()) && Double.valueOf(val) < Double.valueOf(pv.minValue())) {
                        logger.warn("数字小于限定范围" + name);
                        writeJson(ERRMINVALUE, response, pv.placeHolder(), pv.minValue());
                        return false;
                    }

                    if (!"".equals(pv.maxValue()) && Double.valueOf(val) > Double.valueOf(pv.maxValue())) {
                        logger.warn("数字大于限定范围" + name);
                        writeJson(ERRMAXVALUE, response, pv.placeHolder(), pv.maxValue());
                        return false;
                    }
                }

                if (ParameterType.REGEX.getValue().equals(pv.type()) && !val.matches(pv.regex())) {
                    logger.warn("匹配正则表达式失败" + name);
                    writeJson(ERRREGEX, response, pv.placeHolder());
                    return false;
                }

                if (StringUtils.isNotEmpty(pv.equalTo())) {
                    Object eqvalue = map.get(pv.equalTo());
                    if (eqvalue != null && !val.equals(eqvalue.toString())) {
                        String placeHolder = pv.equalTo();
                        for (ParamValidate paramValidate : pvs) {
                            if (paramValidate.name().equals(pv.equalTo())) {
                                logger.warn(name + "数据错误,不等于" + paramValidate.name());
                                placeHolder = paramValidate.placeHolder();
                            }
                        }
                        writeJson(ERREQUALTO, response, pv.placeHolder(), placeHolder);
                        return false;
                    }
                }

            } else if (pv.required()) {
                logger.warn("缺少必要参数：" + name);
                writeJson(errorCode, response, pv.placeHolder());
                return false;
            }
        }

        return joinPoint.proceed();
    }

    /**
     * 输出信息
     */
    protected void writeJson(int errorCode, HttpServletResponse response, String... msg) throws Exception {
        AbsResponse<String> abs = new AbsResponse<String>();
        ReqUtil.setErrAbs(abs, errorCode, msg);
        String data = JsonUtils.toJson(abs);
        ReqUtil.writeData(response, data);
    }

    //类型的判断
    public boolean checkType(String type, String value) {
        return new Context().calRecharge(type, value);
    }


}
