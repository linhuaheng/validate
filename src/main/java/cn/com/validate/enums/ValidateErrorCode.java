package cn.com.validate.enums;

/**
 * Created by jianjun.guan on 2017/5/25 0025.
 */
public enum ValidateErrorCode {

    ERRTYPE(101,"数据类型错误"),
    ERRMINLENGTH(102,""),
    ERRMAXLENGTH(103,""),
    ERRMINVALUE(104,""),
    ERRMAXVALUE(106,""),
    ERRREGEX(107,""),
    ERREQUALTO(108,""),;

    private int code;
    private String text;

    private ValidateErrorCode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getErrorCode() {
        return this.code;
    }

    public String getErrorText() {
        return this.text;
    }

    public String toString() {
        return String.valueOf(getErrorCode()) + ":" + getErrorText();
    }

    public static ValidateErrorCode valueOf(int code) {
        for (ValidateErrorCode err : ValidateErrorCode.values()) {
            if (err.getErrorCode() == code) {
                return err;
            }
        }
        return null;
    }
}
