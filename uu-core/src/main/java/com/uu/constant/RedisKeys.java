package com.uu.constant;


/**
 * Redis所有Keys
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
public class RedisKeys {


    public static final String menu = "smart:menu";
    public static final String MOBILE_CODE = "mobileCode";

    public static String getInviteKey(String recordNo) {
        return "smart:invite:" + recordNo;
    }
    // 1h内 手机发送验证码次数
    public static final String MOBILE_CODE_TIME = "mobileCodeTime";
    // 1天内 手机发送验证码次数
    public static final String MOBILE_CODE_TIME_PER_DAY = "mobileCodeTimePerDay";
    //30分钟内 手机验证失败次数
    public static final String VER_FAIL_TIME = "VerFailTime";
}
