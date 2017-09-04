package cn.com.validate.service;


import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jianjun.guan on 2017/5/11 0011.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParamsValidate {
    ParamValidate[] value();
//    /**
//     * 是否验证图片验证码
//     */
//    boolean iCode() default false;
//
//    /**
//     * 是否验证重复提交
//     */
//    boolean postToken() default false;
}
