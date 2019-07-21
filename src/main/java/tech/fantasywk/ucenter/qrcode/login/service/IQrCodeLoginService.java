package tech.fantasywk.ucenter.qrcode.login.service;

import tech.fantasywk.ucenter.qrcode.login.model.LoginQrCode;
import tech.fantasywk.ucenter.qrcode.login.model.Person;

/**
 * 二维码登录相关service
 *
 * @author wangkai  2019/7/20
 */
public interface IQrCodeLoginService {

    /**
     * 生成二维码
     *
     * @return token and image
     */
    LoginQrCode generateQrCode();

    /**
     * 获取扫描状态
     *
     * @param token 扫码登录中的唯一表示,用于确定用户
     * @return ScanEnum
     */
    Person getScanStatus(String token);

    /**
     * 成功扫描
     */
    void scanOk(String token, String userName);

    /**
     * 确认登录
     */
    boolean qrLogin(String token, String userName);

    /**
     * 取消登录
     */
    void cancelLogin(String token);
}
