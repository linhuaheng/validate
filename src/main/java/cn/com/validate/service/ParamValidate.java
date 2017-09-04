package cn.com.validate.service;

import cn.com.validate.enums.ParameterType;

/**
 * Created by jianjun.guan on 2017/5/11 0011.
 */
public @interface ParamValidate {

    /**
     * 字段名称
     *
     * @return
     */
    String name();

    /**
     * 字段的提示文字
     *
     * @return
     */
    String placeHolder() default "";

    /**
     * 字段数据类型,默认""
     *
     * @return
     */
    ParameterType type() default ParameterType.STRING;

    /**
     * 是否为空的判断,默认可以为空
     *
     * @return
     */
    boolean required() default false;

    /**
     * 错误编码,默认105
     *
     * @return
     */
    int errorCode() default 0;

    /**
     * 最小长度
     *
     * @return
     */
    int minLength() default -1;

    /**
     * 最大长度
     *
     * @return
     */
    int maxLength() default -1;

    /**
     * 最小值
     *
     * @return
     */
    double minValue() default -1.0;

    /**
     * 最大值
     *
     * @return
     */
    double maxValue() default -1.0;

    /**
     * 正则表达式
     *
     * @return
     */
    String regex() default "";

    /**
     * 与之相等的属性
     *
     * @return
     */
    String equalTo() default "";


}