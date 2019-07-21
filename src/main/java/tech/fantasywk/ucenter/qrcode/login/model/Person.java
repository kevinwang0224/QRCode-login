package tech.fantasywk.ucenter.qrcode.login.model;

import lombok.Data;
import tech.fantasywk.ucenter.qrcode.login.common.ScanEnum;

/**
 * 用户与二维码扫描状态的关系
 *
 * @author wangkai  2019/7/20
 */
@Data
public class Person {
    private String userName;
    private ScanEnum scan = ScanEnum.WAIT_SCAN;
}
