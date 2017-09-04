package cn.com.validate.action;

import cn.com.validate.enums.ParameterType;
import cn.com.validate.service.ParamValidate;
import cn.com.validate.service.ParamsValidate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by jianjun.guan on 2017/5/11 0011.
 */
@Controller
@RequestMapping("/")
public class ValidateController {
    private static Logger logger = LogManager.getLogger(ValidateController.class);

    @RequestMapping("/testValidate")
    @ParamsValidate(value = {
            @ParamValidate(name = "name", required = true, type = ParameterType.STRING, minLength = 2, maxLength = 10, errorCode = 101,placeHolder = "名称")}
    //            @ParamValidate(name = "value", required = true, type = ParameterType.DECIMAL, maxValue = 20.5),
    //            @ParamValidate(name = "age", required = true, type = ParameterType.NUMBER, maxValue = 255.0),
    //            @ParamValidate(name = "qq", required = true, type = ParameterType.REGEX, regex = "[1-9]\\d{4,}"),
    //            @ParamValidate(name = "date", required = true, type = ParameterType.DATE, errorCode = 102),
    ////            @ParamValidate(name = "phone", required = true, type = ParameterType.PHONE, errorCode = 103),
    //            @ParamValidate(name = "email", required = true, type = ParameterType.EMAIL, errorCode = 104),
    ////            @ParamValidate(name = "mobile", required = true, type = ParameterType.MOBILE, errorCode = 104),
    ////            @ParamValidate(name = "idCard", required = true, type = "idCard", errorCode = 104),
    //            @ParamValidate(name = "address", type = ParameterType.STRING, minLength = 10, maxLength = 150, errorCode = 105)}
//            , postToken=true,iCode=true
    )
    public String testV(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody() HashMap<String, Object> params) {
        System.out.println(params.get("name"));
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

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        return "Hello, " + name;
    }

    @ResponseBody
    @RequestMapping("/sayBye")
    public String sayBye(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody() HashMap<String, Object> params) {
        String name = (String) params.get("name");
        return "Bye, " + name;
    }

    @RequestMapping(value = {"/index1", "/index"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "/index.html";
    }
}
