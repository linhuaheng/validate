package cn.com.validate.enums;

/**
 * Created by jianjun.guan on 2017/5/12 0012.
 */
public enum ParameterType {

    //字符串类型
    STRING("String"),
    //数字类型
    NUMBER("number"),
    //整数类型
    INTEGER("integer"),
    //小数类型
    DECIMAL("decimal"),
    //时间类型
    DTAE("date"),
    //联系号码
    CONTACTNUMBER("contactNumber"),
    //手机号码
    MOBILE("mobile"),
    //固定号码
    PHONE("phone"),
    //邮箱
    EMAIL("email"),
    //身份证号
    IDCARD("idCard"),
    //邮编号码
    ZIP("zip"),
    //正则表达式类型
    REGEX("regex"),
    //网络地址
    URL("url");


    private String value;

    private ParameterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
