package tech.fantasywk.ucenter.qrcode.login.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.fantasywk.ucenter.qrcode.login.model.Person;
import tech.fantasywk.ucenter.qrcode.login.service.IQrCodeLoginService;

/**
 * 二维码登录相关接口
 *
 * @author wangkai  2019/7/20
 */
@RestController
@RequestMapping("/qrcode")
public class QrCodeLoginController {

    @Autowired
    IQrCodeLoginService qrCodeLoginService;

    @GetMapping
    public String getQrCode(){

        JSONObject result = new JSONObject();
        result.put("status",true);
        result.put("data",qrCodeLoginService.generateQrCode());

        return result.toJSONString();
    }

    @GetMapping(value = "/scan/loop")
    public String scan(@RequestParam String token){

        JSONObject result = new JSONObject();
        result.put("status",true);

        Person person = qrCodeLoginService.getScanStatus(token);
        if (person == null) {
            // 过期
            result.put("type",0);
        }else{
            // 已经扫描
            result.put("type",person.getScan().getType());
            result.put("userName",person.getUserName());
        }

        return result.toJSONString();
    }

    /**
     * 成功扫描
     */
    @PostMapping(value = "/scan/ok")
    public String scanOk(@RequestParam String token,@RequestParam String userName){

        qrCodeLoginService.scanOk(token,userName);

        JSONObject result = new JSONObject();
        result.put("status",true);
        return result.toJSONString();
    }

    /**
     * 确认登陆
     */
    @PostMapping(value = "/scan/login/{token}")
    public String scanLogin(@PathVariable String token,@RequestParam String userName){
        JSONObject result = new JSONObject();
        result.put("status",qrCodeLoginService.qrLogin(token, userName));
        return result.toJSONString();
    }

    /**
     * 取消登录
     */
    @PostMapping(value = "/scan/cancel/{token}")
    public String cancelLogin(@PathVariable String token){

        qrCodeLoginService.cancelLogin(token);

        JSONObject result = new JSONObject();
        result.put("status",true);
        return result.toJSONString();
    }
}
