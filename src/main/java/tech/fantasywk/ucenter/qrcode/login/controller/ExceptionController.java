package tech.fantasywk.ucenter.qrcode.login.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller层异常捕捉
 *
 * @author wangkai  2019/7/20
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handler(){
        JSONObject result = new JSONObject();
        result.put("status",false);
        return result.toJSONString();
    }
}
