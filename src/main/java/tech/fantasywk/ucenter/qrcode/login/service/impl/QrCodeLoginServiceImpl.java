package tech.fantasywk.ucenter.qrcode.login.service.impl;

import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.fantasywk.ucenter.qrcode.common.util.QrCodeUtil;
import tech.fantasywk.ucenter.qrcode.login.common.ScanEnum;
import tech.fantasywk.ucenter.qrcode.login.model.LoginQrCode;
import tech.fantasywk.ucenter.qrcode.login.model.Person;
import tech.fantasywk.ucenter.qrcode.login.service.IQrCodeLoginService;

import java.util.UUID;

/**
 * 二维码登录相关service实现类
 *
 * @author wangkai  2019/7/20
 */
@Slf4j
@Service
public class QrCodeLoginServiceImpl implements IQrCodeLoginService {

    @Autowired
    Cache<String, Person> qrCodeLoginCache;

    @Override
    public LoginQrCode generateQrCode() {
        String uuid = UUID.randomUUID().toString();
        String url = "http://192.168.124.9:8080/mobile/index?token=" + uuid;
        String qrCode = QrCodeUtil.toBase64(url, 280, 280);
        LoginQrCode result = LoginQrCode.builder().token(uuid).image(qrCode).build();
        qrCodeLoginCache.put(uuid, new Person());
        return result;
    }

    @Override
    public Person getScanStatus(String token) {

        return qrCodeLoginCache.getIfPresent(token);
    }

    @Override
    public void scanOk(String token, String userName) {
        Person person = qrCodeLoginCache.getIfPresent(token);
        if (person != null) {
            person.setUserName(userName);
            person.setScan(ScanEnum.WAIT_LOGIN);
        }
    }

    @Override
    public boolean qrLogin(String token, String userName) {
        Person person = qrCodeLoginCache.getIfPresent(token);
        if (person != null) {
            person.setScan(ScanEnum.LOGIN);
            person.setUserName(userName);
            return true;
        }
        return false;
    }

    @Override
    public void cancelLogin(String token) {
        qrCodeLoginCache.invalidate(token);
    }
}
